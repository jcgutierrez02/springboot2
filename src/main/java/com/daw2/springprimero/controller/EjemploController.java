package com.daw2.springprimero.controller;

import com.daw2.springprimero.model.Ejemplo;
import com.daw2.springprimero.service.EjemploService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @GetMapping("/persona")
    public List<Ejemplo> getAllEjemplos() {
        return ejemploService.getAllEjemplos();
    }

    @PostMapping("/persona")
    public ResponseEntity<Ejemplo> createEjemplo(@RequestBody Ejemplo ejemplo) {
        Ejemplo createdEjemplo = ejemploService.createEjemplo(ejemplo);
        return new ResponseEntity<>(createdEjemplo, HttpStatus.CREATED);
    }

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

