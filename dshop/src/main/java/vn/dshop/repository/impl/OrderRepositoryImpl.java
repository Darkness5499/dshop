package vn.dshop.repository.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.dshop.entity.User;
import vn.dshop.entity.Order;
import vn.dshop.repository.OrderRepository;
import java.util.List;
@Repository
public class OrderRepositoryImpl implements OrderRepository{
    private SessionFactory sessionFactory;
    @Autowired
    public OrderRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Order> getAllOrderByUser(User user) {
        return null;
    }
}
