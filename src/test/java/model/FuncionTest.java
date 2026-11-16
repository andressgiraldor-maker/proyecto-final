package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class FuncionTest {
    private Funcion funcion;

    @BeforeEach
    void setUp() {
        Pelicula pelicula = new Pelicula("P001", "up", GeneroPelicula.CIENCIA_FICCION, 120,
                ClasificacionPelicula.MAYORES_12, EstadoPelicula.DISPONIBLE);

        Sala sala = new Sala("S001", "sala 1", 50, EstadoSala.DISPONIBLE);
        sala.getListSalaAsientos().add(new Asiento("A1", 'A', 1, EstadoAsiento.DISPONIBLE));
        sala.getListSalaAsientos().add(new Asiento("B1", 'B', 1, EstadoAsiento.DISPONIBLE));

        funcion = new Funcion("F001", LocalDate.now(), LocalTime.now(),
                15000, EstadoFuncion.PROGRAMADA, pelicula, sala);
    }


    @Test
    void estaProgramadaEsTrueCuandoEstaProgramada() {
        assertTrue(funcion.estaProgramada());
    }

    @Test
    void estaDisponible() {
        assertEquals(funcion.estaProgramada(), funcion.estaDisponible());
        funcion.setEstadoFuncion(EstadoFuncion.CANCELADA);
        assertEquals(funcion.estaProgramada(), funcion.estaDisponible());
    }

    @Test
    void buscarAsiento() {
        Asiento encontrado = funcion.buscarAsiento('A', 1);
        assertNotNull(encontrado);
        assertEquals('A', encontrado.getFila());
        assertEquals(1, encontrado.getNumero());
    }

    @Test
    void contarAsientosDisponiblesConTodosLibres() {
        assertEquals(2, funcion.contarAsientosDisponibles());
    }

    @Test
    void contarAsientosOcupados() {
        assertEquals(0, funcion.contarAsientosOcupados());
    }

    @Test
    void contarAsientosOcupadosCuentaLosOcupados() {
        funcion.buscarAsiento('B', 1).ocupar();
        assertEquals(1, funcion.contarAsientosOcupados());
    }

    @Test
    void calcularPorcentajeOcupacion() {
        Funcion sinAsientos = new Funcion("F002", LocalDate.now(), LocalTime.now(),
                10000, EstadoFuncion.PROGRAMADA, null, null);
        assertEquals(0.0, sinAsientos.calcularPorcentajeOcupacion());
    }

    @Test
    void getAsientosDisponibles() {
        assertEquals(2, funcion.getAsientosDisponibles().size());

    }
}