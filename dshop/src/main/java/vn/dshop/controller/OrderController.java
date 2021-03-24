package vn.dshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.dshop.dto.order.OrderDTO;
import vn.dshop.dto.order.OrderDetailsDTO;
import vn.dshop.dto.user.MessageDTO;
import vn.dshop.entity.Order;
import vn.dshop.entity.OrderDetails;
import vn.dshop.entity.User;
import vn.dshop.service.OrderService;
import vn.dshop.service.UserService;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private OrderService orderService;
    private UserService userService;
    private DateFormat dateFormat;
    private MessageSource messageSource;
    @Autowired
    public OrderController(OrderService orderService, UserService userService, DateFormat dateFormat,MessageSource messageSource) {
        this.orderService = orderService;
        this.userService = userService;
        this.dateFormat = dateFormat;
        this.messageSource = messageSource;
    }
    @PostMapping(value = "/save")
    public ResponseEntity<MessageDTO> save(@RequestBody OrderDTO body, Locale locale) throws ParseException {
        MessageDTO response = new MessageDTO();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        User user = userService.findByUsername(username);
        if(user!=null){
            Order order = new Order();
            List<OrderDetails> orderDetailsList = new ArrayList<>();
            order.setOrderdate(dateFormat.parse(body.getDate()));
            order.setOrderTotalPrice(body.getTotalPrice());
            order.setUser(user);
            for(OrderDetailsDTO detailsDTO:body.getOrderDetailsDTOS()){
                OrderDetails orderDetails1 = new OrderDetails();
                orderDetails1.setProductId(detailsDTO.getProductId());
                orderDetails1.setProductName(detailsDTO.getProductName());
                orderDetails1.setQuantity(detailsDTO.getQuantity());
                orderDetails1.setPrice(detailsDTO.getPrice());
                orderDetailsList.add(orderDetails1);
            }
            this.orderService.save(order,orderDetailsList);
            response.setText(messageSource.getMessage("success.order",null,locale));
            return ResponseEntity.ok(response);
        } else {
            response.setText(messageSource.getMessage("error.order",null,locale));
            return ResponseEntity.badRequest().body(response);
        }
    }
}
