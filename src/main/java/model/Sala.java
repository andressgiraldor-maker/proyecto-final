package model;

import java.util.ArrayList;
import java.util.List;

public class Sala implements Deshabilitable {

    private String codigo;
    private String nombre;
    private int capacidad;
    private EstadoSala estadoSala;
    private List<Asiento> listSalaAsientos;

    /**
     * metodo constructor de la clase Sala
     * @param codigo de la sala
     * @param nombre de la sala
     * @param capacidad de la sala
     * @param estadoSala de la sala
     */
    public Sala(String codigo, String nombre, int capacidad, EstadoSala estadoSala) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.estadoSala = estadoSala;
        this.listSalaAsientos = new ArrayList<>();
    }

    /**
     * metodo para habilitar una sala
     */
    @Override
    public void habilitar() {
        this.estadoSala = EstadoSala.DISPONIBLE;
    }

    /**
     * metodo para dehabilitar una sala
     */
    @Override
    public void deshabilitar() {
        this.estadoSala = EstadoSala.FUERA_DE_SERVICIO;
    }

    /**
     * metodo para saber si una sala esta deshabilitada
     * @return
     */
    @Override
    public boolean estaDeshabilitado() {
        return estadoSala == EstadoSala.FUERA_DE_SERVICIO;
    }

    /**
     * metodo para saber si una sala esta disponible
     * @return
     */
    public boolean estaDisponible() {
        return estadoSala == EstadoSala.DISPONIBLE;
    }

    /**
     * metodo para generar asientos (se multiplica filas por columnas)
     * @param filas del asiento
     * @param columnas del asiento
     */
    public void generarAsientos(int filas, int columnas) {
        listSalaAsientos.clear();
        for (int f = 0; f < filas; f++) {
            char filaChar = (char) ('A' + f);
            for (int n = 1; n <= columnas; n++) {
                String codigoAsiento = codigo + "-" + filaChar + n;

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