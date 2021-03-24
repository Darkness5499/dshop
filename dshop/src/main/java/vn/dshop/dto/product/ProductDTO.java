package vn.dshop.dto.product;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProductDTO {
    @NotBlank(message = "error.blank")
    private String name;
    private double price;
    private String content;
    private int discount;
    private int quantity;
    private String created;
    private int categoryid;

}
