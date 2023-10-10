package com.example.ProyectoBiblioteca.repository;

import com.example.ProyectoBiblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Libro findByTitle(String title);
}