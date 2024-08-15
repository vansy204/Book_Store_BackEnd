package vn.bookstore.Book_Store_BackEnd.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.bookstore.Book_Store_BackEnd.entity.User;
import vn.bookstore.Book_Store_BackEnd.services.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @CrossOrigin(origins = "http://localhost:3000") // cho phep truy cap
    @PostMapping("/dang-ky")
    public ResponseEntity <?> dangKyNguoiDung( @Validated @RequestBody  User user){
        ResponseEntity<?> response = accountService.registryUser(user);
        return response;
    }
}
