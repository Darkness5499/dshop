package vn.dshop.controller;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.dshop.dto.user.AuthenticationRequestDTO;
import vn.dshop.dto.user.ChangePasswordDTO;
import vn.dshop.dto.user.CreateUserDTO;
import vn.dshop.dto.user.JWTResponseDTO;
import vn.dshop.dto.user.MessageDTO;
import vn.dshop.dto.user.UserDTO;
import vn.dshop.jwt.JWTTokenComponent;
import vn.dshop.jwt.JWTUserDetailsService;
import vn.dshop.entity.User;
import vn.dshop.service.UserService;
import vn.dshop.transform.UserTransform;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private MessageSource messageSource;
    private DateFormat dateFormat;
    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JWTTokenComponent jwtTokenComponent;
    private JWTUserDetailsService jwtUserDetailsService;

    @Autowired
    public AccountController(DateFormat dateFormat, UserService userService, BCryptPasswordEncoder passwordEncoder,
                             MessageSource messageSource, AuthenticationManager authenticationManager,
                             JWTTokenComponent jwtTokenComponent, JWTUserDetailsService jwtUserDetailsService) {
        this.messageSource = messageSource;
        this.dateFormat = dateFormat;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenComponent = jwtTokenComponent;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO dto) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(dto.getUsername());
        String token = jwtTokenComponent.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponseDTO(token));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDTO body) throws ParseException {
        UserTransform transform = new UserTransform(dateFormat);
        User user = transform.apply(body);
        encryptPassword(user);
        userService.save(user);
        return ResponseEntity.ok(transform.apply(user));
    }

    @PutMapping("/password")
    public ResponseEntity<MessageDTO> changePassword(@RequestBody ChangePasswordDTO body, Locale locale) {
        User user = userService.findByUsername(body.getUsername());
        MessageDTO response = new MessageDTO();
        if (passwordEncoder.matches(body.getCurrentPassword(), user.getPassword())) {
            user.setPassword(body.getNewPassword());
            encryptPassword(user);
            userService.save(user);
            response.setText(messageSource.getMessage("success.passwordchanged", null, locale));
            return ResponseEntity.ok(response);
        } else {
            response.setText(messageSource.getMessage("error.currentpassword", null, locale));
            return ResponseEntity.badRequest().body(response);
        }
    }

    private void encryptPassword(User user) {
        String rawPassword = user.getPassword();
        if (rawPassword != null) {
            user.setPassword(passwordEncoder.encode(rawPassword));
        }
    }
}
