package com.sistema.certus.servicio;

import com.sistema.certus.enumeraciones.Rol;
import com.sistema.certus.modelo.Pelicula;
import com.sistema.certus.modelo.Ticket;
import com.sistema.certus.modelo.Usuario;
import com.sistema.certus.repositorios.PeliculaRepositorio;
import com.sistema.certus.repositorios.TicketRepositorio;
import com.sistema.certus.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.security.Principal;
import java.time.LocalDate;

@Service
public class TicketServicio {

    @Autowired
    private TicketRepositorio Bases;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PeliculaRepositorio peliculaRepositorio;

    public Usuario GuardarTicket(int id,Pelicula pelicula, int asientos, Principal principal) {

        Pelicula peliculaDB = peliculaRepositorio.getOne(id);
        peliculaDB.setTitulo(pelicula.getTitulo());

        //String nombrepeli=pelicula.getTitulo();

        String nombreUsuario = principal.getName();
        Usuario usuario = usuarioRepository.BuscarPorNombre(nombreUsuario);

        Ticket ticket = new Ticket();

        ticket.setUsuario(usuario);
        ticket.setPeliculas(peliculaDB); // Utilizar la película actualizada desde la base de datos
        ticket.setFecha(LocalDate.now());
        ticket.setPrecio(50); // Convertir el precio a un valor de tipo double
        ticket.setAsiento(asientos);
         //Guardar el ticket en la base de datos
        return Bases.save(ticket).getUsuario();

    }

}
