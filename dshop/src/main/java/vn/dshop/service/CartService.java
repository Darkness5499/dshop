package vn.dshop.service;
import vn.dshop.entity.Cart;
import vn.dshop.entity.CartItem;

public interface CartService {
    void addToCart(CartItem cartItem, Cart cart);
    void deleteCartItem(CartItem cartItem);
    void save(Cart cart);
    void emptyCart(Cart cart);
}
