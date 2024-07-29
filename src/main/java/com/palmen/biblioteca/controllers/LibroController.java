package com.palmen.biblioteca.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.palmen.biblioteca.models.entities.Libro;
import com.palmen.biblioteca.models.entities.Usuario;
import com.palmen.biblioteca.service.ILibroService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class LibroController {

	@Autowired
	private ILibroService libroService;

	// Ruta donde se guardarán las imágenes
	private static String UPLOADED_FOLDER = "src/main/resources/static/images/";

	@PostMapping("/crearLibros")
	public String crearLibros(@Valid Libro libro, BindingResult result, Model model) {

		// Validar la imagen manualmente si está vacía
		if (libro.getImagen() == null || libro.getImagen().isEmpty()) {
			result.rejectValue("imagen", "error.libro", "Por favor seleccione una imagen.");
		}
		
		if (result.hasErrors()) {
			return "panel-admin";
		}

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
		model.addAttribute("librosHabilitados", libroService.obtenerLibrosHabilitados());
		model.addAttribute("librosDeshabilitados", libroService.obtenerLibrosDeshabilitados());
		model.addAttribute("mostrar", "habilitados"); // Para mantener la vista inicial en habilitados
		return "ver-libros"; // Nombre de la plantilla Thymeleaf
	}

	@PostMapping("/libros/editar")
	public String editarLibros(@Valid @ModelAttribute("libroAEditar") Libro libro, BindingResult result, Model model) {

		// Validar la imagen manualmente si está vacía
		if (libro.getImagen() == null || libro.getImagen().isEmpty()) {
			result.rejectValue("imagen", "error.libroAEditar", "Por favor seleccione una imagen.");
		}

		if (result.hasErrors()) {
			model.addAttribute("errorMensaje", "Ha ocurrido un error al guardar los cambios");
			model.addAttribute("librosHabilitados", libroService.obtenerLibrosHabilitados());
			model.addAttribute("librosDeshabilitados", libroService.obtenerLibrosDeshabilitados());
			model.addAttribute("mostrar", "habilitados"); // Mantener la vista inicial en habilitados
			return "ver-libros";
		}
		// Verificar si la imagen no está vacía
		if (libro.getImagen() != null && !libro.getImagen().isEmpty()) {
			try {
				// Obtener el nombre original del archivo
				String fileName = StringUtils.cleanPath(libro.getImagen().getOriginalFilename());
				// Crear el path completo para guardar la imagen
				Path path = Paths.get(UPLOADED_FOLDER + fileName);
				// Guardar la imagen en el path especificado
				Files.write(path, libro.getImagen().getBytes());

				libro.setRutaImagen(fileName); // Asignar el nombre de la imagen al libro
			} catch (IOException e) {
				e.printStackTrace();
				model.addAttribute("errorMensaje", "Hubo un problema al subir la imagen");
				return "ver-libros";
			}
		}

		if (libro.isEstaReservado()) {
			libro.setEstaReservado(true);
		}

		libroService.save(libro);
		return "redirect:/buscarTodos";
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
	public String buscarTodos(HttpSession session, @RequestParam(required = false) String mostrar, Model model) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "index";
		}
		if (usuario.isEsAdmin()) {
			List<Libro> librosHabilitados = libroService.obtenerLibrosHabilitados();
			List<Libro> librosDeshabilitados = libroService.obtenerLibrosDeshabilitados();

			model.addAttribute("librosHabilitados", librosHabilitados);
			model.addAttribute("librosDeshabilitados", librosDeshabilitados);

			// Establecer mostrar por defecto como "habilitados" si no se especifica
			if (mostrar == null || mostrar.isEmpty()) {
				mostrar = "habilitados";
			}

			model.addAttribute("mostrar", mostrar);

			return "ver-libros";
		} else {
			return "index";
		}
	}

	// Ejemplo para deshabilitar un libro
	@PostMapping("/libros/deshabilitar")
	public String deshabilitarLibro(@RequestParam("isbn") String isbn) {
		Optional<Libro> optionalLibro = libroService.findById(isbn);
		if (optionalLibro.isPresent()) {
			Libro libroDeshabilitado = optionalLibro.get();

			libroDeshabilitado.setHabilitado(false);
			libroService.save(libroDeshabilitado); // Guardar el libro con estado deshabilitado
		}
		return "redirect:/catalogo";
	}

	// Ejemplo para habilitar un libro
	@PostMapping("/libros/habilitar")
	public String habilitarLibro(@RequestParam("isbn") String isbn) {
		Optional<Libro> optionalLibro = libroService.findById(isbn);
		if (optionalLibro.isPresent()) {
			Libro libroHabilitado = optionalLibro.get();

			libroHabilitado.setHabilitado(true);
			libroService.save(libroHabilitado); // Guardar el libro con estado habilitado
		}
		return "redirect:/catalogo";
	}

}
