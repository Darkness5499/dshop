package vn.dshop.service;

import org.springframework.web.multipart.MultipartFile;
import vn.dshop.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    void save(Product p, List<MultipartFile> files);
    void delete(int id);
    Product getProductById(int id);
    List<Product> getProductsByCategory(int categoryId);
    List<Product> getProductByCategoryName(String categoryName);
}
