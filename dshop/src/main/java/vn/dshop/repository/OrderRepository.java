package vn.dshop.repository;

import vn.dshop.entity.User;
import vn.dshop.entity.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> getAllOrderByUser(User user);
}
