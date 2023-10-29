package com.sistema.certus.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.certus.modelo.Pelicula;

public interface PeliculaRepositorio extends JpaRepository<Pelicula, Integer>{

}
