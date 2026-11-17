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
    private List<Venta> listFuncionVentas;
    private Pelicula pelicula;
    private Sala sala;

    /**
     * metodo constructor de la clas Funcion
     * @param codigo de la funcion
     * @param fecha de la funcion
     * @param hora de la funcion
     * @param valorBase de la funcion
     * @param estadoFuncion de la funcion
     * @param pelicula de la funcion
     * @param sala de la funcion
     */
    public Funcion(String codigo, LocalDate fecha, LocalTime hora, double valorBase, EstadoFuncion estadoFuncion, Pelicula pelicula, Sala sala) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.valorBase = valorBase;
        this.estadoFuncion = estadoFuncion;
        this.pelicula = pelicula;
        this.sala = sala;
        this.listFuncionAsientos = new ArrayList<>();
        this.listFuncionVentas = new ArrayList<>();

        if (sala != null) {
            for (Asiento a : sala.getListSalaAsientos()) {
                listFuncionAsientos.add(new Asiento(a.getCodigo(), a.getFila(), a.getNumero(), EstadoAsiento.DISPONIBLE));
            }
        }
    }


    /**
     * metodo para saber si una funcion esta programada
     * @return
     */
    public boolean estaProgramada() {
        return estadoFuncion == EstadoFuncion.PROGRAMADA;
    }

    /**
     * metodo para saber si una funcion esta disponibles
     * @return
     */
    public boolean estaDisponible() {
        return estaProgramada();
    }

    /**
     * metodo para buscar un asiento por su fila y columna
     * @param fila del asiento
     * @param numero del asiento
     * @return
     */
    public Asiento buscarAsiento(char fila, int numero) {
        for (Asiento a : listFuncionAsientos) {
            if (a.getFila() == fila && a.getNumero() == numero) {
                return a;
            }
        }
        return null;
    }

    /**
     * metodo para contar asientos disponibles que tenga la funcion
     * @return
     */
    public int contarAsientosDisponibles() {
        int total = 0;
        for (Asiento a : listFuncionAsientos) {
            if (a.estaDisponible()) total++;
        }
        return total;
    }

    /**
     * metodo para contar los asiento ocupados de la funcion
     * @return
     */
    public int contarAsientosOcupados() {
        return listFuncionAsientos.size() - contarAsientosDisponibles();
    }

    /**
     * metodo para calcular el porcentaje de ocupacion de una funcion
     * @return
     */
    public double calcularPorcentajeOcupacion() {
        if (listFuncionAsientos.isEmpty()) return 0;
        return (contarAsientosOcupados() * 100.0) / listFuncionAsientos.size();
    }

    /**
     * metodo para obtener los asientos disponibles en una lista
     * @return
     */
    public List<Asiento> getAsientosDisponibles() {
        List<Asiento> disponibles = new ArrayList<>();
        for (Asiento a : listFuncionAsientos) {
            if (a.estaDisponible()) {
                disponibles.add(a);
            }
        }
        return disponibles;
    }

    // ===== Getters y Setters =====

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
                ", pelicula=" + (pelicula != null ? pelicula.getNombre() : "sin pelicula") +
                ", sala=" + (sala != null ? sala.getCodigo() : "sin sala") +
                ", asientos=" + listFuncionAsientos.size() +
                '}';
    }
}