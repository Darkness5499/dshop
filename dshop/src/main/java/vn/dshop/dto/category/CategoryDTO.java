package vn.dshop.dto.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CategoryDTO {
    @NotBlank(message = "error.blank")
    private String name;
}
