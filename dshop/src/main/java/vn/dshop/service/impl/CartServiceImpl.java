package vn.dshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.dshop.entity.Cart;
import vn.dshop.entity.CartItem;
import vn.dshop.repository.CartItemRepository;
import vn.dshop.repository.CartRepository;
import vn.dshop.service.CartService;

import javax.transaction.Transactional;
@Service
public class CartServiceImpl implements CartService {
    private CartItemRepository cartItemRepository;
    private CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartItemRepository cartItemRepository, CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public void addToCart(CartItem cartItem) {
        this.cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public void deleteCartItem(CartItem cartItem) {
        this.cartItemRepository.delete(cartItem);
    }


    @Override
    @Transactional
    public void emptyCart(Cart cart) {
        for(CartItem cartItem: cart.getCartItems()){
            this.cartItemRepository.delete(cartItem);
        }
        cart.setTotal(0);
        this.cartRepository.update(cart);
    }
}
