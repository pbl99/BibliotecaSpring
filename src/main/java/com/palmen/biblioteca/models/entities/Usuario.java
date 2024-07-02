package com.palmen.biblioteca.models.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Usuario {

	@Id
	@Email
	@NotBlank(message = "El email no puede estar vacío")
	private String email;

	@NotBlank(message = "El nombre de usuario no puede estar vacío")
	private String username;

	@NotBlank(message = "La contraseña no puede estar vacía")
	@Pattern(regexp = ".{8,}", message = "La contraseña debe tener al menos 8 caracteres")
	private String password;

	@NotBlank(message = "El nombre no puede estar vacío")
	private String nombre;

	@NotBlank(message = "Los apellidos no pueden estar vacíos")
	private String apellidos;

	//@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	//private List<Libro> libros;

	@Column(name = "es_admin")
	private boolean esAdmin = false;

	@Column(name = "fecha_registro")
	private LocalDate fechaRegistro;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}*/

	public boolean isEsAdmin() {
		return esAdmin;
	}

	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

}
