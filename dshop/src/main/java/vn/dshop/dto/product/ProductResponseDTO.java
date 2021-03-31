package vn.dshop.dto.product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponseDTO{
    private int productId;
    private String name;
    private double price;
    private String content;
    private int discount;
    private int quantity;
    private String created;
    private double newPrice;
    private List<String> images;
    private int categoryid;


}