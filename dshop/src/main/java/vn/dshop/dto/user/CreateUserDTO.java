package vn.dshop.dto.user;

import javax.validation.constraints.Size;

public class CreateUserDTO extends UserDTO {
	@Size(min = 6, max = 32, message = "password must be between 2 and 32 characters long")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
