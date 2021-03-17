package vn.dshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;
    private String name;
    private double price;
    private String content;
    private int discount;
    private int quantity;
    private Date created;
    private int view;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<ProductComment> productComments;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<CartItem> cartItems;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<Image> images;


}
