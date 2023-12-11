package com.sistema.certus.controladores;

import com.sistema.certus.excepciones.MiException;
import com.sistema.certus.modelo.Pelicula;
import com.sistema.certus.modelo.Ticket;
import com.sistema.certus.modelo.Usuario;
import com.sistema.certus.modelo.asiento;
import com.sistema.certus.repositorios.PeliculaRepositorio;
import com.sistema.certus.repositorios.UsuarioRepository;
import com.sistema.certus.servicio.TicketServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;

@Controller
public class TicketController {
    @Autowired
    private PeliculaRepositorio peliculaRepositorio;

    @Autowired
    private TicketServicio ticketService;

    @Autowired
    private UsuarioRepository respositoriousuario;
    @GetMapping("asientos")
    public String mostrarasientos(){
        return "asientos";
    }
    @PostMapping("/generar")
    public String comprarAsiento(@RequestParam int id, @RequestParam Pelicula pelicula,@RequestParam int asiento,@RequestParam Principal principal) {
        ticketService.GuardarTicket(id,pelicula, asiento,principal);
        return "asientos";


        }


}
