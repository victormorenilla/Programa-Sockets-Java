package controller;

import models.Mensaje;
import org.springframework.web.multipart.MultipartFile;
import services.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


import static controller.ImagenController.UPLOAD_DIR;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {
    private static final String UPLOAD_DIR = "uploads/";
    @Autowired
    private MensajeService mensajeService;

    // Endpoint para guardar un mensaje
    @PostMapping
    public Mensaje guardarMensaje(@RequestBody Mensaje mensaje) {
        // Verifica si la URL de la imagen está presente en el mensaje
        if (mensaje.getImageUrl() == null || mensaje.getImageUrl().isEmpty()) {
            mensaje.setImageUrl(""); // Asigna una cadena vacía si no hay imagen
        }
        // Guarda el mensaje en la base de datos
        return mensajeService.guardarMensaje(mensaje);
    }

    // Endpoint para obtener todos los mensajes
    @GetMapping
    public List<Mensaje> obtenerTodosLosMensajes() {
        System.out.println("Obtener todos los mensajes");
        return mensajeService.obtenerTodosLosMensajes();
    }

    // Endpoint para obtener mensajes de un usuario específico
    @GetMapping("/usuario/{username}")
    public List<Mensaje> obtenerMensajesPorUsuario(@PathVariable String username) {
        System.out.println("Obtener mensajes por usuario: " + username);
        return mensajeService.obtenerMensajesPorUsuario(username);
    }
}