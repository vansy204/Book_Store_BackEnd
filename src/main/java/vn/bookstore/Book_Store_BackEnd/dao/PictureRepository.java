package vn.bookstore.Book_Store_BackEnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bookstore.Book_Store_BackEnd.entity.Picture;
@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {
}
