package com.daw2.springprimero.controller;

import com.daw2.springprimero.model.Ejemplo;
import com.daw2.springprimero.service.EjemploService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EjemploController {

    @Autowired
    private EjemploService ejemploService;

    @Operation(summary = "Obtiene todas las Personas", description = "Obtiene una lista de Personas", tags = {"personas"})
    @ApiResponse(responseCode = "200", description = "Lista de Personas")
    @GetMapping("/persona")
    public List<Ejemplo> getAllEjemplos() {
        return ejemploService.getAllEjemplos();
    }

    @Operation(summary = "Crea una persona", description = "Añade una persona a la colección", tags = {"personas"})
    @ApiResponse(responseCode = "201", description = "Persona añadida")
    @ApiResponse(responseCode = "400", description = "Datos de persona no válidos")
    @PostMapping("/persona")
    public ResponseEntity<Ejemplo> createEjemplo(@RequestBody Ejemplo ejemplo) {
        Ejemplo createdEjemplo = ejemploService.createEjemplo(ejemplo);
        return new ResponseEntity<>(createdEjemplo, HttpStatus.CREATED);
    }
    @Operation(summary = "Obtiene una Persona", description = "Obtiene una persona dado su id", tags = {"personas"})
    @Parameter(name = "id", description = "ID de la Persona", required = true, example = "1")
    @ApiResponse(responseCode = "200", description = "Persona encontrada")
    @ApiResponse(responseCode = "404", description = "Persona no encontrada")
    @GetMapping("/persona/{id}")
    public ResponseEntity<Ejemplo>  getEjemploById(@PathVariable Long id) {
        Optional<Ejemplo> optionalEjemplo = ejemploService.getEjemploById(id);

        if (((Optional<?>) optionalEjemplo).isPresent()) {
            optionalEjemplo = ejemploService.getEjemploById(id);
            return new ResponseEntity<>(optionalEjemplo.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @Operation(summary = "Actualiza una Persona", description = "Actualiza una Persona dado su id", tags = {"personas"})
    @Parameter(name = "id", description = "ID de la Persona", required = true, example = "1")
    @ApiResponse(responseCode = "200", description = "Persona actualizada")
    @ApiResponse(responseCode = "404", description = "Persona no encontrada")
    @ApiResponse(responseCode = "400", description = "Datos de persona no válidos")
    @PutMapping("/persona/{id}")
    public ResponseEntity<Ejemplo> updateEjemplo(@PathVariable Long id, @RequestBody Ejemplo ejemplo) {
        Optional<Ejemplo> optionalEjemplo = ejemploService.getEjemploById(id);

        if (((Optional<?>) optionalEjemplo).isPresent()) {
            Ejemplo existingEjemplo = optionalEjemplo.get();
            existingEjemplo.setNombre(ejemplo.getNombre());
            existingEjemplo.setEdad(ejemplo.getEdad());

            Ejemplo updatedEjemplo = ejemploService.updateEjemplo(existingEjemplo);
            return new ResponseEntity<>(updatedEjemplo, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Elimina una Persona", description = "Elimina una Persona dado su id", tags = {"personas"})
    @Parameter(name = "id", description = "ID de la Persona", required = true, example = "1")
    @ApiResponse(responseCode = "204", description = "Persona eliminada")
    @ApiResponse(responseCode = "404", description = "Persona no encontrada")
    @DeleteMapping("/persona/{id}")
    public ResponseEntity<Void> deleteEjemplo(@PathVariable Long id) {
        Optional<Ejemplo> optionalEjemplo = ejemploService.getEjemploById(id);

        if (optionalEjemplo.isPresent()) {
            ejemploService.deleteEjemploById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Definir otros endpoints para POST, PUT, DELETE según sea necesario
    @Operation(summary = "Obtiene un grupo de Personas", description = "Obtiene una lista de Personas", tags = {"personas"})
    @Parameter(name = "nombre", description = "Nombre de la Persona a buscar", required = true, example = "García")
    @ApiResponse(responseCode = "200", description = "Lista de Personas")
    @ApiResponse(responseCode = "404", description = "Persona no encontrada")
    @GetMapping("/persona/nom")
    public ResponseEntity<List<Ejemplo>> getEjemplosPorNombre(@RequestParam String nombre) {
        List<Ejemplo> ejemplos = ejemploService.getEjemplosByNombre(nombre);
        if (!ejemplos.isEmpty()) {
            return new ResponseEntity<>(ejemplos, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
