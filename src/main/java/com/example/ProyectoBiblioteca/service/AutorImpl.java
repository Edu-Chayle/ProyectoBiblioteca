package com.example.ProyectoBiblioteca.service;

import com.example.ProyectoBiblioteca.dto.AutorDto;
import com.example.ProyectoBiblioteca.exceptions.AutorNoEncontradoException;
import com.example.ProyectoBiblioteca.mapper.MapperAutor;
import com.example.ProyectoBiblioteca.model.Autor;
import com.example.ProyectoBiblioteca.repository.AutorRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class AutorImpl implements AutorI {
    private final AutorRepository autorRepository;

    @Override
    public List<AutorDto> findAllAutores() {
        List<AutorDto> listaAutores = autorRepository.findAll().stream()
                                                     .map(MapperAutor::toDto)
                                                     .collect(Collectors.toList());

        return listaAutores;
    }

    @Override
    public String saveAutor(AutorDto autorDto) {
        Autor autorEntity = MapperAutor.toEntity(autorDto);

        autorRepository.save(autorEntity);

        return "Autor guardado exitosamente";
    }

    @Override
    public AutorDto findAutor(String surname) {
        Autor autorEntity = autorRepository.findBySurname(surname);

        if (autorEntity != null) {
            AutorDto autorDto = MapperAutor.toDto(autorEntity);

            return autorDto;
        } else {
            throw new AutorNoEncontradoException("No se encontró al autor " + surname + ".");
        }
    }

    @Override
    public String updateAutor(Long id, AutorDto autorDto) {
        try {
            Autor autor = autorRepository.findById(id).orElseThrow(() -> new
                          AutorNoEncontradoException("No se encontró al autor con ID " + id + "."));

            autor.setName(autorDto.getName());
            autor.setSurname(autorDto.getSurname());
            autor.setLibros(autorDto.getLibros());

            autorRepository.save(autor);

            return "El autor " + autor.getSurname() + " fue actualizado correctamente.";
        } catch (AutorNoEncontradoException e) {
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            return "Error en la actualización del autor: " + e.getMessage();
        }
    }

    @Override
    public String deleteAutor(Long id) {
        try {
            autorRepository.findById(id).orElseThrow(() -> new
                            AutorNoEncontradoException("No se encontró al autor con ID " + id + "."));

            autorRepository.deleteById(id);

            return "El autor con ID " + id + " fue eliminado correctamente.";
        } catch (AutorNoEncontradoException e) {
            return "El autor con ID " + id + " no fue encontrado.";
        } catch (Exception e) {
            return "Error en la eliminación del autor con ID " + id + ": " + e.getMessage();
        }
    }
}