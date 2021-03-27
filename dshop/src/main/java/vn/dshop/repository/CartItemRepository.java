package vn.dshop.repository;
import vn.dshop.entity.CartItem;
public interface CartItemRepository {
    void save(CartItem cartItem);
    void delete(CartItem cartItem);
}
