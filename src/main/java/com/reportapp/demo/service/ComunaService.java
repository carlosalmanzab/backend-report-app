package com.reportapp.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.reportapp.demo.entity.dto.comuna.ComunaDTO;

public interface ComunaService {

    ResponseEntity<List<ComunaDTO>> listar();
}
