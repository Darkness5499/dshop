package vn.dshop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "order_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_id")
    private int productId;
    private int quantity;
    private double price;
    @Column(name = "product_name")
    private String productName;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
