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