package co.parameta.aplicacion.entidades;

public enum Cargo {
    PROGRAMADOR("Programador"),
    DEVOPS("DevOps"),
    PROFESIONAL_SEGURIDAD("Profesional de seguridad"),
    DATA_SCIENCE("Data science"),
    DESIGNER_UX("Designer UX"),
    ESPECIALISTA_REDES("Especialista en redes");

    private String code;

    private Cargo(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
