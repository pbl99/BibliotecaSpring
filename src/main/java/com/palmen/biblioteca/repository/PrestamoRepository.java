package com.palmen.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.palmen.biblioteca.models.entities.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{

}
