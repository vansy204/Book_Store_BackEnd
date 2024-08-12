package vn.bookstore.Book_Store_BackEnd.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.bookstore.Book_Store_BackEnd.entity.User;
import vn.bookstore.Book_Store_BackEnd.services.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private AccountService accountService;
    @PostMapping("/dang-ky")
    public ResponseEntity <?> dangKyNguoiDung( @Validated @RequestBody  User user){
        ResponseEntity<?> response = accountService.registryUser(user);
        return response;
    }
}
