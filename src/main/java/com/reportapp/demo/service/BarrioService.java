package com.reportapp.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.reportapp.demo.entity.dto.barrio.BarrioDTO;

public interface BarrioService {

    ResponseEntity<List<BarrioDTO>> listar();
}
