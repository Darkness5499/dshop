package vn.dshop.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.dshop.dto.user.MessageDTO;

@RestController
@RequestMapping(value = "admin/categories")
public class AdCategoryController {
    @PostMapping(value = "/save")
    public ResponseEntity<MessageDTO> save(){
        return null;
    }
    @PutMapping(value = "/update")
    public ResponseEntity<MessageDTO> update(){
        return null;
    }

}
