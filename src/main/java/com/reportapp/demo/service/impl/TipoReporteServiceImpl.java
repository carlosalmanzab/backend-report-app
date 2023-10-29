package com.reportapp.demo.service.impl;

import com.reportapp.demo.entity.TipoReporte;
import com.reportapp.demo.entity.dto.tipoReporte.TipoReporteDTO;
import com.reportapp.demo.entity.dto.tipoReporte.TipoReporteDTOSave;
import com.reportapp.demo.entity.mapper.TipoReporteMapper;
import com.reportapp.demo.repository.ITipoReporteRepository;
import com.reportapp.demo.service.TipoReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class TipoReporteServiceImpl implements TipoReporteService {
    @Autowired
    private ITipoReporteRepository tipoReporteRepository;

    @Autowired
    private TipoReporteMapper tipoReporteMapper;

    @Override
    public ResponseEntity<List<TipoReporteDTO>> listarTiposDeIndentificaciones() {

        List<TipoReporte> tipoReportes = tipoReporteRepository.findAllByEstado(true);
        
        if (tipoReportes.isEmpty()) return ResponseEntity.noContent().build();

        List<TipoReporteDTO> tipoReporteDTOS = tipoReportes.stream().map(tipoReporteMapper::toDTO).toList();
        return ResponseEntity.ok(tipoReporteDTOS);
    }

    @Override
    public ResponseEntity<TipoReporteDTO> guardar(TipoReporteDTOSave tipoReporteDTOSave) {

        if (tipoReporteDTOSave == null) return ResponseEntity.badRequest().build();

        TipoReporte tipoReporteSave = tipoReporteRepository.save(tipoReporteMapper.toEntity(tipoReporteDTOSave));

        if (tipoReporteSave.getId() == null) return ResponseEntity.internalServerError().build();

        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("tiporeportes/{id}");
        URI location = builder.buildAndExpand(tipoReporteSave).toUri();

        return ResponseEntity.created(location).body(tipoReporteMapper.toDTO(tipoReporteSave));
    }
}
