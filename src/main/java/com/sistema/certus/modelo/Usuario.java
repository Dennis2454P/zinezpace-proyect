package com.sistema.certus.modelo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String nombre;
    private String apellido;
    private int telefono;
    private String email;
    private String contrasena;

    /* @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Singular
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")  Esta columna "usuario_id" se utiliza como clave foránea en Ticket
    private List<ticket> tickets;*/
}
