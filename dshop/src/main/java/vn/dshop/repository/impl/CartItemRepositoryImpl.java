package vn.dshop.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.dshop.entity.CartItem;
import vn.dshop.repository.CartItemRepository;
@Repository
public class CartItemRepositoryImpl implements CartItemRepository {
    private SessionFactory sessionFactory;
    @Autowired
    public CartItemRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addToCart(CartItem cartItem) {
        Session session = this.sessionFactory.getCurrentSession();

    }

    @Override
    public void deleteCartItem(CartItem cartItem) {

    }
}
