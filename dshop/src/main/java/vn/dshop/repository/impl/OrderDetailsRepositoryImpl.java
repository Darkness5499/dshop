package vn.dshop.repository.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import vn.dshop.entity.OrderDetails;
import vn.dshop.repository.OrderDetailsRepository;

public class OrderDetailsRepositoryImpl implements OrderDetailsRepository {
    private SessionFactory sessionFactory;
    @Autowired
    public OrderDetailsRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(OrderDetails orderDetails) {

    }
}
