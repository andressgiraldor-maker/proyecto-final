/**
 * Clase para probar los metodos de operador
 * @author Área de programación UQ
 * @since 2023-08
 *
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */
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