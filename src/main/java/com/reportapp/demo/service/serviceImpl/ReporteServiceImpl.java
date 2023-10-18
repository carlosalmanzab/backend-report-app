package com.reportapp.demo.service.serviceImpl;

import com.reportapp.demo.entity.Reporte;
import com.reportapp.demo.entity.Usuario;
import com.reportapp.demo.entity.dto.reporte.ReporteDTO;
import com.reportapp.demo.entity.dto.reporte.ReporteDTOSave;
import com.reportapp.demo.entity.mapper.ReporteMapper;
import com.reportapp.demo.repository.IReporteRepository;
import com.reportapp.demo.repository.IUsuarioRepository;
import com.reportapp.demo.service.ReporteService;
import com.reportapp.demo.share.constant.ReporteMessageConstants;
import com.reportapp.demo.share.constant.UsuarioMessageConstants;
import com.reportapp.demo.share.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public ResponseEntity<?> listarReportes() {
        ResponseMessage.ResponseMessageBuilder responseMessage = ResponseMessage.builder();

        List<Reporte> reportes = reporteRepository.findAll();
        if (reportes.isEmpty()) {
            responseMessage
                    .code(HttpStatus.NOT_FOUND.value())
                    .message(ReporteMessageConstants.REPORTES_NO_FOUND)
                    .build();
            return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
        }

        List<ReporteDTO> reporteDTOS = reportes.stream().map(reporteMapper::toDTO).toList();
        return new ResponseEntity<>(reporteDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> reportePorId(Long id) {
        ResponseMessage.ResponseMessageBuilder responseMessage =  ResponseMessage.builder();
        Optional<Reporte> reporte = reporteRepository.findById(id);
        if (reporte.isEmpty()) {
            responseMessage
                    .code(HttpStatus.NOT_FOUND.value())
                    .message(ReporteMessageConstants.REPORTE_ERROR_NO_FOUND)
                    .build();
            return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
        }
        ReporteDTO reporteDTO = reporteMapper.toDTO(reporte.get());
        return new ResponseEntity<>(reporteDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> guardarReporte(ReporteDTOSave reporteDTOSave, Long idUsuario) {
        ResponseMessage.ResponseMessageBuilder responseMessage =  ResponseMessage.builder();

        if (reporteDTOSave == null) {
            responseMessage
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(ReporteMessageConstants.REPORTE_ERROR_NULL)
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
        }

        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isEmpty()) {
            responseMessage
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(UsuarioMessageConstants.USUARIO_ERROR_NO_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
        }
        Reporte reporteCreate = reporteMapper.toEntity(reporteDTOSave);
        reporteCreate.setUsuario(usuario.get());

        Reporte reporteSave = reporteRepository.save(reporteCreate);

        if (reporteSave == null) {
            responseMessage
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(ReporteMessageConstants.REPORTE_ERROR_SAVE)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }

        responseMessage
                .code(HttpStatus.CREATED.value())
                .message(ReporteMessageConstants.REPORTE_SUCCESS_SAVE)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

}
