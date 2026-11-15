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
     * UNICO constructor de la clase Funcion
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

        // Copia los asientos de la sala para esta funcion
        // Se copian, no se usan los mismos, para que ocupar en una funcion no afecte a otra
        if (sala != null) {
            for (Asiento a : sala.getListSalaAsientos()) {
                listFuncionAsientos.add(new Asiento(a.getCodigo(), a.getFila(), a.getNumero(), EstadoAsiento.DISPONIBLE));
            }
        }
    }

    // ===== METODOS DEL DIAGRAMA =====

    public boolean estaProgramada() {
        return estadoFuncion == EstadoFuncion.PROGRAMADA;
    }

    // Alias para que Cine viejo no falle, es lo mismo que estaProgramada()
    public boolean estaDisponible() {
        return estaProgramada();
    }

    public Asiento buscarAsiento(char fila, int numero) {
        for (Asiento a : listFuncionAsientos) {
            if (a.getFila() == fila && a.getNumero() == numero) {
                return a;
            }
        }
        return null;
    }

    public int contarAsientosDisponibles() {
        int total = 0;
        for (Asiento a : listFuncionAsientos) {
            if (a.estaDisponible()) total++;
        }
        return total;
    }

    public int contarAsientosOcupados() {
        return listFuncionAsientos.size() - contarAsientosDisponibles();
    }

    public double calcularPorcentajeOcupacion() {
        if (listFuncionAsientos.isEmpty()) return 0;
        return (contarAsientosOcupados() * 100.0) / listFuncionAsientos.size();
    }

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