package vn.dshop.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.dshop.entity.OrderDetails;
import vn.dshop.repository.OrderDetailsRepository;
@Repository
public class OrderDetailsRepositoryImpl implements OrderDetailsRepository {
    private SessionFactory sessionFactory;
    @Autowired
    public OrderDetailsRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(OrderDetails orderDetails) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(orderDetails);
    }
}
