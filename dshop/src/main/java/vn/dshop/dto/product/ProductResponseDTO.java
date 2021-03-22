package vn.dshop.dto.product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO extends ProductDTO{
    private double newPrice;
}