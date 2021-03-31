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
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    @PostMapping(value = "save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MessageDTO> save1(@ModelAttribute @Valid ProductDTO body, Locale locale) throws ParseException {
        MessageDTO response = new MessageDTO();
        ProductTransform productTransform = new ProductTransform(dateFormat);
        Category category = categoryService.getCategoryById(body.getCategoryid());
        try{
            if(category!=null){
                Product product = productTransform.apply(body);
                product.setCategory(category);
                productService.save(product, body.getImages());
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
        Product product = this.productService.getProductById(productid);
        ProductTransform transform = new ProductTransform(dateFormat);
        MessageDTO response = new MessageDTO();
        if(product!=null){
            product = transform.apply(body);
            productService.update(product);
            response.setText(messageSource.getMessage("success.update",null,locale));
            return ResponseEntity.ok(response);
        } else {
            response.setText(messageSource.getMessage("error.update",null,locale));
            return ResponseEntity.badRequest().body(response);
        }

    }
    @DeleteMapping(value = "delete/{productid}")
    public ResponseEntity<MessageDTO> delete(@PathVariable(name = "productid") int productid, Locale locale){
        Product product = this.productService.getProductById(productid);
        MessageDTO response = new MessageDTO();
        if(product!=null){
            productService.delete(product.getProductId());
            response.setText(messageSource.getMessage("success.delete",null,locale));
            return ResponseEntity.ok(response);
        }else {
            response.setText(messageSource.getMessage("error.delete",null,locale));
            return ResponseEntity.badRequest().body(response);
        }
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
