package com.sistema.certus.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Pelicula {
	@Id
	@GeneratedValue(strategy =
			GenerationType.IDENTITY)
	@Column(name = "id_pelicula")
	private Integer id;

	@NotBlank
	private String titulo;

	@NotBlank
	private String sinopsis;

	@NotNull
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate fechaEstreno;



	private String rutaPortada;



	@Transient
	private MultipartFile portada;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public LocalDate getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(LocalDate fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}





	public String getRutaPortada() {
		return rutaPortada;
	}

	public void setRutaPortada(String rutaPortada) {
		this.rutaPortada = rutaPortada;
	}





	public MultipartFile getPortada() {
		return portada;
	}

	public void setPortada(MultipartFile portada) {
		this.portada = portada;
	}

	public Pelicula() {
		super();
	}

	public Pelicula(Integer id, @NotBlank String titulo, @NotBlank String sinopsis, @NotNull LocalDate fechaEstreno,
			 String rutaPortada,
			MultipartFile portada) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.sinopsis = sinopsis;
		this.fechaEstreno = fechaEstreno;

		this.rutaPortada = rutaPortada;

		this.portada = portada;
	}

	public Pelicula(@NotBlank String titulo, @NotBlank String sinopsis, @NotNull LocalDate fechaEstreno,
			 String rutaPortada,
			MultipartFile portada) {
		super();
		this.titulo = titulo;
		this.sinopsis = sinopsis;
		this.fechaEstreno = fechaEstreno;

		this.rutaPortada = rutaPortada;

		this.portada = portada;
	}


}
