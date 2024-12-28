package controller;

import models.Mensaje;
import services.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {
    @Autowired
    private MensajeService mensajeService;
    // Endpoint para guardar un mensaje
    @PostMapping
    public Mensaje guardarMensaje(@RequestBody Mensaje mensaje) {
        System.out.println("Guardar mensaje: " + mensaje);
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