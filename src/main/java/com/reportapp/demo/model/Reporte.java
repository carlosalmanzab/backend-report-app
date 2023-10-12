package com.reportapp.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    //manyToOne
    @ManyToMany
    @JoinTable(name = "usuario_reporte",
            joinColumns = @JoinColumn(name = "reporte_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<Usuario> usuarios;

    @ManyToOne
    @JoinColumn(name = "barrio_id")
    private Barrio barrio;

    //oneToMany
    @ManyToOne
    @JoinColumn(name = "tipo_reporte_id")
    private TipoReporte tipoReporte;

    //OnetoOne
    @OneToOne
    @JoinColumn(name = "imagen_id")
    private Imagen imagen;

    @Column(name = "fecha")
    private LocalDate fechaRegistro;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "latitud", precision = 10, scale = 6)
    private BigDecimal latitud;

    @Column(name = "longitud", precision = 10, scale = 6)
    private BigDecimal longitud;


}
