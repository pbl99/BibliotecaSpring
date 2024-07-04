package com.palmen.biblioteca.models.entities;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Entity
public class Libro {

	@Id
	@Pattern(regexp = "\\d{13}", message = "El ISBN debe contener 13 dígitos númericos") // Exactamente 13 dígitos
	private String isbn;

	@NotBlank(message = "El título no puede estar vacío")
	private String titulo;
	@NotBlank(message = "El autor no puede estar vacío")
	private String autor;
	@NotBlank(message = "El género no puede estar vacío")
	private String genero;

	private String rutaImagen;

	private boolean estaReservado;

	private boolean habilitado = true;

	@NotNull(message = "El número de páginas no puede estar vacío")
	@Positive(message = "El número de páginas debe ser positivo")
	private Integer paginas;

	@Transient
	private MultipartFile imagen; // Este campo es para el archivo de la imagen, no se almacena en la base de
									// dato
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public MultipartFile getImagen() {
		return imagen;
	}

	public void setImagen(MultipartFile imagen) {
		this.imagen = imagen;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public boolean isEstaReservado() {
		return estaReservado;
	}

	public void setEstaReservado(boolean estaReservado) {
		this.estaReservado = estaReservado;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

}
