package com.alura.challengeforoalura.controller;

import com.alura.challengeforoalura.domain.users.AuthenticationUserData;
import com.alura.challengeforoalura.domain.users.User;
import com.alura.challengeforoalura.infra.security.JWTTokenData;
import com.alura.challengeforoalura.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity authenticationUser(
            @RequestBody @Valid AuthenticationUserData authenticationUserData) {

        Authentication authToken = new UsernamePasswordAuthenticationToken(
                authenticationUserData.email(), authenticationUserData.password());

        var authenticatedUser = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generateToken((User) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new JWTTokenData(JWTtoken));
    }
}
