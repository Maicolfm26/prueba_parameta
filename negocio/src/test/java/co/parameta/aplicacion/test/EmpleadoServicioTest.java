package co.parameta.aplicacion.test;

import co.parameta.aplicacion.NegocioApplication;
import co.parameta.aplicacion.entidades.Cargo;
import co.parameta.aplicacion.entidades.Empleado;
import co.parameta.aplicacion.entidades.TipoDocumento;
import co.parameta.aplicacion.servicios.EmpleadoServicio;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class EmpleadoServicioTest {

    @Autowired
    private EmpleadoServicio empleadoServicio;

    @Test
    public void registrarUsuarioTest(){
        //Creamos un usuario con los campos en null
        Empleado empleado = new Empleado();

        empleado.setNombres("");
        empleado.setApellidos("");
        empleado.setNumeroDocumento("");

        try {
            //Guardamos el registro
            Empleado guardado = empleadoServicio.guardarEmpleado(empleado);
            //Si lo guarda ha fallado las validaciones
            Assert.fail();
        } catch (Exception e) {
            //El usuario no se ha guardado por lo que paso esta validacion
        }

        //Seteamos unos valores al empleado
        empleado.setNombres("Maicol");
        empleado.setApellidos("Florez Muñoz");
        empleado.setTipoDocumento(TipoDocumento.CEDULA);
        empleado.setNumeroDocumento("1004826767");
        empleado.setFechaNacimiento(LocalDate.of(2002,6,26));
        empleado.setCargo(Cargo.PROGRAMADOR);
        empleado.setSalario(8500.0);

        try {
            //Guardamos el registro
            Empleado guardado = empleadoServicio.guardarEmpleado(empleado);
            //Verificamos que el empleado se haya guardado
            Assert.assertNotNull(guardado);
        } catch (Exception e) {
            //El empleado no se guardo
            Assertions.assertTrue(false, e.getMessage());
        }

        try {
            //Intentamos volver a guardar y no nos puede dejar porque se repite la cedula
            Empleado guardado = empleadoServicio.guardarEmpleado(empleado);
            //Falla porque guardo un empleado duplicado
            Assert.fail();
        } catch (Exception e) {
            //El usuario no se ha guardado por lo que paso esta validacion
        }

        //Ponemos menor edad al empleado
        empleado.setNumeroDocumento("1004827");
        empleado.setFechaNacimiento(LocalDate.of(2010,6,26));

        try {
            //Intentamos guardar el empleado menor de edad
            Empleado guardado = empleadoServicio.guardarEmpleado(empleado);
            //Falla porque guardo un empleado menor de edad
            Assert.fail();
        } catch (Exception e) {
            //El usuario no se ha guardado por lo que paso esta validacion
        }
    }
}
