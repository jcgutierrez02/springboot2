package com.daw2.springprimero.service;

import com.daw2.springprimero.exceptions.EjemploBadRequestException;
import com.daw2.springprimero.exceptions.EjemploException;
import com.daw2.springprimero.exceptions.EjemploNotFoundException;
import com.daw2.springprimero.model.Ejemplo;
import com.daw2.springprimero.model.Genero;
import com.daw2.springprimero.repository.EjemploRepository;
import com.daw2.springprimero.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        Long idGenero = ejemplo.getGenero().getId();

        if (idGenero == null || idGenero <= 0 || idGenero > 3)
            throw new EjemploBadRequestException("Debe introducirse valor de género entre 1 y 3");

        String genero;
        if ( idGenero == 1)
            genero = "mujer";
        else if ( idGenero == 2)
            genero = "hombre";
        else
            genero = "indefinido";

        Ejemplo ejemplosave = new Ejemplo(ejemplo.getNombre(), ejemplo.getEdad(), new Genero(idGenero, genero));

        if (!file.isEmpty()) {
            ejemplosave.setImagen(file.getOriginalFilename());
            ejemplosave.setFoto(ImageUtils.compressImage(file.getBytes())); // Almacena en BD el binario de la foto

            // El resto de líneas es para almacenar la imagen en disco
            // En despliegue para Docker sin DockerFile no podemos escribir en fichero.
            // Por este motivo se comenta el código correspondienter
            /*
            Path dirImg = Paths.get("src//main//resources//static//img");
            String rutaAbsoluta = dirImg.toFile().getAbsolutePath();

            try {
                byte[] bytesImg = file.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" +
                        file.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
            } catch (IOException e) {
                throw new EjemploException("Error de escritura");
            }
             */
        }
        else
            throw new EjemploBadRequestException("Debe introducirse el fichero imagen");

        return ejemploRepository.save(ejemplosave);
    }

    public Optional<Ejemplo> getEjemploById(Long id) {

        // return ejemploRepository.findById(id);
        return Optional.ofNullable(ejemploRepository.findById(id).orElseThrow(
                () -> new EjemploNotFoundException("No se ha encontrado la persona con id: " + id)
        ));
    }
    public Ejemplo updateEjemplo(Ejemplo ejemplo, MultipartFile file) throws IOException {
        if (ejemplo.getNombre() == null || ejemplo.getNombre().isEmpty())
            throw new EjemploBadRequestException("Debe introducirse el nombre");

        if (ejemplo.getEdad() == null || ejemplo.getEdad() <= 0)
            throw new EjemploBadRequestException("Debe introducirse la edad y debe ser mayor que 0");

        Long idGenero = ejemplo.getGenero().getId();

        if (idGenero == null || idGenero <= 0 || idGenero > 3)
            throw new EjemploBadRequestException("Debe introducirse valor de género entre 1 y 3");

        String genero;
        if ( idGenero == 1)
            genero = "mujer";
        else if ( idGenero == 2)
            genero = "hombre";
        else
            genero = "indefinido";

        Ejemplo ejemplosave = new Ejemplo(ejemplo.getNombre(), ejemplo.getEdad(), new Genero(idGenero, genero));

        if (!file.isEmpty()) {
            ejemplo.setImagen(file.getOriginalFilename());
            ejemplo.setFoto(ImageUtils.compressImage(file.getBytes())); // Almacena en BD el binario de la foto

            // El resto de líneas es para almacenar la imagen en disco
            // En despliegue para Docker sin DockerFile no podemos escribir en fichero.
            // Por este motivo se comenta el código correspondiente
            /*
            Path dirImg = Paths.get("src//main//resources//static//img");
            String rutaAbsoluta = dirImg.toFile().getAbsolutePath();

            try {
                byte[] bytesImg = file.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" +
                                                    file.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
            } catch (IOException e) {
                throw new EjemploException("Error de escritura");
            }
            */
        }
        else
           throw new EjemploBadRequestException("Debe introducirse el fichero imagen");

        return ejemploRepository.save(ejemplo);
    }

    public void deleteEjemploById(Long id) {
        ejemploRepository.deleteById(id);
    }

    // Otros métodos para operaciones específicas

    public List<Ejemplo> getEjemplosByNombre(String nombre) {
        return ejemploRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public byte[] descargarFoto(Long id) {
        Ejemplo ejemplo = ejemploRepository.findById(id).orElse(null);
        return ejemplo != null ? ImageUtils.decompressImage(ejemplo.getFoto()) : null;
    }

}


