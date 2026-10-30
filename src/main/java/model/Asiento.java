package model;
import java.util.ArrayList;
import java.util.List;

public class Asiento {

    //Atributos de la clase Asiento
    private String codigo;
    private char fila;
    private int numero;
    EstadoAsiento estado;

    //Relaciones de la clase Asiento
    private Funcion funcion;
    private List<Venta> listAsientoVentas;

    /**
     * metodo constructor de la clase ASiento
     * @param codigo del asiento
     * @param fila del asiento
     * @param numero del asiento
     * @param estado del asiento
     * @param funcion del asiento
     * @param listAsientoVentas del asiento
     */
    public Asiento(String codigo, char fila, int numero, EstadoAsiento estado, Funcion funcion, List<Venta> listAsientoVentas) {
        this.codigo = codigo;
        this.fila = fila;
        this.numero = numero;
        this.estado = estado;
        this.funcion = funcion;
        this.listAsientoVentas = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public char getFila() {
        return fila;
    }

    public void setFila(char fila) {
        this.fila = fila;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public EstadoAsiento getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsiento estado) {
        this.estado = estado;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public List<Venta> getListAsientoVentas() {
        return listAsientoVentas;
    }

    public void setListAsientoVentas(List<Venta> listAsientoVentas) {
        this.listAsientoVentas = listAsientoVentas;
    }

    @Override
    public String toString() {
        return "Asiento{" +
                "codigo='" + codigo + '\'' +
                ", fila=" + fila +
                ", numero=" + numero +
                ", estado=" + estado +
                ", funcion=" + funcion +
                ", listAsientoVentas=" + listAsientoVentas +
                '}';
    }
}
