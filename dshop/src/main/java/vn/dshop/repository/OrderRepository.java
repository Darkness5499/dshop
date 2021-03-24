package vn.dshop.repository;

import vn.dshop.entity.Status;
import vn.dshop.entity.User;
import vn.dshop.entity.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> getAllOrderByUser(String username);
    List<Order> getAllOrderByStatus(int status);
    void updateStatus(int oid, Status status);
    Order getOrderById(int oid);
    void save(Order order);
}
