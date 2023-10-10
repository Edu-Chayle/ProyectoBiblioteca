package com.example.ProyectoBiblioteca.controller;

import com.example.ProyectoBiblioteca.dto.AutorDto;
import com.example.ProyectoBiblioteca.exceptions.AutorNoEncontradoException;
import com.example.ProyectoBiblioteca.service.AutorI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/autores")
@RequiredArgsConstructor
@RestController

public class AutorController {
    private final AutorI autorService;

    @GetMapping("/findAll")
    public ResponseEntity<List<AutorDto>> findAllAutores() {
        List<AutorDto> lista = autorService.findAllAutores();

        return new ResponseEntity<> (lista, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveAutor(@RequestBody AutorDto autorDto) {
        String mensaje = autorService.saveAutor(autorDto);

        return new ResponseEntity<> (mensaje, HttpStatus.CREATED);
    }

    @GetMapping("/find/{surname}")
    public ResponseEntity<?> findAutor(@PathVariable String surname) {
        try {
            AutorDto autorDto = autorService.findAutor(surname);

            return new ResponseEntity<> (autorDto, HttpStatus.OK);
        } catch (AutorNoEncontradoException e) {
            return new ResponseEntity<> (e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAutor(@PathVariable Long id, @RequestBody AutorDto autorDto) {
        try {
            String mensaje = autorService.updateAutor(id, autorDto);

            return ResponseEntity.ok(mensaje);
        } catch (AutorNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la actualización del autor: "
                                                                                + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAutor(@PathVariable Long id) {
        try {
            String mensaje = autorService.deleteAutor(id);

            return ResponseEntity.ok(mensaje);
        } catch (AutorNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El autor con ID " + id + " no fue encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error en la eliminación del autor con ID " + id + ": " + e.getMessage());
        }
    }
}