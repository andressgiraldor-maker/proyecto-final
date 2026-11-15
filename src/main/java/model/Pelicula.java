package model;

public class Pelicula implements Deshabilitable {

    private String codigo;
    private String nombre;
    private GeneroPelicula generoPelicula;
    private int duracionMinutos;
    private ClasificacionPelicula clasificacionPelicula;
    private EstadoPelicula estadoPelicula;

    public Pelicula(String codigo, String nombre, GeneroPelicula generoPelicula, int duracionMinutos, ClasificacionPelicula clasificacionPelicula, EstadoPelicula estadoPelicula) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.generoPelicula = generoPelicula;
        this.duracionMinutos = duracionMinutos;
        this.clasificacionPelicula = clasificacionPelicula;
        this.estadoPelicula = estadoPelicula;
    }

    // ===== LOS 3 DE LA INTERFAZ - TODOS CON @Override =====
    @Override
    public void habilitar() {
        this.estadoPelicula = EstadoPelicula.DISPONIBLE;
    }

    @Override
    public void deshabilitar() {
        this.estadoPelicula = EstadoPelicula.NO_DISPONIBLE;
    }

    @Override
    public boolean estaDeshabilitado() {
        return estadoPelicula == EstadoPelicula.NO_DISPONIBLE;
    }

    // ===== PROPIO DE Pelicula - SIN @Override =====
    public boolean estaDisponible() {
        return estadoPelicula == EstadoPelicula.DISPONIBLE;
    }

    // ===== Getters y Setters =====
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public GeneroPelicula getGeneroPelicula() { return generoPelicula; }
    public void setGeneroPelicula(GeneroPelicula generoPelicula) { this.generoPelicula = generoPelicula; }
    public int getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(int duracionMinutos) { this.duracionMinutos = duracionMinutos; }
    public ClasificacionPelicula getClasificacionPelicula() { return clasificacionPelicula; }
    public void setClasificacionPelicula(ClasificacionPelicula clasificacionPelicula) { this.clasificacionPelicula = clasificacionPelicula; }
    public EstadoPelicula getEstadoPelicula() { return estadoPelicula; }
    public void setEstadoPelicula(EstadoPelicula estadoPelicula) { this.estadoPelicula = estadoPelicula; }

    @Override
    public String toString() {
        return "Pelicula{" + "codigo='" + codigo + '\'' + ", nombre='" + nombre + '\'' + ", estado=" + estadoPelicula + '}';
    }
}