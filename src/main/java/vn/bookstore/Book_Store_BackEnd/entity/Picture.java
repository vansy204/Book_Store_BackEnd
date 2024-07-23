package vn.bookstore.Book_Store_BackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private int pictureId;
    @Column(name = "picture_name", length = 256, columnDefinition = "text")
    private String pictureName;
    @Column(name = "is_icon")
    private boolean isIcon;
    @Column(name = "picture_link")
    private String pictureLink;
    @Column(name = "picture_data")
    @Lob
    private String pictureData;
    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "book_id",nullable = false)
    private Book book;
}
