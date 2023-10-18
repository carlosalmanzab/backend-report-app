package com.reportapp.demo.service.serviceImpl;

import com.reportapp.demo.entity.Barrio;
import com.reportapp.demo.entity.Comuna;
import com.reportapp.demo.entity.dto.barrio.BarrioDTO;
import com.reportapp.demo.entity.dto.comuna.ComunaDTO;
import com.reportapp.demo.entity.mapper.BarrioMapper;
import com.reportapp.demo.entity.mapper.ComunaMapper;
import com.reportapp.demo.repository.IBarrioRepository;
import com.reportapp.demo.repository.IComunaRepository;
import com.reportapp.demo.service.ComunaService;
import com.reportapp.demo.share.constant.BarrioMessageConstants;
import com.reportapp.demo.share.constant.ComunaMessageConstants;
import com.reportapp.demo.share.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> listar() {
        ResponseMessage.ResponseMessageBuilder responseMessage = ResponseMessage.builder();

        List<Comuna> comunas = comunaRepository.findAll();

        if (comunas.isEmpty()) {
            responseMessage.code(HttpStatus.NOT_FOUND.value())
                    .message(ComunaMessageConstants.COMUNA_NO_FOUND)
                    .build();
            return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
        }

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
        return new ResponseEntity<>(comunaDTOList, HttpStatus.OK);
    }
}
