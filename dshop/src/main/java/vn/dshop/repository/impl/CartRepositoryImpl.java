package vn.dshop.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.dshop.entity.Cart;
import vn.dshop.repository.CartRepository;

@Repository
public class CartRepositoryImpl implements CartRepository {
    private SessionFactory sessionFactory;
    @Autowired
    public CartRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Cart cart) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(cart);
    }

    @Override
    public void emptyCart(Cart cart) {
        //xoá toàn bộ sản phẩm trong giỏ hàng

    }

}
