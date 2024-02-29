package com.pokemonreview.api.controllers;

import com.pokemonreview.api.dto.auth.AuthenticationRequest;
import com.pokemonreview.api.dto.auth.AuthenticationResponse;
import com.pokemonreview.api.dto.auth.RegisterRequest;
import com.pokemonreview.api.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
//@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;

    @Autowired
    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        System.out.println("==================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================");
        return new ResponseEntity<AuthenticationResponse>(authService.register(request), HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return new ResponseEntity<AuthenticationResponse>(authService.authenticate(request), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<String>("Hello from auth", HttpStatus.OK);
    }
}
