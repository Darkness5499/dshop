package vn.dshop.dto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class ProductDTO {

    private String name;
    private double price;
    private String content;
    private int discount;
    private int quantity;
    private String created;
    private int categoryid;
    private double newPrice;

}
