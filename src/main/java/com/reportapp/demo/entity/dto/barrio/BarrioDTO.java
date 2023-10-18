package com.reportapp.demo.entity.dto.barrio;

import com.reportapp.demo.entity.Comuna;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BarrioDTO {

    private Long id;

    private String nombre;

    private Comuna comuna;
}
