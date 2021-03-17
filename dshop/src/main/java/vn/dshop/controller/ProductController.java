package vn.dshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.dshop.entity.Category;
import vn.dshop.entity.Product;
import vn.dshop.service.CategoryService;
import vn.dshop.service.ProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    public ProductService productService;
    private CategoryService categoryService;
    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Product> listProduct(){
        return productService.getAllProducts();
    }

    @GetMapping(value = "/{category}")
    public List<Product> getProductsByCategory(@PathVariable(name = "category") String category){
        return this.productService.getProductByCategoryName(category);
    }
}
