package vn.dshop.repository;

import vn.dshop.entity.Cart;
import vn.dshop.entity.CartItem;
public interface CartItemRepository {
    void addToCart(CartItem cartItem, Cart cart);
    void deleteCartItem(CartItem cartItem);
}
