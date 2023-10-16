package com.reportapp.demo.controller;

import com.reportapp.demo.entity.dto.usuario.UsuarioDTOSave;
import com.reportapp.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario", description = "Endpoint para el manejo de usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @PostMapping()
    public ResponseEntity<?> guardar(@Valid @RequestBody UsuarioDTOSave usuarioDTOSave) {
        return usuarioService.registrar(usuarioDTOSave);
    }

    @GetMapping("/{id}/reportes")
    public ResponseEntity<?> reportesPorId(@PathVariable Long id) {
        return usuarioService.reportesPorId(id);
    }

    @GetMapping("/{identificacion}/reportes")
    public ResponseEntity<?> reportesPorIdentificacion(@PathVariable String identificacion) {
        return usuarioService.reportesPorIdentificacion(identificacion);
    }

}
