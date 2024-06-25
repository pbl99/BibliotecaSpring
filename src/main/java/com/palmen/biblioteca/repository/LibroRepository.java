package com.palmen.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.palmen.biblioteca.models.entities.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer>{

}
