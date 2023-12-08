package com.reportapp.demo.entity.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.reportapp.demo.entity.Barrio;
import com.reportapp.demo.entity.Reporte;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Usuario", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Usuario implements UserDetails{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "documento", unique = true)
    private String identificacion;

    @NotBlank(message = "El campo nombre no puede estar vacio")
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "segundoNombre")
    private String segundoNombre;

    @NotBlank(message = "El campo apellido no puede estar vacio")
    @Column(name = "apellido")
    private String apellido;

    @Column(name = "segundoApellido")
    private String segundoApellido;


    @NotBlank(message = "El campo email no puede estar vacio")
    @Email(message =  "El email ingresado no cumple con el formato standar")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank(message = "El campo password no puede estar vacio")
    @Length(min = 6, message = "La contraseña debe tener minimo 6 caracteres")
    @Column(name = "password")
    private String password;

    @Column(name = "celular")
    private String celular;

    @Column(name = "direccion")
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "barrio_id")
    private Barrio barrio;

    @Builder.Default
    @Column(name = "fechaCreacion")
    private LocalDateTime fechaCreacio = LocalDateTime.now();

    @OneToMany(mappedBy = "usuario")
    private List<Reporte> reportes;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
