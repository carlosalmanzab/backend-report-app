package com.reportapp.demo.service.impl;

import com.reportapp.demo.entity.Reporte;
import com.reportapp.demo.entity.dto.reporte.ReporteDTO;
import com.reportapp.demo.entity.dto.reporte.ReporteDTOSave;
import com.reportapp.demo.entity.mapper.ReporteMapper;
import com.reportapp.demo.entity.usuario.Usuario;
import com.reportapp.demo.repository.IReporteRepository;
import com.reportapp.demo.repository.IUsuarioRepository;
import com.reportapp.demo.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private IReporteRepository reporteRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private ReporteMapper reporteMapper;

    @Override
    public ResponseEntity<List<ReporteDTO>> listarReportes() {

        List<Reporte> reportes = reporteRepository.findAll();
        
        if (reportes.isEmpty()) return ResponseEntity.noContent().build();

        List<ReporteDTO> reporteDTOS = reportes.stream().map(reporteMapper::toDTO).toList();
        return ResponseEntity.ok(reporteDTOS);
    }

    @Override
    public ResponseEntity<ReporteDTO> reportePorId(Long id) {
        Optional<Reporte> reporte = reporteRepository.findById(id);

        if (reporte.isEmpty()) return ResponseEntity.notFound().build();
        
        ReporteDTO reporteDTO = reporteMapper.toDTO(reporte.get());
        return ResponseEntity.ok(reporteDTO);
    }

    @Override
    public ResponseEntity<ReporteDTO> guardarReporte(ReporteDTOSave reporteDTOSave, Long idUsuario) {

        if (reporteDTOSave == null) return ResponseEntity.badRequest().build();

        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isEmpty()) return ResponseEntity.badRequest().build();
        
        Reporte reporteCreate = reporteMapper.toEntity(reporteDTOSave);
        reporteCreate.setUsuario(usuario.get());

        Reporte reporteSave = reporteRepository.save(reporteCreate);

        if (reporteSave.getId() == null) return ResponseEntity.internalServerError().build();
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("reportes/{id}");
        URI locationUri = builder.buildAndExpand(reporteSave.getId()).toUri();

        return ResponseEntity.created(locationUri).body(reporteMapper.toDTO(reporteSave));
    }

}
