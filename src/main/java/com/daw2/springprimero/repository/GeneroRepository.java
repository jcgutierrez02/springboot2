package com.daw2.springprimero.repository;

import com.daw2.springprimero.model.Ejemplo;
import com.daw2.springprimero.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
}
