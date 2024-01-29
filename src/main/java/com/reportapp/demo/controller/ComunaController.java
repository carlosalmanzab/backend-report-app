package com.reportapp.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reportapp.demo.entity.dto.comuna.ComunaDTO;
import com.reportapp.demo.service.ComunaService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/comunas")
@Tag(name = "Comuna", description = "Endpoint para el manejo de los Comunas")
public class ComunaController {
    @Autowired
    private ComunaService comunaService;

    @GetMapping()
    public ResponseEntity<List<ComunaDTO>> listar() {
        return comunaService.listar();
    }
}
