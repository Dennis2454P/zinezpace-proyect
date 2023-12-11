package com.sistema.certus.repositorios;


import com.sistema.certus.modelo.Ticket;
import com.sistema.certus.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepositorio extends JpaRepository<Ticket, Long> {
}
