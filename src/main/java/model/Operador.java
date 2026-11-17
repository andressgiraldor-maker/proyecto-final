package model;

public class Operador extends Persona {

    private String codigoEmpleado;

    /**
     * metodo constructor de la clase Operador
     * @param identificacion del operador
     * @param nombre del operador
     * @param codigoEmpleado del operador
     */
    public Operador(String identificacion, String nombre, String codigoEmpleado) {
        super(identificacion, nombre);
        if (codigoEmpleado == null || codigoEmpleado.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo del empleado no puede estar vacio");
        }
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    /**
     * metodo para modificar el codigo del operador
     * @param codigoEmpleado del operador
     */
    public void setCodigoEmpleado(String codigoEmpleado) {
        if (codigoEmpleado == null || codigoEmpleado.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo del empleado no puede estar vacio");
        }
        this.codigoEmpleado = codigoEmpleado;
    }

    /**
     * metodo para obtner el nombre del operador
     * @return
     */
    @Override
    public String obtenerRol() {
        return "Operador";
    }

    @Override
    public String toString() {
        return "Operador{" +
                "identificacion='" + getIdentificacion() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", codigoEmpleado='" + codigoEmpleado + '\'' +
                '}';
    }
}