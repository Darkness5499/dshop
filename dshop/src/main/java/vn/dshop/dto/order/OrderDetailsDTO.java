package vn.dshop.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailsDTO {
    private int productId;
    private String productName;
    private int quantity;
    private double price;
}
