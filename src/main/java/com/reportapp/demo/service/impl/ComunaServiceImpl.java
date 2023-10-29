package com.reportapp.demo.service.impl;

import com.reportapp.demo.entity.Comuna;
import com.reportapp.demo.entity.dto.comuna.ComunaDTO;
import com.reportapp.demo.entity.mapper.BarrioMapper;
import com.reportapp.demo.entity.mapper.ComunaMapper;
import com.reportapp.demo.repository.IBarrioRepository;
import com.reportapp.demo.repository.IComunaRepository;
import com.reportapp.demo.service.ComunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComunaServiceImpl implements ComunaService {

    @Autowired
    private IComunaRepository comunaRepository;
    @Autowired
    private IBarrioRepository barrioRepository;
    @Autowired
    private ComunaMapper comunaMapper;

    @Autowired
    private BarrioMapper barrioMapper;

    @Override
    public ResponseEntity<List<ComunaDTO>> listar() {
        List<Comuna> comunas = comunaRepository.findAll();

        if (comunas.isEmpty()) return ResponseEntity.noContent().build();

        List<ComunaDTO> comunaDTOList = comunas.stream().map(comuna -> {
            ComunaDTO comunaDTO = new ComunaDTO();
            comunaDTO.setId(comuna.getId());
            comunaDTO.setNombre(comuna.getNombre());
            comunaDTO.setBarrios(
                    barrioRepository.findByComuna(comunaMapper.toEntity(comunaDTO)).stream()
                            .map(barrioMapper::toDTO)
                            .toList()
            );
            return comunaDTO;
        }).toList();

        return ResponseEntity.ok(comunaDTOList);
    }
}
