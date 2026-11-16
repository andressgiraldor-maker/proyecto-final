package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaTest {
    Sala sala = new Sala("S01", "sala 1", 50, EstadoSala.DISPONIBLE);

    @Test
    void habilitar() {
        sala.deshabilitar();
        sala.habilitar();
        assertEquals(EstadoSala.DISPONIBLE,sala.getEstadoSala());
    }

    @Test
    void deshabilitar() {
        sala.deshabilitar();
        assertEquals(EstadoSala.FUERA_DE_SERVICIO,sala.getEstadoSala());
    }

    @Test
    void estaDeshabilitado() {
        sala.habilitar();
        assertFalse(sala.estaDeshabilitado());
    }

    @Test
    void estaDisponible() {
        sala.deshabilitar();
        assertFalse(sala.estaDisponible());
    }

    @Test
    void generarAsientos() {
        sala.generarAsientos(2,6);
        assertEquals(12, sala.getCapacidad());
    }
}