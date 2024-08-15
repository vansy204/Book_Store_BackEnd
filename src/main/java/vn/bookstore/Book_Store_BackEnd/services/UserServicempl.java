package vn.bookstore.Book_Store_BackEnd.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import vn.bookstore.Book_Store_BackEnd.entity.User;

public class UserServicempl implements UserService{
    @Override
    public User findByUserName(String userName) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
