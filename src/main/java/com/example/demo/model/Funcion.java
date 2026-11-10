package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Funcion
{

    private String codigo;
    private LocalDate fecha;
    private LocalTime hora;
    private double valorBase;
    private EstadoFuncion estado;
    private Pelicula pelicula;
    private Sala sala;
    private List<Asiento> asientos;

    /**
     * Cada funcion tiene su propia copia de los asientos de la sala,
     * asi la ocupacion de una funcion no afecta a las demas.
     */
    public Funcion(String codigo, Pelicula pelicula, Sala sala, LocalDate fecha, LocalTime hora, double valorBase)
    {
        if (!sala.estaDisponible())
        {
            throw new IllegalStateException("La sala " + sala.getNombre() + " esta fuera de servicio");
        }

        this.codigo = codigo;
        this.pelicula = pelicula;
        this.sala = sala;
        this.fecha = fecha;
        this.hora = hora;
        this.valorBase = valorBase;
        this.estado = EstadoFuncion.PROGRAMADA;
        this.asientos = new ArrayList<>();

        for (Asiento asientoSala : sala.getAsientos())
        {
            Asiento copia = new Asiento(asientoSala.getFila(), asientoSala.getNumero());
            asientos.add(copia);
        }
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

    public int contarAsientosDisponibles()
    {
        int contador = 0;

        for (Asiento asiento : asientos)
        {
            if (asiento.getEstado() == EstadoAsiento.DISPONIBLE)
            {
                contador++;
            }
        }

        return contador;
    }

    public int contarAsientosOcupados()
    {
        int contador = 0;

        for (Asiento asiento : asientos)
        {
            if (asiento.getEstado() == EstadoAsiento.OCUPADO)
            {
                contador++;
            }
        }

        return contador;
    }

    public double calcularPorcentajeOcupacion()
    {
        double porcentaje = 0;
        int total = asientos.size();

        if (total > 0)
        {
            int ocupados = contarAsientosOcupados();
            porcentaje = (double) ocupados / total * 100;
        }

        return porcentaje;
    }

    /**
     * El descuento es una fraccion entre 0 y 1 (0.2 = 20% de descuento).
     */
    public double calcularValorEntrada(double descuento)
    {
        double valor;
        valor = valorBase - (valorBase * descuento);
        return valor;
    }

    public boolean estaProgramada()
    {
        boolean programada;
        programada = estado == EstadoFuncion.PROGRAMADA;
        return programada;
    }

    public void cancelar()
    {
        estado = EstadoFuncion.CANCELADA;
    }

    public void finalizar()
    {
        estado = EstadoFuncion.FINALIZADA;
    }

    public String getCodigo()
    {
        return codigo;
    }

    public LocalDate getFecha()
    {
        return fecha;
    }

    public void setFecha(LocalDate fecha)
    {
        this.fecha = fecha;
    }

    public LocalTime getHora()
    {
        return hora;
    }

    public void setHora(LocalTime hora)
    {
        this.hora = hora;
    }

    public double getValorBase()
    {
        return valorBase;
    }

    public void setValorBase(double valorBase)
    {
        this.valorBase = valorBase;
    }

    public EstadoFuncion getEstado()
    {
        return estado;
    }

    public Pelicula getPelicula()
    {
        return pelicula;
    }

    public Sala getSala()
    {
        return sala;
    }

    public List<Asiento> getAsientos()
    {
        return asientos;
    }
}
