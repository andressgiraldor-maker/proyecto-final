/**
 * Clase para probar los metodos de la clase Cliente
 * @author Área de programación UQ
 * @since 2023-08
 *
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */

package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteTest {

    Cliente cliente = new Cliente("87654567", "diego", "diego1234", TipoCliente.GENERAL);

    @Test
    void obtenerRol() {
        assertEquals("CLIENTE", cliente.obtenerRol());
    }
}