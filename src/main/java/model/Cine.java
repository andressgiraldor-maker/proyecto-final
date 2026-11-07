package model;

import java.util.ArrayList;
import java.util.List;

public class Cine {

    private String nombre;
    private String direccion;

    private List<Funcion> listCineFunciones;
    private List<Venta> listCineVentas;
    private List<Pelicula> listCinePeliculas;
    private List<Sala> listCineSalas;

    public Cine(String nombre, String direccion, List<Funcion> listCineFunciones, List<Venta> listCineVentas, List<Pelicula> listCinePeliculas, List<Sala> listCineSalas) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.listCineFunciones = new ArrayList<>();
        this.listCineVentas = new ArrayList<>();
        this.listCinePeliculas = new ArrayList<>();
        this.listCineSalas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Funcion> getListCineFunciones() {
        return listCineFunciones;
    }

    public void setListCineFunciones(List<Funcion> listCineFunciones) {
        this.listCineFunciones = listCineFunciones;
    }

    public List<Venta> getListCineVentas() {
        return listCineVentas;
    }

    public void setListCineVentas(List<Venta> listCineVentas) {
        this.listCineVentas = listCineVentas;
    }

    public List<Pelicula> getListCinePeliculas() {
        return listCinePeliculas;
    }

    public void setListCinePeliculas(List<Pelicula> listCinePeliculas) {
        this.listCinePeliculas = listCinePeliculas;
    }

    public List<Sala> getListCineSalas() {
        return listCineSalas;
    }

    public void setListCineSalas(List<Sala> listCineSalas) {
        this.listCineSalas = listCineSalas;
    }

    @Override
    public String toString() {
        return "Cine{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", listCineFunciones=" + listCineFunciones +
                ", listCineVentas=" + listCineVentas +
                ", listCinePeliculas=" + listCinePeliculas +
                ", listCineSalas=" + listCineSalas +
                '}';
    }
}