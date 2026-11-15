package model;

import java.util.ArrayList;
import java.util.List;

public class Sala implements Deshabilitable {

    private String codigo;
    private String nombre;
    private int capacidad;
    private EstadoSala estadoSala;
    private List<Asiento> listSalaAsientos;

    public Sala(String codigo, String nombre, int capacidad, EstadoSala estadoSala) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.estadoSala = estadoSala;
        this.listSalaAsientos = new ArrayList<>();
    }

    // ===== LOS 3 DE LA INTERFAZ Deshabilitable - TODOS CON @Override =====
    @Override
    public void habilitar() {
        this.estadoSala = EstadoSala.DISPONIBLE;
    }

    @Override
    public void deshabilitar() {
        this.estadoSala = EstadoSala.FUERA_DE_SERVICIO;
    }

    @Override
    public boolean estaDeshabilitado() {
        return estadoSala == EstadoSala.FUERA_DE_SERVICIO;
    }

    // ===== PROPIO DE Sala - SIN @Override =====
    // Quitale el @Override a este, por eso te salia "Method does not override"
    public boolean estaDisponible() {
        return estadoSala == EstadoSala.DISPONIBLE;
    }

    // ===== Generar asientos - ARREGLADO: Asiento ahora recibe 4 argumentos, no 5 =====
    public void generarAsientos(int filas, int columnas) {
        listSalaAsientos.clear();
        for (int f = 0; f < filas; f++) {
            char filaChar = (char) ('A' + f);
            for (int n = 1; n <= columnas; n++) {
                String codigoAsiento = codigo + "-" + filaChar + n;
                // ANTES (mal): new Asiento(codigoAsiento, filaChar, n, null, DISPONIBLE) -> 5 args
                // AHORA (bien): 4 args
                listSalaAsientos.add(new Asiento(codigoAsiento, filaChar, n, EstadoAsiento.DISPONIBLE));
            }
        }
        this.capacidad = listSalaAsientos.size();
    }

    // ===== Getters y Setters =====
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public EstadoSala getEstadoSala() { return estadoSala; }
    public void setEstadoSala(EstadoSala estadoSala) { this.estadoSala = estadoSala; }
    public List<Asiento> getListSalaAsientos() { return listSalaAsientos; }
    public void setListSalaAsientos(List<Asiento> listSalaAsientos) { this.listSalaAsientos = listSalaAsientos; }

    @Override
    public String toString() {
        return "Sala{" + "codigo='" + codigo + '\'' + ", nombre='" + nombre + '\'' + ", capacidad=" + capacidad + ", estado=" + estadoSala + '}';
    }
}