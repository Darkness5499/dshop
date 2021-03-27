package vn.dshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.dshop.entity.Order;
import vn.dshop.entity.OrderItems;
import vn.dshop.entity.Status;
import vn.dshop.repository.OrderDetailsRepository;
import vn.dshop.repository.OrderRepository;
import vn.dshop.service.OrderService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public List<Order> getAllOrdersByUser(String username) {
        return this.orderRepository.getAllOrderByUser(username);
    }

    @Override
    public List<Order> getAllOrdersByStatus(int status) {
        return this.orderRepository.getAllOrderByStatus(status);
    }

    @Override
    @Transactional
    public void updateStatus(int oid, Status status) {
        this.orderRepository.updateStatus(oid,status);
    }

    @Override
    public Order getOrderById(int oid) {
        return this.orderRepository.getOrderById(oid);
    }

    @Override
    @Transactional
    public void save(Order order, List<OrderItems> orderDetails) {
        this.orderRepository.save(order);
        for(OrderItems orderDetail:orderDetails){
            this.orderDetailsRepository.save(orderDetail);
        }
    }
}
