package model;

public class Administrador extends Persona {

    private String codigoAdministrador;

    public Administrador(String identificacion, String nombre, String codigoAdministrador) {
        super(identificacion, nombre);
        this.codigoAdministrador = codigoAdministrador;
    }

    @Override
    public String obtenerRol() {
        return "ADMINISTRADOR";
    }

    public String getCodigoAdministrador() { return codigoAdministrador; }
    public void setCodigoAdministrador(String codigoAdministrador) { this.codigoAdministrador = codigoAdministrador; }

    @Override
    public String toString() {
        return "Administrador{" + "identificacion='" + getIdentificacion() + '\'' + ", nombre='" + getNombre() + '\'' + ", codigoAdministrador='" + codigoAdministrador + '\'' + '}';
    }
}