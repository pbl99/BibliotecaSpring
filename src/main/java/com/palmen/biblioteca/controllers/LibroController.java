package com.palmen.biblioteca.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.palmen.biblioteca.models.entities.Libro;
import com.palmen.biblioteca.service.ILibroService;

import jakarta.validation.Valid;

@Controller
public class LibroController {

	@Autowired
	private ILibroService libroService;

	// Ruta donde se guardarán las imágenes
	private static String UPLOADED_FOLDER = "src/main/resources/static/images/";

	@PostMapping("/crearLibros")
	public String crearLibros(@Valid Libro libro, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "panel-admin";
		}

		// Verificar si la imagen no está vacía
		if (!libro.getImagen().isEmpty()) {
			try {
				// Obtener el nombre original del archivo
				String fileName = StringUtils.cleanPath(libro.getImagen().getOriginalFilename());
				// Crear el path completo para guardar la imagen
				Path path = Paths.get(UPLOADED_FOLDER + fileName);
				// Guardar la imagen en el path especificado
				Files.write(path, libro.getImagen().getBytes());

				libro.setRutaImagen(fileName);
			} catch (IOException e) {
				e.printStackTrace();
				model.addAttribute("message", "Hubo un problema al subir la imagen");
				return "panel-admin";
			}
		} else {
			model.addAttribute("message", "Por favor seleccione una imagen:");
			return "panel-admin";
		}

		libroService.save(libro);
		return "redirect:/catalogo";
	}

	@GetMapping("/libros/editar/{isbn}")
	public String mostrarFormularioEdicion(@PathVariable String isbn, Model model) {
		Optional<Libro> optionalLibro = libroService.findById(isbn);
		if (optionalLibro.isPresent()) {
			model.addAttribute("libroAEditar", optionalLibro.get());
		} else {
			model.addAttribute("error", "El libro no fue encontrado.");
		}
		model.addAttribute("libro", libroService.findAll());
		return "ver-libros"; // Nombre de la plantilla Thymeleaf
	}

	@PostMapping("/borrarLibros")
	public String borrarLibros(Libro libro, Model model) {
		Optional<Libro> optionalLibro = libroService.findById(libro.getIsbn());
		if (optionalLibro.isPresent()) {
			libroService.deleteById(libro.getIsbn());
			model.addAttribute("exito", "El libro se ha borrado correctamente");
		} else {
			model.addAttribute("errorBorrarPorId", "El Libro no ha sido encontrado");
		}
		return "panel-admin";
	}

	@PostMapping("/buscarPorId")
	public String buscarPorId(Libro libro, Model model) {
		Optional<Libro> optionalLibro = libroService.findById(libro.getIsbn());
		if (optionalLibro.isPresent()) {
			model.addAttribute("libro", optionalLibro.get());
			return "ver-libros";
		} else {
			// Manejar el caso cuando no se encuentra el libro, por ejemplo, agregando un
			// mensaje de error
			model.addAttribute("errorBuscarPorId", "Libro no encontrado");
			return "panel-admin";
		}

	}

	@GetMapping("/buscarTodos")
	public String buscarTodos(Model model) {
		model.addAttribute("libro", libroService.findAll());
		return "ver-libros";
	}

}
