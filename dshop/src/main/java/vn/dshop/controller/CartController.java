package vn.dshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import vn.dshop.dto.user.MessageDTO;
import vn.dshop.entity.User;
import vn.dshop.service.UserService;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
    private UserService userService;

    @Autowired
    public CartController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity currentCart(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            User user = this.userService.findByUsername(username);
            return ResponseEntity.ok(user.getCart());
        } else {
            String username = principal.toString();
            return ResponseEntity.badRequest().body(new String("Please Login!"));
        }
    }
    @GetMapping(value = "/s")
    public Object test(@AuthenticationPrincipal Object o){
        return o;
    }
    @Secured("ROLE_USER")
    @PostMapping(value = "/delete-items/{id}")
    public void delete(@PathVariable int id){

    }
    @Secured("ROLE_USER")
    @PostMapping(value = "/add-items")
    public ResponseEntity<MessageDTO> save(){
        return null;
    }

}
