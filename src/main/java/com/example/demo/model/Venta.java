package com.example.demo.model;

import java.time.LocalDateTime;

public class Venta
{

    private String codigo;
    private LocalDateTime fechaHoraVenta;
    private double valorPagado;
    private EstadoVenta estado;
    private Cliente cliente;
    private Operador operador;
    private Funcion funcion;
    private Asiento asiento;
    private Tarifa tarifa;

    /**
     * metodo constructor de la classe Venta
     * @param codigo de la venta
     * @param cliente de la venta
     * @param funcion de la vente
     * @param asiento de la evnta
     * @param tarifa de la venta
     * @param valorPagado de la venta
     */
    public Venta(String codigo, Cliente cliente, Funcion funcion, Asiento asiento, Tarifa tarifa, double valorPagado)
    {
        this.codigo = codigo;
        this.cliente = cliente;
        this.funcion = funcion;
        this.asiento = asiento;
        this.tarifa = tarifa;
        this.valorPagado = valorPagado;
        this.fechaHoraVenta = LocalDateTime.now();
        this.estado = EstadoVenta.ACTIVA;
    }

    /**
     * metodo para saber si una venta esta activa
     * @return
     */
    public boolean estaActiva()
    {
        boolean activa;
        activa = estado == EstadoVenta.ACTIVA;
        return activa;
    }

    /**
     * Cancela la venta y libera el asiento asociado.
     */
    public void cancelar()
    {
        if (estado == EstadoVenta.CANCELADA)
        {
            throw new IllegalStateException("La venta " + codigo + " ya fue cancelada");
        }
        estado = EstadoVenta.CANCELADA;
        asiento.liberar();
    }

    /**
     * metodo para generar una entrada
     * @return
     */
    public Entrada generarEntrada()
    {
        String nombrePelicula = funcion.getPelicula().getNombre();
        String nombreSala = funcion.getSala().getNombre();
        String codigoAsiento = asiento.getCodigo();
        String nombreTipo = tarifa.tipo().name();

        Entrada entrada = new Entrada(
                codigo,
                nombrePelicula,
                nombreSala,
                codigoAsiento,
                funcion.getFecha(),
                funcion.getHora(),
                nombreTipo,
                valorPagado);

        return entrada;
    }

    public String getCodigo()
    {
        return codigo;
    }

    public LocalDateTime getFechaHoraVenta()
    {
        return fechaHoraVenta;
    }

    public double getValorPagado()
    {
        return valorPagado;
    }

    public EstadoVenta getEstado()
    {
        return estado;
    }

    public Cliente getCliente()
    {
        return cliente;
    }

    public Operador getOperador()
    {
        return operador;
    }

    public void setOperador(Operador operador)
    {
        this.operador = operador;
    }

    public Funcion getFuncion()
    {
        return funcion;
    }

    public Asiento getAsiento()
    {
        return asiento;
    }

    public Tarifa getTarifa()
    {
        return tarifa;
    }
}
