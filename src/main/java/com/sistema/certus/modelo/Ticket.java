package com.sistema.certus.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;
    private int numAsiento;
    private LocalDate Fecha;
    private BigDecimal precio;


    @OneToOne
    @JoinColumn(name = "pelicula_id", referencedColumnName = "id_pelicula")
    private Pelicula pelicula;
    private long idUsuario;
}
