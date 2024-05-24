package org.happybaras.taller3.controllers;

import jakarta.validation.Valid;
import org.happybaras.taller3.domain.dtos.GeneralResponse;
import org.happybaras.taller3.domain.dtos.TokenDTO;
import org.happybaras.taller3.domain.dtos.UserLoginDTO;
import org.happybaras.taller3.domain.dtos.UserRegisterDTO;
import org.happybaras.taller3.domain.entities.Token;
import org.happybaras.taller3.domain.entities.User;
import org.happybaras.taller3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<GeneralResponse> login(@ModelAttribute @Valid UserLoginDTO info) {

        Token token = userService.registerToken(info);
        return GeneralResponse.builder().status(HttpStatus.OK).data(token).getResponse();
    }

    @PostMapping("/register")
    public ResponseEntity<GeneralResponse> register(@ModelAttribute @Valid UserRegisterDTO info) {
        User user = userService.findByUsernameOrEmail(info.getEmail(), info.getUsername());

        if(user != null)
            return GeneralResponse.builder().status(HttpStatus.CONFLICT).getResponse();

        return GeneralResponse.builder().status(HttpStatus.OK).data(info).message("User registered succesfuly").getResponse();
    }
}
