package vn.dshop.dto.user;

import lombok.Getter;
import lombok.Setter;
import vn.dshop.entity.Role;

import java.util.List;


@Getter
@Setter
public class UserDTO {
	private String username;
	private String email;
	private String firstname;
	private String lastname;
	private String address;
	private List<Role> roles;
	private boolean active;


}
