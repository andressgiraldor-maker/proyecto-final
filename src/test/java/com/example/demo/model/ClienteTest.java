/**
 * Clase para probar los metodos de cliente
 * @author Área de programación UQ
 * @since 2023-08
 *
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */
package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    Cliente cliente= new Cliente("87654567", "diego", "diego1234", TipoCliente.GENERAL);

    @Test
    void obtenerRol() {

        assertEquals("CLIENTE", cliente.obtenerRol());
    }
}