package vn.dshop.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import vn.dshop.entity.Role;
import vn.dshop.entity.User;
import vn.dshop.transform.RoleTransform;

public final class JWTUserDetailsFactory {

    private JWTUserDetailsFactory() {
    }

    public static JWTUserDetails create(User user) {
        return new JWTUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                mapToGrantedAuthorities(user.getRoles()),
                user.isActive()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(int rolesNumber) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        RoleTransform transform = new RoleTransform();
        List<Role> roles = transform.apply(rolesNumber);
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        }
        return authorities;
    }
}