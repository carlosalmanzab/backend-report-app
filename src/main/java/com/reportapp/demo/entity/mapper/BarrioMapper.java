package com.reportapp.demo.entity.mapper;

import com.reportapp.demo.entity.Barrio;
import com.reportapp.demo.entity.dto.barrio.BarrioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BarrioMapper {

    BarrioMapper INSTANCE = Mappers.getMapper(BarrioMapper.class);

    BarrioDTO toDTO(Barrio barrio);
    Barrio toEntity(BarrioDTO barrioDTO);
}
