package com.palmen.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmen.biblioteca.models.entities.Libro;
import com.palmen.biblioteca.repository.LibroRepository;

@Service
public class LibroServiceImpl implements ILibroService{

	@Autowired
	private LibroRepository libroRepository;
	
	@Override
	public void save(Libro libro) {
		libroRepository.save(libro);
	}


	@Override
	public void deleteById(Integer isbn) {
		libroRepository.deleteById(isbn);
	}


	@Override
	public Optional<Libro> findById(Integer isbn) {
		return libroRepository.findById(isbn);
	}


	@Override
	public List<Libro> findAll() {
		return libroRepository.findAll();
	}
}
