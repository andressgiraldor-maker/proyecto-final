package com.example.demo.model;

public class Pelicula implements Deshabilitable
{

    private String codigo;
    private String nombre;
    private Genero genero;
    private int duracionMinutos;
    private Clasificacion clasificacion;
    private EstadoPelicula estado;

    public Pelicula(String codigo, String nombre, Genero genero, int duracionMinutos, Clasificacion clasificacion)
    {
        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.duracionMinutos = duracionMinutos;
        this.clasificacion = clasificacion;
        this.estado = EstadoPelicula.DISPONIBLE;
    }

    @Override
    public boolean estaDisponible()
    {
        boolean disponible;
        disponible = estado == EstadoPelicula.DISPONIBLE;
        return disponible;
    }

    @Override
    public void deshabilitar()
    {
        estado = EstadoPelicula.NO_DISPONIBLE;
    }

    public String getCodigo()
    {
        return codigo;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public Genero getGenero()
    {
        return genero;
    }

    public void setGenero(Genero genero)
    {
        this.genero = genero;
    }

    public int getDuracionMinutos()
    {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos)
    {
        this.duracionMinutos = duracionMinutos;
    }

    public Clasificacion getClasificacion()
    {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion)
    {
        this.clasificacion = clasificacion;
    }

    public EstadoPelicula getEstado()
    {
        return estado;
    }

    public void setEstado(EstadoPelicula estado)
    {
        this.estado = estado;
    }
}
