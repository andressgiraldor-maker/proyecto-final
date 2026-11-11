/**
 * Clase para probar los metodos de funcion
 * @author Área de programación UQ
 * @since 2023-08
 *
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */

package com.example.demo.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class FuncionTest {
    Pelicula pelicula = new Pelicula("9876567", "cocito",Genero.CIENCIA_FICCION, 130, Clasificacion.MAYORES_12);
    Sala sala = new Sala("876567", "sala 1");
    Funcion funcion = new Funcion("123456789876", pelicula, sala, LocalDate.now(), LocalTime.now(), 30 );
    Asiento asiento = new Asiento('2','5');

    @Test
    void buscarAsiento() {
    assertNotNull(funcion);
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

    }

    @Test
    void estaProgramada() {
        boolean estaProgramda = funcion.estaProgramada();
        assertTrue(estaProgramda);
    }
}