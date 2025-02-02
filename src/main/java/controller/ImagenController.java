package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.Map;

@RestController
@RequestMapping("/api/imagenes")
public class ImagenController {
    static final String UPLOAD_DIR = "uploads/";

    @PostMapping("/subir")
    public String subirImagen(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Error: El archivo está vacío";
        }

        try {
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + filename);

            // Verifica si el directorio existe, si no lo crea
            Path directoryPath = path.getParent();
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath); // Crea el directorio si no existe
                System.out.println("Directorio creado: " + directoryPath);
            }

            // Guardar el archivo en el sistema
            Files.write(path, file.getBytes());

            // Devolver la URL del archivo guardado
            return "http://localhost:8090/uploads/" + filename;


        } catch (IOException e) {
            return "Error al subir la imagen: " + e.getMessage();
        }
    }

    @GetMapping("/ver/{fileName}")
    public ResponseEntity<byte[]> verImagen(@PathVariable String fileName) throws IOException {
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        byte[] imageBytes = Files.readAllBytes(filePath);
        return ResponseEntity.ok(imageBytes);
    }
}
