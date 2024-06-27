package com.palmen.biblioteca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogoController {

	@GetMapping("/catalogo")
	public String verCatalogo() {
		return "catalogo";
	}

}
