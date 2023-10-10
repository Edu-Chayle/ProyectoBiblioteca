package com.example.ProyectoBiblioteca.repository;

import com.example.ProyectoBiblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findBySurname(String surname);
}