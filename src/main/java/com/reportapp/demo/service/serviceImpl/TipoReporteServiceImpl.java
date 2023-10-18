package com.reportapp.demo.service.serviceImpl;

import com.reportapp.demo.entity.TipoReporte;
import com.reportapp.demo.entity.dto.tipoReporte.TipoReporteDTO;
import com.reportapp.demo.entity.dto.tipoReporte.TipoReporteDTOSave;
import com.reportapp.demo.entity.mapper.TipoReporteMapper;
import com.reportapp.demo.repository.ITipoReporteRepository;
import com.reportapp.demo.service.TipoReporteService;
import com.reportapp.demo.share.constant.TipoReporteMessageConstants;
import com.reportapp.demo.share.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoReporteServiceImpl implements TipoReporteService {
    @Autowired
    private ITipoReporteRepository tipoReporteRepository;

    @Autowired
    private TipoReporteMapper tipoReporteMapper;

    @Override
    public ResponseEntity<?> listarTiposDeIndentificaciones() {
        ResponseMessage.ResponseMessageBuilder responseMessage = ResponseMessage.builder();

        List<TipoReporte> tipoReportes = tipoReporteRepository.findAllByEstado(true);
        if (tipoReportes.isEmpty()) {
            responseMessage
                    .code(HttpStatus.NOT_FOUND.value())
                    .message(TipoReporteMessageConstants.TIPO_REPORTE_ERROR_NO_FOUND_TIPO_REPORTES)
                    .build();
            return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
        }

        List<TipoReporteDTO> tipoReporteDTOS = tipoReportes.stream().map(tipoReporteMapper::toDTO).toList();
        return new ResponseEntity<>(tipoReporteDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> guardar(TipoReporteDTOSave tipoReporteDTOSave) {
        ResponseMessage.ResponseMessageBuilder responseMessage =  ResponseMessage.builder();

        if (tipoReporteDTOSave == null) {
            responseMessage
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(TipoReporteMessageConstants.TIPO_REPORTE_ERROR_NULL)
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
        }

        TipoReporte tipoReporteSave = tipoReporteRepository.save(tipoReporteMapper.toEntity(tipoReporteDTOSave));

        if (tipoReporteSave == null) {
            responseMessage
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(TipoReporteMessageConstants.TIPO_REPORTE_ERROR_SAVE)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }

        responseMessage
                .code(HttpStatus.CREATED.value())
                .message(TipoReporteMessageConstants.TIPO_REPORTE_SUCCESS_SAVE)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }
}
