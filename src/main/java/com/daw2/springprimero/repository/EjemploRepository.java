package com.daw2.springprimero.repository;

import com.daw2.springprimero.model.Ejemplo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EjemploRepository extends JpaRepository<Ejemplo, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    List<Ejemplo> findByNombreContainingIgnoreCase(String nombre);
}
