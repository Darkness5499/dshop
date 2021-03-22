package vn.dshop.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.dshop.dto.order.OrderDTO;
import vn.dshop.dto.user.MessageDTO;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class AdOrdersController {

    @GetMapping(value = "/{id}")
    public OrderDTO getOrder(){
        return null;
    }
    @PutMapping(value = "/update")
        public ResponseEntity<MessageDTO> update(){
        return null;
    }
    @GetMapping(value = "/list")
    public List<OrderDTO> list(){
        return null;
    }
}
