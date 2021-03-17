package vn.dshop.repository;

import vn.dshop.entity.Category;
import vn.dshop.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();
    void save(Product p);
    void delete(int id);
    Product getProductById(int id);
    List<Product> getProductsByCategory(int categoryId);
    List<Product> getProductsByName(String name);
    List<Product> getProductByCategoryName(String categoryName);
}
