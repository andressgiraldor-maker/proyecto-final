package model;

public class Pelicula implements Deshabilitable {

    private String codigo;
    private String nombre;
    private GeneroPelicula generoPelicula;
    private int duracionMinutos;
    private ClasificacionPelicula clasificacionPelicula;
    private EstadoPelicula estadoPelicula;

    /**
     * metodo constructor de la clase Pelicula
     * @param codigo de la pelicula
     * @param nombre de la pelicula
     * @param generoPelicula de la pelicula
     * @param duracionMinutos de la pelicula
     * @param clasificacionPelicula de la pelicula
     * @param estadoPelicula de la pelicula
     */
    public Pelicula(String codigo, String nombre, GeneroPelicula generoPelicula, int duracionMinutos, ClasificacionPelicula clasificacionPelicula, EstadoPelicula estadoPelicula) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.generoPelicula = generoPelicula;
        this.duracionMinutos = duracionMinutos;
        this.clasificacionPelicula = clasificacionPelicula;
        this.estadoPelicula = estadoPelicula;
    }

    /**
     * metodo para habilitar una pelicula
     */
    @Override
    public void habilitar() {
        this.estadoPelicula = EstadoPelicula.DISPONIBLE;
    }

    /**
     * metodo para deshabilitar una pelicula
     */
    @Override
    public void deshabilitar() {
        this.estadoPelicula = EstadoPelicula.NO_DISPONIBLE;
    }

    /**
     * meetodo para saber si una pelicula esta deshbailitado
     * @return
     */
    @Override
    public boolean estaDeshabilitado() {
        return estadoPelicula == EstadoPelicula.NO_DISPONIBLE;
    }

    /**
     * metodo para saber si una pelicula esa disponibles
     * @return
     */
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