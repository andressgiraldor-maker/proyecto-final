package model;

public class Operador extends Persona {

    private String codigoEmpleado;

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

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    @Override
    public String obtenerRol() {
        return "Operador";
    }

    @Override
    public String toString() {
        return "Operador{" +
                "codigoEmpleado='" + codigoEmpleado + '\'' +
                '}';
    }
}