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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<GeneralResponse> login(@RequestBody @Valid UserLoginDTO info) {
        User user = userService.findOneByIdentifier(info.getIdentifier());

        if(user == null)
            return GeneralResponse.builder().status(HttpStatus.NOT_FOUND).getResponse();

        if(!userService.checkPassword(user, info.getPassword()))
            return GeneralResponse.builder().status(HttpStatus.NOT_FOUND).message("idk").getResponse();

        try {
            Token token = userService.registerToken(user);
            return GeneralResponse.builder().status(HttpStatus.OK).data(token).getResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return GeneralResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).getResponse();
        }

    }

    @PostMapping("/register")
    public ResponseEntity<GeneralResponse> register(@RequestBody @Valid UserRegisterDTO info) {
        User user = userService.findByUsernameOrEmail(info);

        if(user != null)
            return GeneralResponse.builder().status(HttpStatus.CONFLICT).getResponse();

        userService.createUser(info);

        return GeneralResponse.builder().status(HttpStatus.OK).message("User registered succesfuly").getResponse();
    }
}
