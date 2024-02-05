package com.daw2.springprimero.controller;

import com.daw2.springprimero.model.Ejemplo;
import com.daw2.springprimero.model.Genero;
import com.daw2.springprimero.service.EjemploService;
import com.daw2.springprimero.service.GeneroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GeneroController {
    @Autowired
    private GeneroService generoService;

    @Operation(summary = "Obtiene todas los Géneros", description = "Obtiene una lista de Géneros", tags = {"generos"})
    @ApiResponse(responseCode = "200", description = "Lista de Géneros")
    @GetMapping("/genero")
    public List<Genero> getAllGeneros() {
        return generoService.getAllGeneros();
    }

    @Operation(summary = "Obtiene un Género", description = "Obtiene un género dado su id", tags = {"generos"})
    @Parameter(name = "id", description = "ID del Género", required = true, example = "1")
    @ApiResponse(responseCode = "200", description = "Género encontrado")
    @ApiResponse(responseCode = "404", description = "Género no encontrado")
    @GetMapping("/genero/{id}")
    public ResponseEntity<Genero> getGeneroById(@PathVariable Long id) {
        Optional<Genero> optionalGenero = generoService.getGeneroById(id);

        if (((Optional<?>) optionalGenero).isPresent()) {
            optionalGenero = generoService.getGeneroById(id);
            return new ResponseEntity<>(optionalGenero.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
