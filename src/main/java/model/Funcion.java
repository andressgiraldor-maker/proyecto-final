package model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

public class Funcion {

    //Atributos de la clase Funcion
    private String codigo;
    private LocalDate fecha;
    private LocalTime hora;
    private double valorBase;
    private EstadoFuncion estadoFuncion;

    //Relaciones de la clase Funcion
    private List<Asiento> listFuncionAsientos;
    private Cine cine;
    private List<Venta> listFuncionVentas;
    private Pelicula pelicula;
    private Sala sala;

    /**
     * metodo constructor de la clase Funcion
     * @param codigo de la funcion
     * @param fecha de la funcion
     * @param hora de la funcion
     * @param valorBase de la funcion
     * @param estadoFuncion
     */
    public Funcion(String codigo, LocalDate fecha, LocalTime hora, double valorBase, EstadoFuncion estadoFuncion) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.valorBase = valorBase;
        this.estadoFuncion = estadoFuncion;
        this.listFuncionAsientos = new ArrayList<>();
        this.cine = cine;
        this.listFuncionVentas = new ArrayList<>();
        this.pelicula = pelicula;
        this.sala = sala;
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

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public EstadoFuncion getEstadoFuncion() {
        return estadoFuncion;
    }

    public void setEstadoFuncion(EstadoFuncion estadoFuncion) {
        this.estadoFuncion = estadoFuncion;
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

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public String toString() {
        return "Funcion{" +
                "codigo='" + codigo + '\'' +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", valorBase=" + valorBase +
                ", estadoFuncion=" + estadoFuncion +
                ", listFuncionAsientos=" + listFuncionAsientos +
                ", cine=" + cine +
                ", listFuncionVentas=" + listFuncionVentas +
                ", pelicula=" + pelicula +
                ", sala=" + sala +
                '}';
    }
}