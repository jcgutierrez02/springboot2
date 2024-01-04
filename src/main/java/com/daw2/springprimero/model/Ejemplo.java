package com.daw2.springprimero.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "persona")
public class Ejemplo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nombre", nullable = false, length = 50)
    private String nombre;
    @Column(name="edad", nullable = false)
    private Integer edad;
    @Column(name="created_at")
    private LocalDateTime created_at = LocalDateTime.now();

    public Ejemplo() {
    }

    public Ejemplo(String nombre, Integer edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Ejemplo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }

}