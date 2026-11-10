package com.example.demo.model;

public abstract class Persona
{

    private String identificacion;
    private String nombre;

    /**
     * metodo constructor de la clase Persona
     * @param identificacion de la persona
     * @param nombre de la persona
     */
    public Persona(String identificacion, String nombre)
    {
        this.identificacion = identificacion;
        this.nombre = nombre;
    }

    /**
     * metodo para obtener el rol de una persona
     * @return
     */
    public abstract String obtenerRol();

    public String getIdentificacion()
    {
        return identificacion;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
}
