package com.palmen.biblioteca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.palmen.biblioteca.models.entities.Libro;

@Controller
public class UsuarioController {

	@GetMapping("/verPanelAdmin")
	public String verPanelAdmin(Libro libro) {
		return "panel-admin";
	}

}
