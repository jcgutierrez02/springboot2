package com.daw2.springprimero.service;

import com.daw2.springprimero.model.Ejemplo;
import com.daw2.springprimero.model.Genero;
import com.daw2.springprimero.repository.EjemploRepository;
import com.daw2.springprimero.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {
    @Autowired
    private GeneroRepository generoRepository;

    public List<Genero> getAllGeneros() {
        return generoRepository.findAll();
    }


}
