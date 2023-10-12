package com.reportapp.demo.controller;

import com.reportapp.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario", description = "Endpoint para el manejo de usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{identificacion}")
    @Operation(
            summary = "Buscar usuario por documento de identificación",
            description = "Buscar usuario por documento de identificación",
            tags = {"Usuario"})
    public ResponseEntity<?> existe(@PathVariable String identificacion) {
        boolean exists = false;
        Map<String, Object> response = new HashMap<>();

        try {
            exists = usuarioService.verificarIdentificacion(identificacion);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al verificar la identificación");
            response.put("error", e);

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El usuario existe en la BD");
        response.put("usuarioExiste", exists);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
