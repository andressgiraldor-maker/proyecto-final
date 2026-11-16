package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class VentaTest {
    Cliente cliente = new Cliente("001", "raul", "raul@gamil",
           TipoCliente.GENERAL );
    Pelicula pelicula = new Pelicula("P001", "coco", GeneroPelicula.CIENCIA_FICCION,
            120,ClasificacionPelicula.MAYORES_12, EstadoPelicula.DISPONIBLE);
    Sala sala = new Sala("S001", "sala 1",
    20, EstadoSala.DISPONIBLE);
    Asiento asiento = new Asiento ("A1", 'A', 2, EstadoAsiento.DISPONIBLE);
    Funcion funcion = new Funcion("F001", LocalDate.now(), LocalTime.now(), 10000, EstadoFuncion.PROGRAMADA, pelicula, sala);
    Venta venta1 = new Venta("V001", cliente, funcion, asiento, TipoEntrada.GENERAL, LocalDateTime.now(), 2000);

    @Test
    void estaActiva() {
        assertTrue(venta1.estaActiva());
    }

    @Test
    void cancelar() {
        venta1.cancelar();
        assertFalse(venta1.estaActiva());
    }
}