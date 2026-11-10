package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperadorTest {

    Operador operador = new Operador("123456798", "raul", "099");

    @Test
    void obtenerRol() {

        assertEquals("OPERADOR", operador.obtenerRol());
    }
}