package vn.bookstore.Book_Store_BackEnd.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import vn.bookstore.Book_Store_BackEnd.entity.User;

public interface UserService extends UserDetailsService {
    public User findByUserName(String userName);
}
