package com.reportapp.demo.controller;

import com.reportapp.demo.service.ComunaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comunas")
@Tag(name = "Comuna", description = "Endpoint para el manejo de los Comunas")
public class ComunaController {
    @Autowired
    private ComunaService comunaService;

    @GetMapping()
    public ResponseEntity<?> listar() {
        return comunaService.listar();
    }
}
