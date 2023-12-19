package com.tr.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUsuario;

    @Column(unique = true)
    private String nombre;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private Ciudad ciudad;
}
