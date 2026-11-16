package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdministradorTest {

    Administrador admin = new Administrador("123456", "andres", "3456787654");

    @Test
    void obtenerRol() {
        assertEquals("ADMINISTRADOR", admin.obtenerRol());
    }
}