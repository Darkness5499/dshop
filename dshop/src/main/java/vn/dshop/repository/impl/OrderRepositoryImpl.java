package vn.dshop.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.dshop.entity.Order;
import vn.dshop.entity.Status;
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
    public List<Order> getAllOrderByUser(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<Order> query = session.createQuery("select o from Order o where o.user.username =: username", Order.class);
        query.setParameter("username",username);
        return query.getResultList();
    }

    @Override
    public List<Order> getAllOrderByStatus(int status) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<Order> query = session.createQuery("select o from Order o where o.status =: status", Order.class);
        query.setParameter("status",status);
        return query.getResultList();
    }

    @Override
    public void updateStatus(int oid, Status status) {
        Session session = this.sessionFactory.getCurrentSession();
        Order order = getOrderById(oid);
        if(order!=null){
            order.setStatus(status);
        }
        session.persist(order);

    }

    @Override
    public Order getOrderById(int oid) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<Order> query = session.createQuery("select o from Order o where o.orderId =:oid",Order.class);
        query.setParameter("oid",oid);
        List<Order> orders= query.getResultList();
        Order order = orders.get(0);
        return order;
    }

    @Override
    public void save(Order order) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(order);
    }
}
