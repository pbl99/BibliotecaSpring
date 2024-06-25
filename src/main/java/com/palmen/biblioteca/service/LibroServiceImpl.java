package com.palmen.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmen.biblioteca.repository.LibroRepository;

@Service
public class LibroServiceImpl implements ILibroService{

	@Autowired
	private LibroRepository libroRepository;
}
