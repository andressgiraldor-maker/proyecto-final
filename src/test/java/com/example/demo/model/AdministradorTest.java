/**
 * Clase para probar el registro de los jugadores
 * @author Área de programación UQ
 * @since 2023-08
 *
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */
package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdministradorTest {
    Administrador admin = new Administrador("123456", "andres", "3456787654");

    @Test
    void obtenerRol() {
         assertEquals("ADMINISTRADOR", admin.obtenerRol());
    }
}