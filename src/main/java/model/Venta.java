package model;

import java.time.LocalDate;

public class Venta {

    //Atributos de la clase Venta
    private String codigo;
    private LocalDate fechaHoraVenta;
    private double valorPagado;
    private EstadoVenta estadoVenta;

    //Relaciones de la clase Venta
    private Funcion funcion;
    private Cine cine;
    private Asiento asiento;
    private Cliente cliente;

    /**
     * metodo constructor de la clase Venta
     */
    public Venta(String codigo, LocalDate fechaHoraVenta, double valorPagado, EstadoVenta estadoVenta, Funcion funcion, Cine cine, Asiento asiento, Cliente cliente) {
        this.codigo = codigo;
        this.fechaHoraVenta = fechaHoraVenta;
        this.valorPagado = valorPagado;
        this.estadoVenta = estadoVenta;
        this.funcion = funcion;
        this.cine = cine;
        this.asiento = asiento;
        this.cliente = cliente;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaHoraVenta() {
        return fechaHoraVenta;
    }

    public void setFechaHoraVenta(LocalDate fechaHoraVenta) {
        this.fechaHoraVenta = fechaHoraVenta;
    }

    public double getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(double valorPagado) {
        this.valorPagado = valorPagado;
    }

    public EstadoVenta getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(EstadoVenta estadoVenta) {
        this.estadoVenta = estadoVenta;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "codigo='" + codigo + '\'' +
                ", fechaHoraVenta=" + fechaHoraVenta +
                ", valorPagado=" + valorPagado +
                ", estadoVenta=" + estadoVenta +
                ", funcion=" + funcion +
                ", cine=" + cine +
                ", asiento=" + asiento +
                ", cliente=" + cliente +
                '}';
    }
}