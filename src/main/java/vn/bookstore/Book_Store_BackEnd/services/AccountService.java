package vn.bookstore.Book_Store_BackEnd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.bookstore.Book_Store_BackEnd.dao.UserRepository;
import vn.bookstore.Book_Store_BackEnd.entity.ErrorResponse;
import vn.bookstore.Book_Store_BackEnd.entity.User;

@Service
public class AccountService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> registryUser(User user) {
        // kiem tra ten dang nhap da ton tai chua
        if(userRepository.existsByUserName(user.getUserName())) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Ten dang nhap da ton tai"));
        }
        // kiems tra email
        if(userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Email da ton tai"));
        }
        // luu nguoi du ng vao db
        User userRegistrySuccess = userRepository.save(user);
        return ResponseEntity.ok("Dang ky thanh cong");
    }


}
