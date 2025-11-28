package com.perfuland.perfulandia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfuland.perfulandia.dto.LoginDTO;
import com.perfuland.perfulandia.dto.LoginDTOResponse;
import com.perfuland.perfulandia.security.JwtService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticación", description = "Endpoints para autenticación con JWT")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginDTOResponse> login(@RequestBody LoginDTO request) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getUserName(),
                request.getPassword()
        );
        authenticationManager.authenticate(authentication);

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getUserName());

        String token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new LoginDTOResponse(token));
    }
}