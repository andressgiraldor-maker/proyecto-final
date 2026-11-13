package model;

import java.util.Objects;

public abstract class Persona {

    private String identificacion;
    private String nombre;

    /**
     * metodo para crear perosna
     *
     * @param identificacion de la persona
     * @param nombre         de la persona
     */
    public Persona(String identificacion, String nombre) {
        if (identificacion == null || identificacion.trim().isEmpty()) {
            throw new IllegalArgumentException("La identificacion no puede estar vacia");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        this.identificacion = identificacion;
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public abstract String obtenerRol();

    @Override
    public String toString() {
        return "Persona{" +
                "identificacion='" + identificacion + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}