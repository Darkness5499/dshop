package vn.dshop.service;
import vn.dshop.entity.Cart;
import vn.dshop.entity.CartItem;

public interface CartService {
    void addToCart(CartItem cartItem);
    void deleteCartItem(CartItem cartItem);
    void emptyCart(Cart cart);
}
