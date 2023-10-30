package com.reportapp.demo.service.impl;

import com.reportapp.demo.entity.Barrio;
import com.reportapp.demo.entity.Coordenadas;
import com.reportapp.demo.entity.Imagen;
import com.reportapp.demo.entity.Reporte;
import com.reportapp.demo.entity.TipoReporte;
import com.reportapp.demo.entity.dto.reporte.ReporteDTO;
import com.reportapp.demo.entity.dto.reporte.ReporteDTOSave;
import com.reportapp.demo.entity.mapper.ReporteMapper;
import com.reportapp.demo.entity.usuario.Usuario;
import com.reportapp.demo.repository.IBarrioRepository;
import com.reportapp.demo.repository.ICoordenadasRepository;
import com.reportapp.demo.repository.IImagenRepository;
import com.reportapp.demo.repository.IReporteRepository;
import com.reportapp.demo.repository.ITipoReporteRepository;
import com.reportapp.demo.repository.IUsuarioRepository;
import com.reportapp.demo.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private IReporteRepository reporteRepository;

    @Autowired
    private ICoordenadasRepository coordenadasRepository;

    @Autowired
    private IImagenRepository imagenRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IBarrioRepository barrioRepository;

    @Autowired
    private ITipoReporteRepository tipoReporteRepository;

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
    public ResponseEntity<ReporteDTO> guardarReporte(ReporteDTOSave reporteDTOSave, String username) {
        
        if (reporteDTOSave == null) return ResponseEntity.badRequest().build();
        Reporte reporteCreate = reporteMapper.toEntity(reporteDTOSave);
        
        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
        if (usuario.isEmpty()) return ResponseEntity.badRequest().build();
        reporteCreate.setUsuario(usuario.get());

        if (reporteDTOSave.getCoordenadas()==null) return ResponseEntity.badRequest().build();
        Coordenadas coordenadas = new Coordenadas();
        coordenadas.setLatitud(reporteDTOSave.getCoordenadas().getLatitud());
        coordenadas.setLongitud(reporteDTOSave.getCoordenadas().getLongitud());
        Coordenadas coordenadasSaved = coordenadasRepository.save(coordenadas);
        reporteCreate.setCoordenadas(coordenadasSaved);

        Imagen imagen = new Imagen();
        imagen.setArchivo(reporteDTOSave.getImagen().getArchivo());
        Imagen imagenSaved = imagenRepository.save(imagen);
        reporteCreate.setImagen(imagenSaved);
        
        Optional<Barrio> barrio = barrioRepository.findById(reporteDTOSave.getBarrioId());
        if (barrio.isEmpty()) return ResponseEntity.badRequest().build();
        reporteCreate.setBarrio(barrio.get());
        
        Optional<TipoReporte> tipoReporte = tipoReporteRepository.findById(reporteDTOSave.getTipoReporteId());
        if (tipoReporte.isEmpty()) return ResponseEntity.badRequest().build();
        reporteCreate.setTipoReporte(tipoReporte.get());
        reporteCreate.setFechaRegistro(LocalDateTime.now());
        
        Reporte reporteSave = reporteRepository.save(reporteCreate); 
        if (reporteSave.getId() == null) return ResponseEntity.internalServerError().build();
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("reportes/{id}");
        URI locationUri = builder.buildAndExpand(reporteSave.getId()).toUri();

        return ResponseEntity.created(locationUri).body(reporteMapper.toDTO(reporteSave));
    }

    @Override
    public ResponseEntity<List<ReporteDTO>> reportesPorUsuario(String username) {
        Optional<Usuario> usuario =usuarioRepository.findByEmail(username);

        if (usuario.isEmpty()) return ResponseEntity.notFound().build();
        if (usuario.get().getReportes().isEmpty()) return ResponseEntity.noContent().build();
        List<Reporte> reportes = usuario.get().getReportes();

        List<ReporteDTO> reporteDTOS = reportes.stream().map(reporteMapper::toDTO).toList();
        return ResponseEntity.ok(reporteDTOS);
    }

}
