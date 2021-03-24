package vn.dshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import vn.dshop.dto.product.ProductResponseDTO;
import vn.dshop.entity.Product;
import vn.dshop.service.CategoryService;
import vn.dshop.service.ProductService;
import vn.dshop.transform.ProductTransform;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    public ProductService productService;
    private CategoryService categoryService;
    private DateFormat dateFormat;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, DateFormat dateFormat) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.dateFormat = dateFormat;
    }
    @GetMapping
    public List<ProductResponseDTO> listProduct(){
        ProductTransform productTransform = new ProductTransform(dateFormat);
        List<Product> products = productService.getAllProducts();
        List<ProductResponseDTO> response = new ArrayList<>();
        for(Product p:products){
            ProductResponseDTO productResponseDTO = productTransform.apply(p);
            response.add(productResponseDTO);
        }
        return response;
    }
    @GetMapping(value = "/{category}")
    public List<Product> getProductsByCategory(@PathVariable(name = "category") String category){
        return this.productService.getProductByCategoryName(category);
    }


}
