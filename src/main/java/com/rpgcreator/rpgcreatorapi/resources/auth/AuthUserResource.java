package com.rpgcreator.rpgcreatorapi.resources.auth;

import com.rpgcreator.rpgcreatorapi.dtos.input.auth.AuthUserInputDTO;
import com.rpgcreator.rpgcreatorapi.dtos.output.auth.LoginOutputDTO;
import com.rpgcreator.rpgcreatorapi.services.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthUserResource {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<LoginOutputDTO> authenticateUser(
        @RequestBody
        @Valid
        AuthUserInputDTO
        userLoginData
    ) {

        return ResponseEntity.ok(authService.validateUserLogin(userLoginData, authenticationManager));
    }
}
