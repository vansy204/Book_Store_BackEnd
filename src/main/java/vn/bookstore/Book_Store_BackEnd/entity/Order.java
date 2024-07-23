package vn.bookstore.Book_Store_BackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "purchase_address",length = 512)
    private String purchaseAddress;
    @Column(name = "delivery_address",length = 512)
    private String deliveryAddress;
    @Column(name = "total_product_cost")
    private double totalProductCost;
    @Column(name = "delivery_cost")
    private double deliveryCost;
    @Column(name = "payments_cost")
    private double paymentsCost;
    @Column(name = "total_price")
    private double totalPrice;

    @OneToMany(
            mappedBy = "order",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<OrderDetail> listOrderDetail;
    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "payments_id")
    private Payments payments;
    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "delivery_method_id")
    private DeliveryMethod deliveryMethod;
}
