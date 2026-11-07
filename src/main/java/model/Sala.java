package model;

import java.util.ArrayList;
import java.util.List;

public class Sala implements Deshabilitable {

    //Atributos de la clase Sala
    private String codigo;
    private String nombre;
    private int capacidad;
    EstadoSala estadoSala;

    //Relaicones de la clase Sala
    private List<Funcion> listSalaFunciones;
    private List<Asiento> listSalaAsientos;
    private Cine cine;

    /**
     * metodo constructor de la clase Sala
     * @param codigo de la sala
     * @param nombre de la sala
     * @param capacidad de la sala
     * @param estadoSala de la sala
     * @param listSalaFunciones de la sala
     * @param listSalaAsientos de la sala
     * @param cine de la sala
     */
    public Sala(String codigo, String nombre, int capacidad, EstadoSala estadoSala, List<Funcion> listSalaFunciones, List<Asiento> listSalaAsientos, Cine cine) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.estadoSala = estadoSala;
        this.listSalaFunciones = new ArrayList<>();
        this.listSalaAsientos = new ArrayList<>();
        this.cine = cine;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public EstadoSala getEstadoSala() {
        return estadoSala;
    }

    public void setEstadoSala(EstadoSala estadoSala) {
        this.estadoSala = estadoSala;
    }

    public List<Funcion> getListSalaFunciones() {
        return listSalaFunciones;
    }

    public void setListSalaFunciones(List<Funcion> listSalaFunciones) {
        this.listSalaFunciones = listSalaFunciones;
    }

    public List<Asiento> getListSalaAsientos() {
        return listSalaAsientos;
    }

    public void setListSalaAsientos(List<Asiento> listSalaAsientos) {
        this.listSalaAsientos = listSalaAsientos;
    }

    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", capacidad=" + capacidad +
                ", estadoSala=" + estadoSala +
                ", listSalaFunciones=" + listSalaFunciones +
                ", listSalaAsientos=" + listSalaAsientos +
                ", cine=" + cine +
                '}';
    }
}
