package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.exception.UnauthorizedAccessException;
import com.axonactive.personalproject.service.security.JwtAuthenticationService;
import com.axonactive.personalproject.service.security.Token;
import com.axonactive.personalproject.service.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(UserController.PATH)
@RestController
public class UserController {
    public static final String PATH="/api/user";

    @Autowired
    JwtAuthenticationService jwtAuthenticationService;

    @PostMapping
    public Token getAuthenticationToken(@RequestBody User user) throws UnauthorizedAccessException {
        return jwtAuthenticationService.createToKen(user);
    }
}
