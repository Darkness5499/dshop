package vn.dshop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.dshop.dto.category.CategoryDTO;
import vn.dshop.dto.user.MessageDTO;
import vn.dshop.entity.Category;
import vn.dshop.service.CategoryService;
import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping(value = "admin/categories")
public class AdCategoryController {
    private CategoryService categoryService;
    private MessageSource messageSource;
    @Autowired
    public AdCategoryController(CategoryService categoryService, MessageSource messageSource) {
        this.categoryService = categoryService;
        this.messageSource = messageSource;
    }
    @PostMapping(value = "/save")
    public ResponseEntity<MessageDTO> save(@RequestBody @Valid CategoryDTO body, Locale locale){
        Category category = categoryService.getCategoryByName(body.getName());
        MessageDTO response = new MessageDTO();
        System.out.println(category);
        if(category==null){
            Category category1 = new Category();
            category1.setName(body.getName());
            categoryService.save(category1);
            response.setText(messageSource.getMessage("success.upload", null, locale));
            return ResponseEntity.ok(response);
        } else {
            response.setText(messageSource.getMessage("error.upload", null, locale));
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PutMapping(value = "/update")
    public ResponseEntity<MessageDTO> update(){
        return null;
    }

}
