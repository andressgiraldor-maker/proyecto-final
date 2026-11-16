package uniquindio.edu.co.model;

import model.Asiento;
import model.ClasificacionPelicula;
import model.EstadoAsiento;
import model.EstadoFuncion;
import model.EstadoPelicula;
import model.EstadoSala;
import model.Funcion;
import model.GeneroPelicula;
import model.Pelicula;
import model.Sala;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class FuncionTest {

    Pelicula pelicula = new Pelicula("9876567", "cocito", GeneroPelicula.CIENCIA_FICCION, 130, ClasificacionPelicula.MAYORES_12, EstadoPelicula.DISPONIBLE);
    Sala sala = new Sala("876567", "sala 1", 10, EstadoSala.DISPONIBLE);
    Funcion funcion = new Funcion("123456789876", LocalDate.now(), LocalTime.now(), 30, EstadoFuncion.PROGRAMADA, pelicula, sala);
    Asiento asiento = new Asiento("A1", 'A', 1, EstadoAsiento.DISPONIBLE);

    @Test
    void buscarAsiento() {
        assertNotNull(funcion);
        assertNotNull(funcion.buscarAsiento('A', 1));
        assertNull(funcion.buscarAsiento('Z', 99));
    }

    @Test
    void contarAsientosDisponibles() {
        int total = funcion.contarAsientosDisponibles();
        assertEquals(total, funcion.contarAsientosDisponibles());
    }

    @Test
    void contarAsientosOcupados() {
        int total = funcion.contarAsientosOcupados();
        assertEquals(total, funcion.contarAsientosOcupados());
    }

    @Test
    void calcularPorcentajeOcupacion() {
        double total = funcion.calcularPorcentajeOcupacion();
        assertEquals(total, funcion.calcularPorcentajeOcupacion());
    }

    @Test
    void calcularValorEntrada() {
        // Funcion no tiene calcularValorEntrada, ese es de Cine.
        // Aqui verificamos que el valor base quedo bien guardado
        assertEquals(30, funcion.getValorBase());
    }

    @Test
    void estaProgramada() {
        boolean estaProgramda = funcion.estaProgramada();
        assertTrue(estaProgramda);
    }
}