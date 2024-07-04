package com.palmen.biblioteca.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class LoginDTO {

	@Email
	@NotBlank(message = "El email no puede estar vacío")
	private String email;

	@NotBlank(message = "La contraseña no puede estar vacía")
	@Pattern(regexp = ".{8,}", message = "La contraseña debe tener al menos 8 caracteres")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
