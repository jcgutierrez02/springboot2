package com.daw2.springprimero.repository;

import com.daw2.springprimero.model.Ejemplo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EjemploRepository extends JpaRepository<Ejemplo, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    List<Ejemplo> findByNombreContainingIgnoreCase(String nombre);

    Optional<Ejemplo> findByNombre(String nomfich);
}
