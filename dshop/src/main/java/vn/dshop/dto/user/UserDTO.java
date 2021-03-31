package vn.dshop.dto.user;

import lombok.Getter;
import lombok.Setter;
import vn.dshop.entity.Role;

import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
public class UserDTO {
	@Size(min = 6, max = 32, message = "Name must be between 2 and 32 characters long")
	private String username;
	private String email;
	private String firstname;
	private String lastname;
	private String address;
	private List<Role> roles;
	private boolean active;


}
