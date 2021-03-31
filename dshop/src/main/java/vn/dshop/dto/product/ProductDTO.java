package vn.dshop.dto.product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

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
    private List<MultipartFile> images;

}
