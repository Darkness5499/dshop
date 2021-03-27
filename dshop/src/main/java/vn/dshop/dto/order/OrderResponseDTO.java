package vn.dshop.dto.order;

import lombok.Getter;
import lombok.Setter;
import vn.dshop.entity.Status;

import java.util.List;
@Getter
@Setter
public class OrderResponseDTO extends OrderDTO{
    private List<OrderItemsDTO> dtos;
    private Status status;
}
