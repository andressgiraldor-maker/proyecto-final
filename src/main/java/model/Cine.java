package model;

import java.util.List;

public class Cine {

    //Atributos de la clase Cine
    private String nombre;
    private String direccion;

    //Relaciones de la clase Cine
    private List<Funcion> listCineFunciones;
    private List<Venta> listCineVentas;
    private List<Cine> listCinePeliculas;
    // me sale erro al poner <Sala> private List<Sala> listCineSalas;

    /**
     * constructor de la clase Cine
     * @param nombre
     * @param direccion
     * @param listCineFunciones
     * @param listCineVentas
     * @param listCinePeliculas
     */
    public Cine(String nombre, String direccion, List<Funcion> listCineFunciones, List<Venta> listCineVentas, List<Cine> listCinePeliculas) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.listCineFunciones = listCineFunciones;
        this.listCineVentas = listCineVentas;
        this.listCinePeliculas = listCinePeliculas;
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

    public List<Cine> getListCinePeliculas() {
        return listCinePeliculas;
    }

    public void setListCinePeliculas(List<Cine> listCinePeliculas) {
        this.listCinePeliculas = listCinePeliculas;
    }

    @Override
    public String toString() {
        return "Cine{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", listCineFunciones=" + listCineFunciones +
                ", listCineVentas=" + listCineVentas +
                ", listCinePeliculas=" + listCinePeliculas +
                '}';
    }
}
