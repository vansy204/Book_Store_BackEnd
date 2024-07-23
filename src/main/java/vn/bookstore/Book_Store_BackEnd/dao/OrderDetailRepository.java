package vn.bookstore.Book_Store_BackEnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import vn.bookstore.Book_Store_BackEnd.entity.OrderDetail;
@RepositoryRestResource (path = "order-detail")
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
