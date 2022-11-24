package co.parameta.aplicacion.rest;

import co.parameta.aplicacion.dto.MensajeHttp;
import co.parameta.aplicacion.entidades.Empleado;
import co.parameta.aplicacion.servicios.EmpleadoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/empleado")
public class EmpleadoRestController {

    private final EmpleadoServicio empleadoServicio;

    public EmpleadoRestController(EmpleadoServicio empleadoServicio) {
        this.empleadoServicio = empleadoServicio;
    }

    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Empleado empleado) {
        try {
            return ResponseEntity.status(201).body(empleadoServicio.guardarEmpleado(empleado));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MensajeHttp(e.getMessage()));
        }
    }
}
