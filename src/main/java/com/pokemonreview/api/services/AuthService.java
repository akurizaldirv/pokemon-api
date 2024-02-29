package com.pokemonreview.api.services;

import com.pokemonreview.api.dto.auth.AuthenticationRequest;
import com.pokemonreview.api.dto.auth.AuthenticationResponse;
import com.pokemonreview.api.dto.auth.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {
    AuthenticationResponse register(@RequestBody RegisterRequest request);
    AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request);

}
