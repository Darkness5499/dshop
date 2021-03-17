package vn.dshop.transform;

import vn.dshop.dto.user.CreateUserDTO;
import vn.dshop.dto.user.UserDTO;
import vn.dshop.entity.User;

import java.text.DateFormat;
import java.text.ParseException;


public class UserTransform {

    private DateFormat dateFormat;
    private RoleTransform roleTransform;

    public UserTransform(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
        roleTransform = new RoleTransform();
    }

    public User apply(CreateUserDTO dto) throws ParseException {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setActive(true);
        user.setAddress(dto.getAddress());
        user.setRoles(roleTransform.apply(dto.getRoles()));
        return user;
    }

    public UserDTO apply(User user) {
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setRoles(roleTransform.apply(user.getRoles()));
        dto.setAddress(user.getAddress());
        dto.setActive(user.isActive());
        return dto;
    }
}