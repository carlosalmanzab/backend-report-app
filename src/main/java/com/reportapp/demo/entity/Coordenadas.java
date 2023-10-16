package com.reportapp.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "coordenadas")
public class Coordenadas {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "latitud", precision = 10, scale = 6)
    private BigDecimal latitud;

    @Column(name = "longitud", precision = 10, scale = 6)
    private BigDecimal longitud;
}
