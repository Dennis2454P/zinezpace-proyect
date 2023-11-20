package com.sistema.certus.repositorios;

import com.sistema.certus.modelo.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
   Usuario findByid(Long id);
   Optional<Usuario> findByNombreAndContrasena(String nombre,String contrasena);
   Usuario findFirstByOrderByIdAsc();
   Usuario findByNombre(String nombre);

}
