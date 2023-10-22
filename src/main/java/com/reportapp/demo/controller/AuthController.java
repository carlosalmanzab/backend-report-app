package com.reportapp.demo.controller;

import com.reportapp.demo.entity.dto.usuario.UsuarioDTO;
import com.reportapp.demo.entity.dto.usuario.UsuarioDTOLogin;
import com.reportapp.demo.entity.dto.usuario.UsuarioDTOSave;
import com.reportapp.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Endpoint para crear y manejar las sesiones.")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/loguin")
    public ResponseEntity<UsuarioDTO> login(@RequestBody UsuarioDTOLogin usuarioDTOLogin) {
        return usuarioService.login(usuarioDTOLogin);
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> guardar(@Valid @RequestBody UsuarioDTOSave usuarioDTOSave) {
        return usuarioService.registrar(usuarioDTOSave);
    }

}
