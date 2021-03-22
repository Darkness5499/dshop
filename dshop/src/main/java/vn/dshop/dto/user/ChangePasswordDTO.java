package vn.dshop.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ChangePasswordDTO {
	@NotBlank(message = "username.not.valid")
	private String username;
	private String currentPassword;
	private String newPassword;
}
