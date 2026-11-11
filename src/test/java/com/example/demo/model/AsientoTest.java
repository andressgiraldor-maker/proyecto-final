/**
 * Clase para probar los metodos de asiento
 * @author Área de programación UQ
 * @since 2023-08
 *
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */
package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AsientoTest {

    Asiento asiento = new Asiento('3', '5');

    @Test
    void estaDisponible() {
        assertEquals(true, asiento.estaDisponible());
    }

    @Test
    void ocupar() {
    }

    @Test
    void liberar() {
    }
}