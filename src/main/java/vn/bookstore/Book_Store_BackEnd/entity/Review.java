package vn.bookstore.Book_Store_BackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private long reviewId;
    @Column(name = "rate_point")
    private float ratePoint;
    @Column(name = "comment")
    private String comment;
    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH,
            }
    )
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book book;
    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH,
            }
    )
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private User user;
}
