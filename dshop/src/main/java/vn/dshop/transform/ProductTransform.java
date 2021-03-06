package vn.dshop.transform;
import vn.dshop.dto.product.ProductDTO;
import vn.dshop.dto.product.ProductResponseDTO;
import vn.dshop.entity.Product;
import java.text.DateFormat;
import java.text.ParseException;


public class ProductTransform {
    private DateFormat dateFormat;
    public ProductTransform(DateFormat dateFormat) {
        this.dateFormat = dateFormat;

    }
    public Product apply(ProductDTO dto) throws ParseException {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setContent(dto.getContent());
        product.setDiscount(dto.getDiscount());
        product.setQuantity(dto.getQuantity());
        product.setCreated(dateFormat.parse(dto.getCreated()));
        return product;
    }
    public ProductResponseDTO apply(Product product){
        ProductResponseDTO dto = new ProductResponseDTO();
        double newprice = product.getPrice()*(100-product.getDiscount());
        dto.setProductId(product.getProductId());
        dto.setCategoryid(product.getCategory().getCategoryId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setContent(product.getContent());
        dto.setDiscount(product.getDiscount());
        dto.setQuantity(product.getQuantity());
        dto.setNewPrice(newprice);
        if(product.getCreated()!=null){
            dto.setCreated(dateFormat.format(product.getCreated()));
        }
        return dto;
    }
}
