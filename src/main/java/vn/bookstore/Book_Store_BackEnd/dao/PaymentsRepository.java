package vn.bookstore.Book_Store_BackEnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import vn.bookstore.Book_Store_BackEnd.entity.Payments;
@RepositoryRestResource (path = "payments")
public interface PaymentsRepository extends JpaRepository<Payments, Integer> {
}
