package vn.dshop.repository;

import vn.dshop.entity.OrderItems;

public interface OrderDetailsRepository {
    void save(OrderItems orderItems);

}
