package com.example.demo.model;

public class Asiento
{

    private String codigo;
    private char fila;
    private int numero;
    private EstadoAsiento estado;

    /**
     * metodo constructor de la clase Asiento
     * @param fila del asiento
     * @param numero del asiento
     */
    public Asiento(char fila, int numero)
    {
        String codigoGenerado;
        codigoGenerado = fila + String.valueOf(numero);

        this.codigo = codigoGenerado;
        this.fila = fila;
        this.numero = numero;
        this.estado = EstadoAsiento.DISPONIBLE;
    }

    /**
     * metodo para saber si una siento esta disponible
     * @return
     */
    public boolean estaDisponible()
    {
        boolean disponible;
        disponible = estado == EstadoAsiento.DISPONIBLE;
        return disponible;
    }

    /**
     * metodo para ocupar un asiento
     */
    public void ocupar()
    {
        if (estado == EstadoAsiento.OCUPADO)
        {
            throw new IllegalStateException("El asiento " + codigo + " ya esta ocupado");
        }
        estado = EstadoAsiento.OCUPADO;
    }

    /**
     * metodo para liberar un asiento
     */
    public void liberar()
    {
        estado = EstadoAsiento.DISPONIBLE;
    }

    public String getCodigo()
    {
        return codigo;
    }

    public char getFila()
    {
        return fila;
    }

    public int getNumero()
    {
        return numero;
    }

    public EstadoAsiento getEstado()
    {
        return estado;
    }

    public void setEstado(EstadoAsiento estado)
    {
        this.estado = estado;
    }
}
