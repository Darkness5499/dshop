package vn.dshop.transform;

import org.springframework.beans.factory.annotation.Autowired;
import vn.dshop.dto.ProductDTO;
import vn.dshop.entity.Product;
import vn.dshop.service.ProductService;

import java.text.DateFormat;
import java.text.ParseException;

public class ProductTransform {
    private DateFormat dateFormat;
    @Autowired
    private ProductService productService;
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
    public ProductDTO apply(Product product){
        ProductDTO dto = new ProductDTO();
        double newprice = product.getPrice()*product.getDiscount()/100;
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
