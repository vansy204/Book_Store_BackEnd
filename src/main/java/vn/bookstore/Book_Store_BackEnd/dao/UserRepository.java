package vn.bookstore.Book_Store_BackEnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import vn.bookstore.Book_Store_BackEnd.entity.User;

@RepositoryRestResource (path = "user")
public interface UserRepository extends JpaRepository<User, Integer> {
     boolean existsByUserName(String username);
     boolean existsByEmail(String email);
     public User findByEmail(String email);
     public User findByUserName(String username);
}
