package vn.bookstore.Book_Store_BackEnd.services;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import vn.bookstore.Book_Store_BackEnd.dao.UserRepository;
import vn.bookstore.Book_Store_BackEnd.entity.ErrorResponse;
import vn.bookstore.Book_Store_BackEnd.entity.User;

@Service

public class AccountService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private EmailService emailService;
    public AccountService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;

    }
    public ResponseEntity<?> registryUser(User user) {
        // kiem tra ten dang nhap da ton tai chua
        if(userRepository.existsByUserName(user.getUserName())) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Ten dang nhap da ton tai"));
        }
        // kiems tra email
        if(userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Email da ton tai"));
        }
        // ma hoa mat khau
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        // gan va gui thong tin kich hoat
        user.setActivateCode(createActivateCode());
        user.setActivated(false);
        // luu nguoi du ng vao db
        User userRegistrySuccess = userRepository.save(user);
        // gui email cho nguoi dung de kich hoat
        guiEmailKichHoat(user.getEmail(),user.getActivateCode());
        return ResponseEntity.ok("Dang ky thanh cong");
    }
    private String createActivateCode(){
        return UUID.randomUUID().toString();
    }
    private void guiEmailKichHoat(String email,String activateCode){
        String subject = "Kich hoat tai khoan cua ban tai WebBanSach";
        String text = "vui long su dung ma sau de kich hoat cho tai khoan <" + email  +">: <html><body><br/> <h1>" + activateCode +"</h1></body></html>";
        text+= "<br/> click vao duong link de kich hoat tai khoan";
        String url = "http://localhost:3000/kich-hoat/" +email +"/" +activateCode;
        text+= "<br/> <a href="+ url + ">"+ url +"</a>";
        emailService.sendEmail("phamvansy204@gmail.com", email, subject, text);
    }
    public ResponseEntity<?> activateAccount(String email, String activateCode) {
        User user = userRepository.findByEmail(email);
        if(user == null){
            return ResponseEntity.badRequest().body(new ErrorResponse("User not found"));
        }
        if(user.isActivated()){
            return ResponseEntity.badRequest().body(new ErrorResponse("User is already activated"));
        }
        if(activateCode.equals(user.getActivateCode())){
            user.setActivated(true);
            userRepository.save(user);
            return ResponseEntity.ok("Kich hoat tai khoan thanh cong");
        }else{
            return ResponseEntity.badRequest().body(new ErrorResponse("ma kich hoat khong chinh xac"));
        }
    }
}
