package vn.dshop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.dshop.dto.ProductDTO;
import vn.dshop.entity.Category;
import vn.dshop.entity.Image;
import vn.dshop.entity.Product;
import vn.dshop.service.CategoryService;
import vn.dshop.service.ImageService;
import vn.dshop.service.ProductService;
import vn.dshop.transform.ProductTransform;

import javax.annotation.Resource;
import java.io.File;
import java.text.DateFormat;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "admin/products")
public class AdminProductController {
    public static Date dateServer(){
        Date date = new Date();
        return date;
    }
    private ProductService productService;
    private CategoryService categoryService;
    private DateFormat dateFormat;
    private ImageService imageService;

    @Autowired
    public AdminProductController(ProductService productService, CategoryService categoryService, DateFormat dateFormat, ImageService imageService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.dateFormat = dateFormat;
        this.imageService = imageService;

    }

    @PostMapping(value = "/save")
    public ResponseEntity<ProductDTO> save(@ModelAttribute ProductDTO body)
            throws ParseException {
        ProductTransform productTransform = new ProductTransform(dateFormat);
        Category category = categoryService.getCategoryById(body.getCategoryid());
        Product product = productTransform.apply(body);
        product.setCategory(category);
        productService.save(product);
        for(MultipartFile image:body.getImages()){
            Image imageEntity = new Image();
            imageEntity.setImageLink(image.getOriginalFilename());
            System.out.println(image.getOriginalFilename());
            imageEntity.setProduct(product);
            imageService.save(imageEntity,image);
        }
        return ResponseEntity.ok(productTransform.apply(product));
    }
}
