package vn.dshop.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class OrderDTO {
    private double totalPrice;
    private String date;
    private List<OrderItemsDTO> orderItemsDTOS;
    private String address;
}
