package co.parameta.aplicacion.repositorios;

import co.parameta.aplicacion.entidades.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepo extends JpaRepository<Empleado, String> {
}
