package vn.dshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.dshop.entity.Product;
import vn.dshop.repository.ProductRepository;
import vn.dshop.service.ProductService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.getAllProducts();
    }

    @Override
    @Transactional
    public void save(Product p) {
        this.productRepository.save(p);
    }

    @Override
    @Transactional
    public void delete(int id) {
        this.productRepository.delete(id);
    }

    @Override
    public Product getProductById(int id) {
        return this.productRepository.getProductById(id);
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId) {
        return this.productRepository.getProductsByCategory(categoryId);
    }

    @Override
    public List<Product> getProductByCategoryName(String categoryName) {
        return this.productRepository.getProductByCategoryName(categoryName);
    }
}
