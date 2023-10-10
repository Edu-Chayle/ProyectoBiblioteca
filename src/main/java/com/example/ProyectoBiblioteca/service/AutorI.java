package com.example.ProyectoBiblioteca.service;

import com.example.ProyectoBiblioteca.dto.AutorDto;
import java.util.List;

public interface AutorI {
    List<AutorDto> findAllAutores();
    String saveAutor(AutorDto autorDto);
    AutorDto findAutor(String nombre);
    String updateAutor(Long id, AutorDto autorDto);
    String deleteAutor(Long id);
}