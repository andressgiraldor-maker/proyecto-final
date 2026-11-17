package model;

public class Administrador extends Persona {

    private String codigoAdministrador;

    /**
     * metodo constructor de la clase administrador
     * @param identificacion del admin
     * @param nombre del admin
     * @param codigoAdministrador del admin
     */
    public Administrador(String identificacion, String nombre, String codigoAdministrador) {
        super(identificacion, nombre);
        this.codigoAdministrador = codigoAdministrador;
    }

    /**
     * metodo para obtener el rol del admin
     * @return
     */
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