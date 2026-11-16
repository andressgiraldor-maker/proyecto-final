package uniquindio.edu.co.model;

import model.Asiento;
import model.EstadoAsiento;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AsientoTest {

    Asiento asiento = new Asiento("A1", 'A', 1, EstadoAsiento.DISPONIBLE);

    @Test
    void estaDisponible() {
        assertEquals(true, asiento.estaDisponible());
    }

    @Test
    void ocupar() {
        asiento.ocupar();
        assertEquals(false, asiento.estaDisponible());
        assertEquals(EstadoAsiento.OCUPADO, asiento.getEstadoAsiento());
    }

    @Test
    void liberar() {
        asiento.ocupar();
        asiento.liberar();
        assertEquals(true, asiento.estaDisponible());
        assertEquals(EstadoAsiento.DISPONIBLE, asiento.getEstadoAsiento());
    }
}