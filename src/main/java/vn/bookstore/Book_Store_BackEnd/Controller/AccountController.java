package vn.bookstore.Book_Store_BackEnd.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.bookstore.Book_Store_BackEnd.Security.JwtResponse;
import vn.bookstore.Book_Store_BackEnd.Security.LoginRequest;
import vn.bookstore.Book_Store_BackEnd.entity.User;
import vn.bookstore.Book_Store_BackEnd.services.AccountService;
import vn.bookstore.Book_Store_BackEnd.services.JwtService;
import vn.bookstore.Book_Store_BackEnd.services.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/account")
public class  AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
     // cho phep truy cap
    @PostMapping("/dang-ky")
    public ResponseEntity <?> dangKyNguoiDung( @Validated @RequestBody  User user){
        ResponseEntity<?> response = accountService.registryUser(user);
        return response;
    }

    @GetMapping("/kich-hoat")
    public ResponseEntity <?> kichHoatTaiKhoan( @RequestParam  String email,@RequestParam  String activateCode){
        ResponseEntity<?> response = accountService.activateAccount(email,activateCode);
        return response;
    }

    @PostMapping("/dang-nhap")
    public ResponseEntity<?> dangNhap(@RequestBody LoginRequest loginRequest) throws AuthenticationException {
        // xac thuc nguoi dung bang ten dang nhap va mat khau
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword())
            );
            if(authentication.isAuthenticated()){
                final String jwt = jwtService.generateToken(loginRequest.getUserName());
                return ResponseEntity.ok(new JwtResponse(jwt));
            }
        }catch (AuthenticationException e){
            // xac thuc khong thanh cong tra ve loi hoac thong bao
            return ResponseEntity.badRequest().body("Ten dang nhap hoac mat khau khong chinh xac");
        }
        return ResponseEntity.badRequest().body("Xac thuc khong thanh cong");
    }
}
