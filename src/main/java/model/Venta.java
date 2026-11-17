package model;

import java.time.LocalDateTime;

public class Venta {

    private String codigo;
    private Cliente cliente;
    private Funcion funcion;
    private Asiento asiento;
    private TipoEntrada tipoEntrada;
    private LocalDateTime fechaVenta;
    private double valorPagado;
    private EstadoVenta estadoVenta;

    /**
     * metodo constructor de la clase Venta
     * @param codigo de la venta
     * @param cliente de la venta
     * @param funcion de la venta
     * @param asiento de la venta
     * @param tipoEntrada de la vente
     * @param fechaVenta de la venta
     * @param valorPagado de la vente
     */
    public Venta(String codigo, Cliente cliente, Funcion funcion, Asiento asiento, TipoEntrada tipoEntrada, LocalDateTime fechaVenta, double valorPagado) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.funcion = funcion;
        this.asiento = asiento;
        this.tipoEntrada = tipoEntrada;
        this.fechaVenta = fechaVenta;
        this.valorPagado = valorPagado;
        this.estadoVenta = EstadoVenta.ACTIVA; // <-- Aqui se pone, no en el constructor
    }

    /**
     * metodo para saber si una venta esta ctiva
     * @return
     */
    public boolean estaActiva() {
        return estadoVenta == EstadoVenta.ACTIVA;
    }

    /**
     * metodo para cancelar una venta
     */
    public void cancelar() {
        this.estadoVenta = EstadoVenta.CANCELADA;
        if (asiento != null) {
            asiento.liberar();
        }
    }

    // Getters
    public String getCodigo() { return codigo; }
    public Cliente getCliente() { return cliente; }
    public Funcion getFuncion() { return funcion; }
    public Asiento getAsiento() { return asiento; }
    public TipoEntrada getTipoEntrada() { return tipoEntrada; }
    public LocalDateTime getFechaVenta() { return fechaVenta; }
    public double getValorPagado() { return valorPagado; }
    public EstadoVenta getEstadoVenta() { return estadoVenta; }
    public void setEstadoVenta(EstadoVenta estadoVenta) { this.estadoVenta = estadoVenta; }

    @Override
    public String toString() {
        return "Venta{" + "codigo='" + codigo + '\'' + ", valor=" + valorPagado + ", estado=" + estadoVenta + '}';
    }
}