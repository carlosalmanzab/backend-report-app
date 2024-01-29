package com.reportapp.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reportapp.demo.entity.dto.usuario.UsuarioDTO;
import com.reportapp.demo.service.JwtService;
import com.reportapp.demo.service.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuario", description = "Endpoint para el manejo de usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(HttpServletRequest request) {
        String token = jwtService.getTokenFromRequest(request);

        String username = jwtService.getUsernameFromToken(token);
        return usuarioService.buscar(username);
    }
}
