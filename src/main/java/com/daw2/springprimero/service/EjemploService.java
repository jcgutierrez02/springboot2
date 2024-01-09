package com.daw2.springprimero.service;

import com.daw2.springprimero.exceptions.EjemploBadRequestException;
import com.daw2.springprimero.exceptions.EjemploNotFoundException;
import com.daw2.springprimero.model.Ejemplo;
import com.daw2.springprimero.repository.EjemploRepository;
import com.daw2.springprimero.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EjemploService {

    @Autowired
    private EjemploRepository ejemploRepository;

    public List<Ejemplo> getAllEjemplos() {
        return ejemploRepository.findAll();
    }

    public Ejemplo createEjemplo(Ejemplo ejemplo, MultipartFile file) throws IOException {
        if (ejemplo.getNombre() == null || ejemplo.getNombre().isEmpty())
            throw new EjemploBadRequestException("Debe introducirse el nombre");

        if (ejemplo.getEdad() == null || ejemplo.getEdad() <= 0)
            throw new EjemploBadRequestException("Debe introducirse la edad y debe ser mayor que 0");

        Ejemplo ejemplosave = new Ejemplo(ejemplo.getNombre(), ejemplo.getEdad());
        ejemplosave.setFoto(ImageUtils.compressImage(file.getBytes()));

        return ejemploRepository.save(ejemplosave);
    }

    public Optional<Ejemplo> getEjemploById(Long id) {

        // return ejemploRepository.findById(id);
        return Optional.ofNullable(ejemploRepository.findById(id).orElseThrow(
                () -> new EjemploNotFoundException("No se ha encontrado la persona con id: " + id)
        ));
    }
    public Ejemplo updateEjemplo(Ejemplo ejemplo) {
        if (ejemplo.getNombre() == null || ejemplo.getNombre().isEmpty())
            throw new EjemploBadRequestException("Debe introducirse el nombre");

        if (ejemplo.getEdad() == null || ejemplo.getEdad() <= 0)
            throw new EjemploBadRequestException("Debe introducirse la edad y debe ser mayor que 0");

        return ejemploRepository.save(ejemplo);
    }

    public void deleteEjemploById(Long id) {
        ejemploRepository.deleteById(id);
    }

    // Otros métodos para operaciones específicas

    public List<Ejemplo> getEjemplosByNombre(String nombre) {
        return ejemploRepository.findByNombreContainingIgnoreCase(nombre);
    }

}


