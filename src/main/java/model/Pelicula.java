package model;
import java.util.ArrayList;
import java.util.List;

public class Pelicula implements Deshabilitable {

    //Atributos de la clase Pelicula
    private String codigo;
    private String nombre;
    private int duracionMinutos;
    GeneroPelicula generoPelicula;
    EstadoPelicula estadoPelicula;
    ClasificacionPelicula clasificacionPelicula;

    //Relaciones de la clase Pelicula
    private Cine cine;
    private List<Funcion> listPeliculasFunciones;

    /**
     * metodo constructor de la clase Pelicula
     * @param codigo de la pelicula
     * @param nombre de la pelicula
     * @param duracionMinutos de la pelciula
     */
    public Pelicula(String codigo, String nombre, int duracionMinutos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.duracionMinutos = duracionMinutos;
        this.cine = cine;
        this.listPeliculasFunciones = new ArrayList<>();
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

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public GeneroPelicula getGeneroPelicula() {
        return generoPelicula;
    }

    public void setGeneroPelicula(GeneroPelicula generoPelicula) {
        this.generoPelicula = generoPelicula;
    }

    public EstadoPelicula getEstadoPelicula() {
        return estadoPelicula;
    }

    public void setEstadoPelicula(EstadoPelicula estadoPelicula) {
        this.estadoPelicula = estadoPelicula;
    }

    public ClasificacionPelicula getClasificacionPelicula() {
        return clasificacionPelicula;
    }

    public void setClasificacionPelicula(ClasificacionPelicula clasificacionPelicula) {
        this.clasificacionPelicula = clasificacionPelicula;
    }

    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }

    public List<Funcion> getListPeliculasFunciones() {
        return listPeliculasFunciones;
    }

    public void setListPeliculasFunciones(List<Funcion> listPeliculasFunciones) {
        this.listPeliculasFunciones = listPeliculasFunciones;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", duracionMinutos=" + duracionMinutos +
                ", generoPelicula=" + generoPelicula +
                ", estadoPelicula=" + estadoPelicula +
                ", clasificacionPelicula=" + clasificacionPelicula +
                ", cine=" + cine +
                ", listPeliculasFunciones=" + listPeliculasFunciones +
                '}';
    }
}
