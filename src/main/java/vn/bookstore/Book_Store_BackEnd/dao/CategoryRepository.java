package vn.bookstore.Book_Store_BackEnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import vn.bookstore.Book_Store_BackEnd.entity.Category;
@RepositoryRestResource (path = "category")
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
