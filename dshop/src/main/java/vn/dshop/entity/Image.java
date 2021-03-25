package vn.dshop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "image")
public class Image {
    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "content_type")
    private String contentType;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
