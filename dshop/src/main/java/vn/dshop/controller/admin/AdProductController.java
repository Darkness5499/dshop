package vn.dshop.controller.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.dshop.dto.product.ProductDTO;
import vn.dshop.dto.user.MessageDTO;
import vn.dshop.entity.Category;
import vn.dshop.entity.Product;
import vn.dshop.service.CategoryService;
import vn.dshop.service.ProductService;
import vn.dshop.transform.ProductTransform;
import java.util.Locale;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;
@RestController
//@Secured("ROLE_ADMIN")
@RequestMapping(value = "admin/products")
public class AdProductController {
    private ProductService productService;
    private CategoryService categoryService;
    private DateFormat dateFormat;
    private MessageSource messageSource;
    @Autowired
    public AdProductController(ProductService productService, CategoryService categoryService, DateFormat dateFormat, MessageSource messageSource) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.dateFormat = dateFormat;
        this.messageSource = messageSource;
    }
//    @PostMapping(value = "/save")
//    public ResponseEntity save(@ModelAttribute ProductDTO body, @RequestParam List<MultipartFile> images)
//            throws ParseException {
//        for(MultipartFile file:images){
//            System.out.println(file.getOriginalFilename());
//        }
//
//        try{
//            ProductTransform productTransform = new ProductTransform(dateFormat);
//            Category category = categoryService.getCategoryById(body.getCategoryid());
//            if(category==null){
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body(new String("No category found"));
//            }
//            Product product = productTransform.apply(body);
//            product.setCategory(category);
//            productService.save(product, images);
//            return ResponseEntity.ok(new String("Thêm sản phẩm thành công"));
//        } catch (Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new String("Dữ liệu không đúng"));
//        }
//    }
    @PostMapping(value = "save")
    public ResponseEntity<MessageDTO> save1(@ModelAttribute @Valid ProductDTO body, @RequestParam List<MultipartFile> images, Locale locale) throws ParseException {
        MessageDTO response = new MessageDTO();
        ProductTransform productTransform = new ProductTransform(dateFormat);
        Category category = categoryService.getCategoryById(body.getCategoryid());
        try{
            if(category!=null){
                Product product = productTransform.apply(body);
                product.setCategory(category);
                productService.save(product, images);
                response.setText(messageSource.getMessage("success.upload",null,locale));
                return ResponseEntity.ok(response);
            } else {
                response.setText(messageSource.getMessage("error.category",null,locale));
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e){
            response.setText(messageSource.getMessage("error.upload", null, locale));
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PutMapping(value = "/update/{productid}")
    public ResponseEntity<MessageDTO> update(@RequestBody @Valid ProductDTO body,@PathVariable(name = "productid") int productid, Locale locale) throws ParseException {
        return null;
    }
    @DeleteMapping(value = "delete/{productid}")
    public ResponseEntity<MessageDTO> delete(){
        return null;
    }
    @DeleteMapping(value = "delete-comment/{commentid}")
    public ResponseEntity<MessageDTO> deleteComment(){
        return null;
    }
    @PostMapping(value = "/comment")
    public void comment(){

    }
    @PutMapping(value = "/update-comment")
    public void updateComment(){
    }

}
