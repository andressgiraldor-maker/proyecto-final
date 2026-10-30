package model;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

public class Funcion {

    //Atributos de la clase Funcion
    private String codigo;
    private LocalDate fecha;
    private LocalDate hora;
    private double valorBase;
    EsatdoFuncion esatdoFuncion;

    //Relaciones de la calse Funcion
    private List<Asiento> listFuncionAsientos;
    private Cine cine;
    private List<Venta> listFuncionVentas;
    private Pelicula pelicula;

    /**
     * metodo constructor de la clase Funcion
     * @param codigo de la funcion
     * @param fecha de la funcion
     * @param hora de la funcion
     * @param valorBase de la funcion
     * @param esatdoFuncion de la funcion
     * @param listFuncionAsientos de la funcion
     * @param cine de la funcion
     * @param listFuncionVentas de la funcion
     * @param pelicula de la funcion
     */
    public Funcion(String codigo, LocalDate fecha, LocalDate hora, double valorBase, EsatdoFuncion esatdoFuncion, List<Asiento> listFuncionAsientos, Cine cine, List<Venta> listFuncionVentas, Pelicula pelicula) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.valorBase = valorBase;
        this.esatdoFuncion = esatdoFuncion;
        this.listFuncionAsientos = new ArrayList<>();
        this.cine = cine;
        this.listFuncionVentas =  new ArrayList<>();
        this.pelicula = pelicula;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDate getHora() {
        return hora;
    }

    public void setHora(LocalDate hora) {
        this.hora = hora;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public EsatdoFuncion getEsatdoFuncion() {
        return esatdoFuncion;
    }

    public void setEsatdoFuncion(EsatdoFuncion esatdoFuncion) {
        this.esatdoFuncion = esatdoFuncion;
    }

    public List<Asiento> getListFuncionAsientos() {
        return listFuncionAsientos;
    }

    public void setListFuncionAsientos(List<Asiento> listFuncionAsientos) {
        this.listFuncionAsientos = listFuncionAsientos;
    }

    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }

    public List<Venta> getListFuncionVentas() {
        return listFuncionVentas;
    }

    public void setListFuncionVentas(List<Venta> listFuncionVentas) {
        this.listFuncionVentas = listFuncionVentas;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    @Override
    public String toString() {
        return "Funcion{" +
                "codigo='" + codigo + '\'' +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", valorBase=" + valorBase +
                ", esatdoFuncion=" + esatdoFuncion +
                ", listFuncionAsientos=" + listFuncionAsientos +
                ", cine=" + cine +
                ", listFuncionVentas=" + listFuncionVentas +
                ", pelicula=" + pelicula +
                '}';
    }
}
