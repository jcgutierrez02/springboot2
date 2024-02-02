package com.daw2.springprimero.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "genero")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="genero", nullable = false, length = 15)
    private String genero;

    @OneToMany(mappedBy = "genero", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Ejemplo> personas;

    public Genero(Long id, String genero) {
        this.id = id;
        this.genero = genero;
    }

}
