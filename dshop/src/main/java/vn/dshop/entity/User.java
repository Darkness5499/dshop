package vn.dshop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user")
public class  User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    private int roles;
    private boolean active;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @OneToMany(mappedBy = "user")
    private List<ProductComment> productComments;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders;
}
