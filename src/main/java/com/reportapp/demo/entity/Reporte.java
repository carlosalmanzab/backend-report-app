package com.reportapp.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Reporte")
public class Reporte {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "barrio_id")
    private Barrio barrio;

    @ManyToOne
    @JoinColumn(name = "tipo_reporte_id")
    private TipoReporte tipoReporte;

    @OneToOne
    @JoinColumn(name = "imagen_id")
    private Imagen imagen;

    @OneToOne
    @JoinColumn(name = "coordenada_id")
    private Coordenadas coordenadas;

    @Column(name = "fecha")
    private LocalDate fechaRegistro;

    @Column(name = "descripcion")
    private String descripcion;

}
