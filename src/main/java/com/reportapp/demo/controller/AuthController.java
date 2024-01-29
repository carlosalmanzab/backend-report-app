package com.reportapp.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reportapp.demo.entity.dto.usuario.UsuarioDTOSave;
import com.reportapp.demo.jwt.AuthResponse;
import com.reportapp.demo.jwt.LoginRequest;
import com.reportapp.demo.service.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Endpoint para crear y manejar las sesiones.")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/loguin")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> guardar(@RequestBody UsuarioDTOSave usuarioDTOSave) {
        return authService.registrar(usuarioDTOSave);
    }

}
