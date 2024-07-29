package com.palmen.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.palmen.biblioteca.models.entities.Libro;
import com.palmen.biblioteca.service.ILibroService;

@Controller
public class CatalogoController {

	@Autowired
	private ILibroService libroService;

	@GetMapping("/catalogo")
	public String verCatalogo(Model model) {
		List<Libro> librosHabilitados = libroService.obtenerLibrosHabilitados();
		model.addAttribute("libros", librosHabilitados);
		model.addAttribute("vista", "catalogo");
		return "catalogo";
	}

	@PostMapping("/buscarLibros")
	public String buscarLibros(@RequestParam("categoria") String categoria, Model model) {
		
		List<Libro> librosCategoria =  libroService.obtenerLibrosPorCategoria(categoria);
		model.addAttribute("libros", librosCategoria);
		
		return "catalogo";
	}
}
