package model;

public class Asiento {
    private String codigo;
    private char fila;
    private int numero;
    private EstadoAsiento estadoAsiento;

    /**
     * metodo constructor de la clase Asiento
     * @param codigo del asiento
     * @param fila del asiento
     * @param numero del asiento
     * @param estadoAsiento del asiento
     */
    public Asiento(String codigo, char fila, int numero, EstadoAsiento estadoAsiento) {
        this.codigo = codigo;
        this.fila = fila;
        this.numero = numero;
        this.estadoAsiento = estadoAsiento != null ? estadoAsiento : EstadoAsiento.DISPONIBLE;
    }

    /**
     * metodo para saber si un asiento esta disponibles
     * @return
     */
    public boolean estaDisponible() {
        return estadoAsiento == EstadoAsiento.DISPONIBLE;
    }

    /**
     * metodo para ocupar un asiento
     */
    public void ocupar() { this.estadoAsiento = EstadoAsiento.OCUPADO; }

    /**
     * metodo para liberar un asiento
     */
    public void liberar() { this.estadoAsiento = EstadoAsiento.DISPONIBLE; }

    public String getCodigo() { return codigo; }
    public char getFila() { return fila; }
    public int getNumero() { return numero; }
    public EstadoAsiento getEstadoAsiento() { return estadoAsiento; }
    public void setEstadoAsiento(EstadoAsiento estadoAsiento) { this.estadoAsiento = estadoAsiento; }
}