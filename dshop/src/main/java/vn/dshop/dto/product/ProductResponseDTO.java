package vn.dshop.dto.product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponseDTO extends ProductDTO{
    private double newPrice;
    private List<String> images;
}