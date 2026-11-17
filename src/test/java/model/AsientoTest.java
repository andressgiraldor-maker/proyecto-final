/**
 * Clase para probar los metodos de la clase Asiento
 * @author Área de programación UQ
 * @since 2023-08
 *
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */

package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AsientoTest {

    Asiento asiento = new Asiento("001", 'A', 2, EstadoAsiento.DISPONIBLE);

    @Test
    void estaDisponible() {
        asiento.estaDisponible();
        assertTrue( asiento.estaDisponible());
    }

    @Test
    void ocuparCambiaElEstadoAOcupado() {
        asiento.ocupar();
        assertEquals(EstadoAsiento.OCUPADO, asiento.getEstadoAsiento());
    }

    @Test
    void liberarCambiaElEstadoADisponible() {
        asiento.ocupar();
        asiento.liberar();
        assertEquals(EstadoAsiento.DISPONIBLE, asiento.getEstadoAsiento());
    }

    @Test
    void liberarUnAsientoYaDisponibleSigueDisponible() {
        asiento.liberar();
        assertEquals(EstadoAsiento.DISPONIBLE, asiento.getEstadoAsiento());
    }
}