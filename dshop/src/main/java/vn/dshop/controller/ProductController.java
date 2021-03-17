package vn.dshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.dshop.entity.Product;
import vn.dshop.service.CategoryService;
import vn.dshop.service.ProductService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> image() throws IOException {
        final ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(
                "C:\\Users\\xngok\\Desktop\\dshop\\dshop\\src\\main\\resources\\media\\uploads\\a.jpeg"
        )));
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(inputStream.contentLength())
                .body(inputStream);

    }
}
