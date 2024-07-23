package vn.bookstore.Book_Store_BackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "payments")
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payments_id")
    private int paymentsId;
    @Column(name = "payments_name")
    private String paymentsName;
    @Column(name = "desciption")
    private String description;
    @Column(name = "payments_cost")
    private double paymentsCost;
    @OneToMany(
            mappedBy = "payments",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            }
    )
    private List<Order> listOrder;
}
