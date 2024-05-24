package org.happybaras.taller3.controllers;

import jakarta.validation.Valid;
import org.happybaras.taller3.domain.dtos.GeneralResponse;
import org.happybaras.taller3.domain.dtos.UserLoginDTO;
import org.happybaras.taller3.domain.dtos.UserRegisterDTO;
import org.happybaras.taller3.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    //@Autowired
    //private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<GeneralResponse> login(@ModelAttribute @Valid UserLoginDTO info) {
        // Token token = userService.registerToken(user);
        // return new ResponseEntity<>(new TokenDTO(token), HttpStatus.OK);
        return GeneralResponse.builder().getResponse(); // Esto hay que quitarlo después
    }
}
