package com.reportapp.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "barrio")
public class Barrio {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre", columnDefinition = "varchar(100) character set utf8mb4")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "comuna_id")
    private Comuna comuna;

}
