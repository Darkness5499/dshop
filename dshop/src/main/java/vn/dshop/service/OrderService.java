package vn.dshop.service;

import vn.dshop.entity.Order;
import vn.dshop.entity.OrderDetails;
import vn.dshop.entity.Status;
import vn.dshop.entity.User;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrdersByUser(String username);
    List<Order> getAllOrdersByStatus(int status);
    void updateStatus(int oid, Status status);
    Order getOrderById(int oid);
    void save(Order order, List<OrderDetails> orderDetails);

}
