package com.daw2.springprimero.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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

    @Column(name="imagen", nullable = false, length = 100)
    private String imagen;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="foto", columnDefinition="longblob", nullable=true)
    private byte[] foto;

    @Column(name="created_at")
    private LocalDateTime created_at = LocalDateTime.now();
    @Column(name="updated_at")
    private LocalDateTime updated_at = LocalDateTime.now();

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