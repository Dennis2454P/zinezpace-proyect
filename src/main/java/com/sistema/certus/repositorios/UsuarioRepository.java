package com.sistema.certus.repositorios;

import com.sistema.certus.modelo.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
   Usuario findByid(Long id);
   Optional<Usuario> findByNombreAndContrasena(String nombre,String contrasena);
   Usuario findFirstByOrderByIdAsc();
   Usuario findByNombre(String nombre);
@Query("SELECT u fROM Usuario  u WHERE u.nombre =:nombre")
   public Usuario BuscarPorNombre(@Param("nombre") String nombre);

}
