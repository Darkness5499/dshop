package vn.dshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.dshop.entity.Cart;
import vn.dshop.entity.User;
import vn.dshop.repository.UserRepository;
import vn.dshop.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void save(User u) {
        if (u.getId() > 0) {
            userRepository.update(u);
        } else {
            Cart cart = new Cart();
            cart.setTotal(0);
            cart.setUser(u);
            userRepository.insert(u);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

}
