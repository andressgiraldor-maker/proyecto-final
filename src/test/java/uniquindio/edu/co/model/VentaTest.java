/**
 * Clase para probar los metodos de venta
 * @author Área de programación UQ
 * @since 2023-08
 *
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */
package uniquindio.edu.co.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class VentaTest {
    Asiento asiento = new Asiento('A', 2);
    Tarifa tarifa = new Tarifa(TipoEntrada.GENERAL, 5000);
    Sala sala = new Sala("s001", "sala 1");
    Pelicula pelicula = new Pelicula("P001", "coco", Genero.ANIMACION, 120, Clasificacion.MAYORES_7);
    Funcion funcion = new Funcion("f001", pelicula, sala, LocalDate.now(), LocalTime.now(),10000 );
    Cliente cliente  = new Cliente("12", "raul", "raul@gmail", TipoCliente.GENERAL);
    Venta venta = new Venta("V001",cliente, funcion, asiento,tarifa, 15000 );

    @Test
    void estaActiva() {
        assertTrue(venta.estaActiva());
    }

    @Test
    void generarEntrada() {
        Entrada entrada = venta.generarEntrada();

        assertNotNull(entrada);
    }

    @Test
    void cancelar(){
        venta.cancelar();

        assertEquals(EstadoVenta.CANCELADA, venta.getEstado());
    }

}