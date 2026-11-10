package com.example.demo.model;

public abstract class Persona
{

    private String identificacion;
    private String nombre;

    public Persona(String identificacion, String nombre)
    {
        this.identificacion = identificacion;
        this.nombre = nombre;
    }

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
