/**
 * Clase para probar los metodos de sala
 * @author Área de programación UQ
 * @since 2023-08
 *
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */
package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaTest {
    Sala sala = new Sala("12345678", "sala 1");

    @Test
    void estaDisponible() {
        boolean disponible = sala.estaDisponible();
        assertTrue(disponible);
    }

    @Test
    void generarAsientos() {
        sala.generarAsientos(2,3);
        assertEquals(6,sala.getAsientos().size());

    }

    @Test
    void buscarAsiento() {
        sala.generarAsientos(2,3);
        Asiento asiento = sala.buscarAsiento('A',2);
        assertNotNull(asiento);
        assertEquals('A', asiento.getFila());
        assertEquals(2, asiento.getNumero());

    }

    @Test
    void agregarAsiento() {
    }

    @Test
    void modificarAsiento() {
    }
}