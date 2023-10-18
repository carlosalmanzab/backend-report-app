package com.reportapp.demo.entity.dto.comuna;

import com.reportapp.demo.entity.dto.barrio.BarrioDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComunaDTO {
    private Long id;
    private String nombre;
    private List<BarrioDTO> barrios;
}
