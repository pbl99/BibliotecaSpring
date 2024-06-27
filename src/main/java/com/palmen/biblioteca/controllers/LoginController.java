package com.palmen.biblioteca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.palmen.biblioteca.models.entities.Usuario;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login(Usuario usuario) {
		return "login";
	}

}
