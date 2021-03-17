package vn.dshop.service;

import vn.dshop.entity.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    List<User> getAllUsers();

    void save(User u);
}
