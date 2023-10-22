package com.reportapp.demo.controller;

import com.reportapp.demo.entity.dto.barrio.BarrioDTO;
import com.reportapp.demo.service.BarrioService;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/barrios")
@Tag(name = "Barrio", description = "Endpoint para el manejo de los Barrios")
public class BarrioController {

    @Autowired
    private BarrioService barrioService;

    @GetMapping()
    public ResponseEntity<List<BarrioDTO>> listar() {
        return barrioService.listar();
    }
}
