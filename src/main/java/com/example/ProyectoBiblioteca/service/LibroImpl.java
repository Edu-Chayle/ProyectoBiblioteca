package com.example.ProyectoBiblioteca.service;

import com.example.ProyectoBiblioteca.dto.LibroDto;
import com.example.ProyectoBiblioteca.exceptions.LibroNoEncontradoException;
import com.example.ProyectoBiblioteca.mapper.MapperLibro;
import com.example.ProyectoBiblioteca.model.Libro;
import com.example.ProyectoBiblioteca.repository.LibroRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class LibroImpl implements LibroI {
    private final LibroRepository libroRepository;

    @Override
    public List<LibroDto> findAllLibros() {
        List<LibroDto> listaLibros = libroRepository.findAll().stream()
                                                    .map(MapperLibro::toDto)
                                                    .collect(Collectors.toList());

        return listaLibros;
    }

    @Override
    public String saveLibro(LibroDto libroDto) {
        Libro libroEntity = MapperLibro.toEntity(libroDto);

        libroRepository.save(libroEntity);

        return "Libro guardado exitosamente.";
    }

    @Override
    public LibroDto findLibro(String title) {
        Libro libroEntity = libroRepository.findByTitle(title);

        if (libroEntity != null) {
            LibroDto libroDto = MapperLibro.toDto(libroEntity);

            return libroDto;
        } else {
            throw new LibroNoEncontradoException("No se encontró el libro " + title + ".");
        }
    }

    @Override
    public String updateLibro(Long id, LibroDto libroDto) {
        try {
            Libro libro = libroRepository.findById(id).orElseThrow(() -> new
                          LibroNoEncontradoException("No se encontró el libro con ID " + id + "."));

            libro.setTitle(libroDto.getTitle());
            libro.setPublishDate(libroDto.getPublishDate());
            libro.setGenre(libroDto.getGenre());
            libro.setAutores(libroDto.getAutores());

            libroRepository.save(libro);

            return "El libro " + libro.getTitle() + " fue actualizado correctamente.";
        } catch (LibroNoEncontradoException e) {
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            return "Error en la actualización del libro: " + e.getMessage();
        }
    }

    @Override
    public String deleteLibro(Long id) {
        try {
            libroRepository.findById(id).orElseThrow(() -> new
                            LibroNoEncontradoException("No se encontró el libro con ID " + id + "."));

            libroRepository.deleteById(id);

            return "El libro con ID " + id + " fue eliminado correctamente.";
        } catch (LibroNoEncontradoException e) {
            return "El libro con ID " + id + " no fue encontrado.";
        } catch (Exception e) {
            return "Error en la eliminación del libro con ID " + id + ": " + e.getMessage();
        }
    }
}