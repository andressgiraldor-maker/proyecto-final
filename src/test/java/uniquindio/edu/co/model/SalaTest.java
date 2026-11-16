package uniquindio.edu.co.model;

import model.EstadoAsiento;
import model.EstadoSala;
import model.Asiento;
import model.Sala;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaTest {

    // Sala es de 4: (codigo, nombre, capacidad, estado)
    Sala sala = new Sala("12345678", "sala 1", 10, EstadoSala.DISPONIBLE);

    // Como Sala no tiene buscarAsiento, lo buscamos a mano en la lista
    private Asiento buscarAsientoEnSala(Sala s, char fila, int numero) {
        for (Asiento a : s.getListSalaAsientos()) {
            if (a.getFila() == fila && a.getNumero() == numero) {
                return a;
            }
        }
        return null;
    }

    @Test
    void estaDisponible() {
        boolean disponible = sala.estaDisponible();
        assertTrue(disponible);
    }

    @Test
    void generarAsientos() {
        sala.generarAsientos(2, 3);
        assertEquals(6, sala.getListSalaAsientos().size());
    }

    @Test
    void buscarAsiento() {
        sala.generarAsientos(2, 3);
        Asiento asiento = buscarAsientoEnSala(sala, 'A', 2);
        assertNotNull(asiento);
        assertEquals('A', asiento.getFila());
        assertEquals(2, asiento.getNumero());
    }

    @Test
    void agregarAsiento() {
        sala.generarAsientos(2, 3);
        // Sala no tiene agregarAsiento, se agrega directo a la lista
        sala.getListSalaAsientos().add(new Asiento("C1", 'C', 1, EstadoAsiento.DISPONIBLE));
        assertEquals(7, sala.getListSalaAsientos().size());
    }

    @Test
    void modificarAsiento() {
        sala.generarAsientos(2, 3);
        // Sala no tiene modificarAsiento, se cambia el estado del que ya existe
        Asiento a = buscarAsientoEnSala(sala, 'A', 1);
        assertNotNull(a);
        a.ocupar();
        assertEquals(EstadoAsiento.OCUPADO, buscarAsientoEnSala(sala, 'A', 1).getEstadoAsiento());
    }
}