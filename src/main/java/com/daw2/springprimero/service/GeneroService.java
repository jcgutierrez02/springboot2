package com.daw2.springprimero.service;

import com.daw2.springprimero.exceptions.GeneroNotFoundException;
import com.daw2.springprimero.model.Genero;
import com.daw2.springprimero.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {
    @Autowired
    private GeneroRepository generoRepository;

    public List<Genero> getAllGeneros() {
        return generoRepository.findAll();
    }

    public Optional<Genero> getGeneroById(Long id) {

        return Optional.ofNullable(generoRepository.findById(id).orElseThrow(
                () -> new GeneroNotFoundException("No se ha encontrado el género con id: " + id)
        ));
    }


}
