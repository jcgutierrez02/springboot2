package com.daw2.springprimero.controller;

import com.daw2.springprimero.model.Ejemplo;
import com.daw2.springprimero.model.Genero;
import com.daw2.springprimero.service.EjemploService;
import com.daw2.springprimero.service.GeneroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
