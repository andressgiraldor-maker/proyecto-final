/**
 * Clase para probar los metodos de la clase Pelicula
 * @author Área de programación UQ
 * @since 2023-08
 *
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */

package model;

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
    void deshabilitar(){
        pelicula.deshabilitar();
        assertEquals(EstadoPelicula.NO_DISPONIBLE, pelicula.getEstadoPelicula());
    }

    @Test
    void habilitar(){
        pelicula.deshabilitar();
        pelicula.habilitar();
        assertEquals(EstadoPelicula.DISPONIBLE, pelicula.getEstadoPelicula());
    }
}