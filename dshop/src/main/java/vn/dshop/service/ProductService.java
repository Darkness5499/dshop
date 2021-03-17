package vn.dshop.service;

import vn.dshop.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    void save(Product p);
    void delete(int id);
    Product getProductById(int id);
    List<Product> getProductsByCategory(int categoryId);
    List<Product> getProductByCategoryName(String categoryName);
}
