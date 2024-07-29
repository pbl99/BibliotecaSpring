package com.palmen.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmen.biblioteca.models.entities.Libro;
import com.palmen.biblioteca.repository.LibroRepository;

@Service
public class LibroServiceImpl implements ILibroService {

	@Autowired
	private LibroRepository libroRepository;

	@Override
	public void save(Libro libro) {
		libroRepository.save(libro);
	}

	@Override
	public void deleteById(String isbn) {
		libroRepository.deleteById(isbn);
	}

	@Override
	public Optional<Libro> findById(String isbn) {
		return libroRepository.findById(isbn);
	}

	@Override
	public List<Libro> findAll() {
		return libroRepository.findAll();
	}

	@Override
	public List<Libro> obtenerLibrosHabilitados() {
		return libroRepository.findByHabilitadoTrue();
	}

	@Override
	public List<Libro> obtenerLibrosDeshabilitados() {
		return libroRepository.findByHabilitadoFalse();
	}

	@Override
	public List<Libro> obtenerLibrosPorCategoria(String categoria) {
		return libroRepository.findByCategoria(categoria);
	}

}
