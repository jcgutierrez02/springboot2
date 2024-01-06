package com.daw2.springprimero.service;

import com.daw2.springprimero.model.Ejemplo;
import com.daw2.springprimero.repository.EjemploRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EjemploService {

    @Autowired
    private EjemploRepository ejemploRepository;

    public List<Ejemplo> getAllEjemplos() {
        return ejemploRepository.findAll();
    }

    public Ejemplo createEjemplo(Ejemplo ejemplo) {
        if (ejemplo.getNombre() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe introducirse el nombre");
        return ejemploRepository.save(ejemplo);
    }

    public Optional<Ejemplo> getEjemploById(Long id) {
        return ejemploRepository.findById(id);
    }
    public Ejemplo updateEjemplo(Ejemplo ejemplo) {
        return ejemploRepository.save(ejemplo);
    }

    public void deleteEjemploById(Long id) {
        ejemploRepository.deleteById(id);
    }

    // Otros métodos para operaciones específicas

    public List<Ejemplo> getEjemplosByNombre(String nombre) { return ejemploRepository.findByNombreContainingIgnoreCase(nombre); }
}
