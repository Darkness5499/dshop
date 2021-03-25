package vn.dshop.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.dshop.entity.Cart;
import vn.dshop.entity.CartItem;
import vn.dshop.repository.CartItemRepository;

import javax.transaction.Transactional;

@Repository
public class CartItemRepositoryImpl implements CartItemRepository {
    private SessionFactory sessionFactory;
    @Autowired
    public CartItemRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addToCart(CartItem cartItem, Cart cart) {
        Session session = this.sessionFactory.getCurrentSession();
        cartItem.setCart(cart);
        session.persist(cartItem);
    }

    @Override
    public void deleteCartItem(CartItem cartItem) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(cartItem);
    }
}
