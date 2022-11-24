package co.parameta.aplicacion.serviciosImpl;

import co.parameta.aplicacion.entidades.Empleado;
import co.parameta.aplicacion.repositorios.EmpleadoRepo;
import co.parameta.aplicacion.servicios.EmpleadoServicio;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EmpleadoServicioImpl implements EmpleadoServicio {

    private final EmpleadoRepo empleadoRepo;

    public EmpleadoServicioImpl(EmpleadoRepo empleadoRepo) {
        this.empleadoRepo = empleadoRepo;
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado) throws Exception {
        Optional<Empleado> buscado = empleadoRepo.findById(empleado.getNumeroDocumento());
        if (buscado.isPresent()) {
            throw new Exception("Ya hay un empleado registrado con la cedula");
        }
        if(empleado.calcularEdadActual().getYear() < 18) {
            throw new Exception("El empleado debe ser mayor de edad");
        }
        empleado.setFechaVinculacion(LocalDate.now());
        empleadoRepo.saveAndFlush(empleado);
        return empleado;
    }
}
