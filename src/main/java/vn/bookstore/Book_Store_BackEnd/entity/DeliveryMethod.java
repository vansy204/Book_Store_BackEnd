package vn.bookstore.Book_Store_BackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "delivery_method")
public class DeliveryMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_method_id")
    private int deliveryMethodId;
    @Column(name = "delivery_method_name")
    private String deliveryMethodName;
    @Column(name = "description")
    private String description;
    @Column(name = "delivery_cost")
    private double deliveryCost;
    @OneToMany(
            mappedBy = "deliveryMethod",
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
