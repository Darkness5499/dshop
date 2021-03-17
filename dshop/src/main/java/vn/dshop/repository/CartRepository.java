package vn.dshop.repository;

import vn.dshop.entity.Cart;

public interface CartRepository {
    void save(Cart cart);
    void emptyCart(Cart cart);
}
