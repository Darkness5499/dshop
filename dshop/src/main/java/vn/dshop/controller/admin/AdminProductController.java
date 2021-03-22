package vn.dshop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.dshop.dto.ProductDTO;
import vn.dshop.entity.Category;
import vn.dshop.entity.Product;
import vn.dshop.service.CategoryService;
import vn.dshop.service.ProductService;
import vn.dshop.transform.ProductTransform;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;
@RestController
@RequestMapping(value = "admin/products")
public class AdminProductController {
    private ProductService productService;
    private CategoryService categoryService;
    private DateFormat dateFormat;
    private MessageSource messageSource;
    @Autowired
    public AdminProductController(ProductService productService, CategoryService categoryService, DateFormat dateFormat, MessageSource messageSource) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.dateFormat = dateFormat;
        this.messageSource = messageSource;
    }
    @PostMapping(value = "/save")
    public ResponseEntity save(@ModelAttribute ProductDTO body, @RequestParam List<MultipartFile> images)
            throws ParseException {
        try{
            ProductTransform productTransform = new ProductTransform(dateFormat);
            Category category = categoryService.getCategoryById(body.getCategoryid());
            if(category==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new String("No category found"));
            }
            Product product = productTransform.apply(body);
            product.setCategory(category);
            productService.save(product, images);
            return ResponseEntity.ok(new String("Thêm sản phẩm thành công"));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new String("Dữ liệu không đúng"));
        }
    }

}
