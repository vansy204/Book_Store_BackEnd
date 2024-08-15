package vn.bookstore.Book_Store_BackEnd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.bookstore.Book_Store_BackEnd.dao.RoleRepository;
import vn.bookstore.Book_Store_BackEnd.dao.UserRepository;
import vn.bookstore.Book_Store_BackEnd.entity.Role;
import vn.bookstore.Book_Store_BackEnd.entity.User;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServicempl implements UserService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Autowired
    public UserServicempl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        org.springframework.security.core.userdetails.User user1 = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),rolesToAuthorities(user.getRoles()));
        return user1;
    }
    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }
}
