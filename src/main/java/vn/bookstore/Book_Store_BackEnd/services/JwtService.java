package vn.bookstore.Book_Store_BackEnd.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import vn.bookstore.Book_Store_BackEnd.entity.Role;
import vn.bookstore.Book_Store_BackEnd.entity.User;

@Component
public class JwtService {
    public static final String SECRECT = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    @Autowired
    private UserService userService;

    // tao jwt dua tren userName
    public String generateToken(String username) {

        Map<String, Object> claims = new HashMap<>();
        User user = userService.findByUserName(username);
        boolean isAdmin = false;
        boolean isStaff = false;
        boolean isUser = false;
        if(user != null && user.getRoles().size() >0) {
            List<Role> roles = user.getRoles();
            for(Role role : roles) {
                if(role.getRoleName().equalsIgnoreCase("ADMIN")) {
                    isAdmin = true;
                }
                if(role.getRoleName().equalsIgnoreCase("STAFF")) {
                    isStaff = true;
                }
                if(role.getRoleName().equalsIgnoreCase("USER")) {
                   isUser = true;
                }
            }
        }
        claims.put("isAdmin", isAdmin);
        claims.put("isStaff", isStaff);
        claims.put("isUser", isUser);
        return createToken(claims, username);

    }

    // tao jwt voi claim da chon
    @SuppressWarnings("deprecation")
    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // jwt het han sau 30 phu
                .signWith(SignatureAlgorithm.HS256 , getSignKey())
                .compact();
    }

    // lay secretky
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRECT);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // trich xuat thong tin
    @SuppressWarnings("deprecation")
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .parseClaimsJws(token)
                .getBody();
    }
    // trich xuat thong tin cho 1 claim
    public <T> T extractClaim(String token, Function<Claims , T> clamsTFuntion) {
        final Claims claims = extractAllClaims(token);
        return clamsTFuntion.apply(claims);
    }

    //kiem tra thoi gian het han tu jwt

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // kiem tra user name
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //kiem tra jwt da het han

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // kiem tra tinh hop le
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
