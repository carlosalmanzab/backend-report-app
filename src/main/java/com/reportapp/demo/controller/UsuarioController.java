package com.reportapp.demo.controller;

import com.reportapp.demo.entity.dto.reporte.ReporteDTO;
import com.reportapp.demo.entity.dto.usuario.UsuarioDTO;
import com.reportapp.demo.entity.dto.usuario.UsuarioDTOSave;
import com.reportapp.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuario", description = "Endpoint para el manejo de usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @GetMapping(value = "/{id}/reportes")
    public ResponseEntity<List<ReporteDTO>> reportesPorId(@PathVariable Long id) {
        return usuarioService.reportesPorId(id);
    }

    @GetMapping(value = "/existe/{usuario}")
    public ResponseEntity<Boolean> verificarUsuario(@PathVariable UsuarioDTOSave usuarioDTOSave ) {
        return usuarioService.verificarExistencia(usuarioDTOSave);
    }
}
