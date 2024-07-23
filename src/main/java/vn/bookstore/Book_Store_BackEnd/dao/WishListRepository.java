package vn.bookstore.Book_Store_BackEnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bookstore.Book_Store_BackEnd.entity.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {

}
