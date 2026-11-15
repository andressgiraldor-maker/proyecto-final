/**
 * Clase para probar los metodos de pelicula
 * @author Área de programación UQ
 * @since 2023-08
 *
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */
package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeliculaTest {
    Pelicula pelicula = new Pelicula("234567654", "coco", Genero.CIENCIA_FICCION,140,Clasificacion.MAYORES_12 );

    @Test
    void estaDisponible() {
        boolean disponible = pelicula.estaDisponible();
        assertTrue(disponible);

    }
}