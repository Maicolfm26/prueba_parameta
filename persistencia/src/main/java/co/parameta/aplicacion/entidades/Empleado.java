package co.parameta.aplicacion.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Empleado implements Serializable {

    @Column(nullable = false)
    @NotBlank(message = "Debe ingresar los nombres")
    private String nombres;

    @Column(nullable = false)
    @NotBlank(message = "Debe ingresar los apellidos")
    private String apellidos;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Debe seleccionar un tipo de documento")
    private TipoDocumento tipoDocumento;

    @Id
    @Column(nullable = false)
    @NotBlank(message = "Debe ingresar el numero de documento")
    @EqualsAndHashCode.Include
    private String numeroDocumento;

    @Column(nullable = false)
    @NotNull(message = "Debe ingresar la fecha de nacimiento")
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    @NotNull(message = "Debe ingresar la fecha de vinculacion")
    private LocalDate fechaVinculacion;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Debe seleccionar un cargo")
    private Cargo cargo;

    @Column(nullable = false)
    @NotNull(message = "Debe ingresar el salario")
    private Double salario;

    @Transient
    private LocalDate edadActual;

    public LocalDate calcularEdadActual() {
        this.edadActual = LocalDate.now().minusYears(fechaNacimiento.getYear())
                .minusMonths(fechaNacimiento.getMonthValue())
                .minusDays(fechaNacimiento.getDayOfMonth());
        return edadActual;
    }
}
