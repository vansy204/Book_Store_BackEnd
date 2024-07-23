package vn.bookstore.Book_Store_BackEnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bookstore.Book_Store_BackEnd.entity.Order;
import vn.bookstore.Book_Store_BackEnd.entity.OrderDetail;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
