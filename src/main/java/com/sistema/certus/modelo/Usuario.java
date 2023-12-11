package com.sistema.certus.modelo;


import com.sistema.certus.enumeraciones.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private  Long id;
    private String nombre;
    private String apellido;
    private int telefono;
    private String email;
    private String contrasena;

    @Enumerated( EnumType.STRING)
    private Rol rol;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinTable(
            name = "usuario_pelicula",
            joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pelicula_id",referencedColumnName = "id_pelicula")
    )
    private List<Pelicula> peliculas;
}
