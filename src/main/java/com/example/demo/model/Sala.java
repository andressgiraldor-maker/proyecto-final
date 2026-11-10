package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Sala implements Deshabilitable
{

    private String codigo;
    private String nombre;
    private int capacidad;
    private EstadoSala estado;
    private List<Asiento> asientos;

    public Sala(String codigo, String nombre)
    {
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidad = 0;
        this.estado = EstadoSala.DISPONIBLE;
        this.asientos = new ArrayList<>();
    }

    @Override
    public boolean estaDisponible()
    {
        boolean disponible;
        disponible = estado == EstadoSala.DISPONIBLE;
        return disponible;
    }

    @Override
    public void deshabilitar()
    {
        estado = EstadoSala.FUERA_DE_SERVICIO;
    }

    /**
     * Genera los asientos de la sala: filas con letras (A, B, C...) y columnas numeradas desde 1.
     */
    public void generarAsientos(int filas, int columnas)
    {
        if (filas <= 0 || filas > 26 || columnas <= 0)
        {
            throw new IllegalArgumentException("Cantidad de filas o columnas invalida");
        }

        asientos.clear();

        for (int i = 0; i < filas; i++)
        {
            char letraFila = (char) ('A' + i);
            for (int numero = 1; numero <= columnas; numero++)
            {
                Asiento asiento = new Asiento(letraFila, numero);
                asientos.add(asiento);
            }
        }

        actualizarCapacidad();
    }

    public Asiento buscarAsiento(char fila, int numero)
    {
        Asiento encontrado = null;

        for (Asiento asiento : asientos)
        {
            if (asiento.getFila() == fila && asiento.getNumero() == numero)
            {
                encontrado = asiento;
            }
        }

        return encontrado;
    }

    public void agregarAsiento(char fila, int numero)
    {
        validarPosicion(fila, numero);

        Asiento existente = buscarAsiento(fila, numero);
        if (existente != null)
        {
            throw new IllegalArgumentException("El asiento " + fila + numero + " ya existe en la sala");
        }

        Asiento asiento = new Asiento(fila, numero);
        asientos.add(asiento);
        actualizarCapacidad();
    }

    /**
     * Cambia la posicion (fila y numero) de un asiento existente.
     */
    public void modificarAsiento(char filaActual, int numeroActual, char filaNueva, int numeroNuevo)
    {
        Asiento actual = buscarAsiento(filaActual, numeroActual);
        if (actual == null)
        {
            throw new IllegalArgumentException("El asiento " + filaActual + numeroActual + " no existe en la sala");
        }

        validarPosicion(filaNueva, numeroNuevo);

        Asiento destino = buscarAsiento(filaNueva, numeroNuevo);
        if (destino != null && destino != actual)
        {
            throw new IllegalArgumentException("El asiento " + filaNueva + numeroNuevo + " ya existe en la sala");
        }

        int posicion = asientos.indexOf(actual);
        Asiento nuevo = new Asiento(filaNueva, numeroNuevo);
        asientos.set(posicion, nuevo);
    }

    public void eliminarAsiento(char fila, int numero)
    {
        Asiento asiento = buscarAsiento(fila, numero);

        if (asiento == null)
        {
            throw new IllegalArgumentException("El asiento " + fila + numero + " no existe en la sala");
        }

        asientos.remove(asiento);
        actualizarCapacidad();
    }

    private void validarPosicion(char fila, int numero)
    {
        if (fila < 'A' || fila > 'Z' || numero <= 0)
        {
            throw new IllegalArgumentException("La fila debe ser una letra de la A a la Z y el numero mayor a cero");
        }
    }

    private void actualizarCapacidad()
    {
        capacidad = asientos.size();
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

    public int getCapacidad()
    {
        return capacidad;
    }

    public EstadoSala getEstado()
    {
        return estado;
    }

    public void setEstado(EstadoSala estado)
    {
        this.estado = estado;
    }

    public List<Asiento> getAsientos()
    {
        return asientos;
    }
}
