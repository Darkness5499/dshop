package vn.dshop.repository;

import vn.dshop.entity.Category;
import vn.dshop.entity.Product;
import vn.dshop.entity.User;

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts(int position);
    void save(Product p);
    void delete(Product p);
    void update(Product p);
    Product getProductById(int id);
    List<Product> getProductsByCategory(int categoryId);
    List<Product> getProductsByName(String name);
    List<Product> getProductByCategoryName(String categoryName);

}
