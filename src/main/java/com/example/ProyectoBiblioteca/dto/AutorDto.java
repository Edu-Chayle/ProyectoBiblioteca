package com.example.ProyectoBiblioteca.dto;

import com.example.ProyectoBiblioteca.model.Libro;
import java.util.Set;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter

public class AutorDto {
    private String name;
    private String surname;
    private Set<Libro> libros;
}