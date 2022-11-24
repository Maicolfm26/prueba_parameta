package co.parameta.aplicacion.test;

import co.parameta.aplicacion.WebApplication;
import co.parameta.aplicacion.entidades.Cargo;
import co.parameta.aplicacion.entidades.Empleado;
import co.parameta.aplicacion.entidades.TipoDocumento;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT, classes =
        WebApplication.class)
@AutoConfigureMockMvc
public class EmpleadoRestTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    public void guardarUsuarioTest() throws Exception {
        //Creamos un usuario con los campos en null
        Empleado empleado = new Empleado();

        empleado.setNombres("");
        empleado.setApellidos("");
        empleado.setNumeroDocumento("");

        mockMvc.perform(post("/api/empleado")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(empleado)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(500));

        //Seteamos unos valores al empleado
        empleado.setNombres("Maicol");
        empleado.setApellidos("Florez Muñoz");
        empleado.setTipoDocumento(TipoDocumento.CEDULA);
        empleado.setNumeroDocumento("1004826767");
        empleado.setFechaNacimiento(LocalDate.of(2002,6,26));
        empleado.setCargo(Cargo.PROGRAMADOR);
        empleado.setSalario(8500.0);

        mockMvc.perform(post("/api/empleado")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(empleado)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/empleado")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(empleado)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(500));

        //Ponemos menor edad al empleado
        empleado.setNumeroDocumento("1004827");
        empleado.setFechaNacimiento(LocalDate.of(2010,6,26));

        mockMvc.perform(post("/api/empleado")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(empleado)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(500));
    }
}
