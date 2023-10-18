package com.reportapp.demo.service.serviceImpl;

import com.reportapp.demo.entity.Barrio;
import com.reportapp.demo.entity.Reporte;
import com.reportapp.demo.entity.dto.barrio.BarrioDTO;
import com.reportapp.demo.entity.dto.reporte.ReporteDTO;
import com.reportapp.demo.entity.mapper.BarrioMapper;
import com.reportapp.demo.repository.IBarrioRepository;
import com.reportapp.demo.service.BarrioService;
import com.reportapp.demo.share.constant.BarrioMessageConstants;
import com.reportapp.demo.share.constant.ReporteMessageConstants;
import com.reportapp.demo.share.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> listar() {
        ResponseMessage.ResponseMessageBuilder responseMessage = ResponseMessage.builder();

        List<Barrio> barrios = barrioRepository.findAll();
        if (barrios.isEmpty()) {
            responseMessage
                    .code(HttpStatus.NOT_FOUND.value())
                    .message(BarrioMessageConstants.BARRIO_NO_FOUND)
                    .build();
            return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
        }

        List<BarrioDTO> barrioDTOS = barrios.stream().map(barrioMapper::toDTO).toList();
        return new ResponseEntity<>(barrioDTOS, HttpStatus.OK);
    }
}
