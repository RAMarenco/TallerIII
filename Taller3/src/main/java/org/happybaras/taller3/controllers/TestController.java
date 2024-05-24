package org.happybaras.taller3.controllers;

import org.happybaras.taller3.domain.dtos.GeneralResponse;
import org.happybaras.taller3.domain.entities.User;
import org.happybaras.taller3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test-token")
public class TestController {
    private final UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/", ""})
    private ResponseEntity<GeneralResponse> testToken () {
        try {
            User user = userService.findUserAuthenticated();
            return GeneralResponse.builder().message("Worked").data(user).getResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return GeneralResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).getResponse();
        }
    }
}
