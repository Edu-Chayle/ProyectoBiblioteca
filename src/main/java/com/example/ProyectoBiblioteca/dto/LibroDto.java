package com.example.ProyectoBiblioteca.dto;

import com.example.ProyectoBiblioteca.model.Autor;
import java.util.Date;
import java.util.Set;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter

public class LibroDto {
    private String title;
    private Date publishDate;
    private String genre;
    private Set<Autor> autores;
}