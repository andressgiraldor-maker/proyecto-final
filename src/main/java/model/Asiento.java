package model;

public class Asiento {
    private String codigo;
    private char fila;
    private int numero;
    private EstadoAsiento estadoAsiento;

    public Asiento(String codigo, char fila, int numero, EstadoAsiento estadoAsiento) {
        this.codigo = codigo;
        this.fila = fila;
        this.numero = numero;
        this.estadoAsiento = estadoAsiento != null ? estadoAsiento : EstadoAsiento.DISPONIBLE;
    }

    public boolean estaDisponible() {
        return estadoAsiento == EstadoAsiento.DISPONIBLE;
    }

    public void ocupar() { this.estadoAsiento = EstadoAsiento.OCUPADO; }
    public void liberar() { this.estadoAsiento = EstadoAsiento.DISPONIBLE; }

    public String getCodigo() { return codigo; }
    public char getFila() { return fila; }
    public int getNumero() { return numero; }
    public EstadoAsiento getEstadoAsiento() { return estadoAsiento; }
    public void setEstadoAsiento(EstadoAsiento estadoAsiento) { this.estadoAsiento = estadoAsiento; }
}