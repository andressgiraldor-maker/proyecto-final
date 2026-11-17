/**
 * Clase para probar los metodos de la clase Administrador
 * @author Área de programación UQ
 * @since 2023-08
 *
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */

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