/**
 * Clase para probar los metodos de sala
 * @author Área de programación UQ
 * @since 2023-08
 *
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */
package uniquindio.edu.co.model;

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
        sala.agregarAsiento('A', 1);

        assertNotNull(sala.buscarAsiento('A', 1));
    }

    @Test
    void modificarAsiento() {
        sala.agregarAsiento('A', 1);

        sala.modificarAsiento('A', 1, 'B', 5);

        assertNull(sala.buscarAsiento('A', 1));
        assertNotNull(sala.buscarAsiento('B', 5));

    }

    @Test
    void deshabilitar(){
        sala.deshabilitar();
        assertEquals(EstadoSala.FUERA_DE_SERVICIO, sala.getEstado());
    }

    @Test
    void eliminarAsiento(){
        sala.agregarAsiento('A', 1);

        sala.eliminarAsiento('A', 1);

        assertNull(sala.buscarAsiento('A', 1));
    }


}