package vn.dshop.repository;

import vn.dshop.entity.CartItem;
public interface CartItemRepository {
    void addToCart(CartItem cartItem);
    void deleteCartItem(CartItem cartItem);
}
