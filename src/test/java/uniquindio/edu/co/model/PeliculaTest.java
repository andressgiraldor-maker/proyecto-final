package uniquindio.edu.co.model;

import model.ClasificacionPelicula;
import model.EstadoPelicula;
import model.GeneroPelicula;
import model.Pelicula;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeliculaTest {

    Pelicula pelicula = new Pelicula("9876567", "cocito", GeneroPelicula.CIENCIA_FICCION, 130, ClasificacionPelicula.MAYORES_12, EstadoPelicula.DISPONIBLE);

    @Test
    void estaDisponible() {
        assertTrue(pelicula.estaDisponible());
    }

    @Test
    void estaDeshabilitado() {
        pelicula.deshabilitar();
        assertTrue(pelicula.estaDeshabilitado());
        assertFalse(pelicula.estaDisponible());
    }

    @Test
    void deshabilitar() {
        pelicula.deshabilitar();
        assertTrue(pelicula.estaDeshabilitado());
    }

    @Test
    void deshabilitar(){
        pelicula.deshabilitar();
        assertEquals(EstadoPelicula.NO_DISPONIBLE, pelicula.getEstado());
    }
}