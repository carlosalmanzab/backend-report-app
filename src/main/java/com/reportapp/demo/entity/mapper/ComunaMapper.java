package com.reportapp.demo.entity.mapper;

import com.reportapp.demo.entity.Comuna;
import com.reportapp.demo.entity.dto.comuna.ComunaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ComunaMapper {
    ComunaMapper INSTANCE = Mappers.getMapper(ComunaMapper.class);

    ComunaDTO toDTO(Comuna comuna);
    Comuna toEntity(ComunaDTO comunaDTO);
}
