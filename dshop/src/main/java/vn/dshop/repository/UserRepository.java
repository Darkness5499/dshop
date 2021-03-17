package vn.dshop.repository;


import vn.dshop.entity.User;

import java.util.List;

public interface UserRepository {
    User findByUsername(String username);

    List<User> getAllUsers();

    void insert(User u);

    void update(User u);

}
