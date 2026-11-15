package com.example.demo.model;

public class Pelicula implements Deshabilitable
{

    private String codigo;
    private String nombre;
    private Genero genero;
    private int duracionMinutos;
    private Clasificacion clasificacion;
    private EstadoPelicula estado;

    /**
     * metodo constructor de la clase Pelicula
     * @param codigo de la pelicula
     * @param nombre de la pelicula
     * @param genero de la pelicula
     * @param duracionMinutos de la pelicula
     * @param clasificacion de la pelicula
     */
    public Pelicula(String codigo, String nombre, Genero genero, int duracionMinutos, Clasificacion clasificacion)
    {
        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.duracionMinutos = duracionMinutos;
        this.clasificacion = clasificacion;
        this.estado = EstadoPelicula.DISPONIBLE;
    }

    /**
     * metodo para saber si una pelciual esta disponible
     * @return
     */
    @Override
    public boolean estaDisponible()
    {
        boolean disponible;
        disponible = estado == EstadoPelicula.DISPONIBLE;
        return disponible;
    }

    /**
     * metodo para deshabilitar una pelicula
     */
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
