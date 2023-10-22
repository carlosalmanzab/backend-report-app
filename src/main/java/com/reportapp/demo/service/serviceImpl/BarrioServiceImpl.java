package com.reportapp.demo.service.serviceImpl;

import com.reportapp.demo.entity.Barrio;
import com.reportapp.demo.entity.dto.barrio.BarrioDTO;
import com.reportapp.demo.entity.mapper.BarrioMapper;
import com.reportapp.demo.repository.IBarrioRepository;
import com.reportapp.demo.service.BarrioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarrioServiceImpl implements BarrioService {

    @Autowired
    private IBarrioRepository barrioRepository;

    @Autowired
    private BarrioMapper barrioMapper;
    @Override
    public ResponseEntity<List<BarrioDTO>> listar() {
        
        List<Barrio> barrios = barrioRepository.findAll();
        if (barrios.isEmpty()) return ResponseEntity.noContent().build();

        List<BarrioDTO> barrioDTOS = barrios.stream().map(barrioMapper::toDTO).toList();
        return ResponseEntity.ok(barrioDTOS);
    }
}
