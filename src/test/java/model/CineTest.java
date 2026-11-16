package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CineTest {
    private Cine cine;
    private Cliente cliente;
    private Pelicula pelicula;
    private Sala sala;
    private Funcion funcion;

    @BeforeEach
    void setUp() throws Exception {
        cine = new Cine("cinemaUQ", "armenia carrera 14");
        cliente = new Cliente("001", "raul", "raul@gamil", TipoCliente.GENERAL);
        pelicula = new Pelicula("P001", "coco", GeneroPelicula.CIENCIA_FICCION,
                120, ClasificacionPelicula.MAYORES_12, EstadoPelicula.DISPONIBLE);
        sala = new Sala("S001", "sala 1", 20, EstadoSala.DISPONIBLE);
        sala.generarAsientos(2, 2); // A1, A2, B1, B2

        cine.registrarPelicula(pelicula);
        cine.registrarSala(sala);
        cine.registrarCliente(cliente);

        funcion = new Funcion("F001", LocalDate.now(), LocalTime.now(),
                10000, EstadoFuncion.PROGRAMADA, pelicula, sala);
        cine.programarFuncion(funcion);
    }


    @Test
    void registrarPelicula() {
        assertEquals(pelicula, cine.buscarPelicula("P001"));
    }

    @Test
    void modificarPelicula() {
        Pelicula nueva = new Pelicula("P001", "up", GeneroPelicula.CIENCIA_FICCION,
                100, ClasificacionPelicula.MAYORES_12, EstadoPelicula.DISPONIBLE);
        cine.modificarPelicula(nueva);
        assertEquals("up", cine.buscarPelicula("P001").getNombre());
    }

    @Test
    void deshabilitarPelicula() {
        cine.deshabilitarPelicula("P001");
        assertFalse(cine.buscarPelicula("P001").estaDisponible());
    }

    @Test
    void habilitarPelicula() {
        cine.deshabilitarPelicula("P001");
        cine.habilitarPelicula("P001");
        assertTrue(cine.buscarPelicula("P001").estaDisponible());
    }

    @Test
    void buscarPelicula() {
        assertNotNull(cine.buscarPelicula("P001"));
        assertNull(cine.buscarPelicula("NO-EXISTE"));
    }

    // ===== SALAS =====

    @Test
    void registrarSala() {
        assertEquals(sala, cine.buscarSala("S001"));
    }

    @Test
    void modificarSala() {
        Sala nueva = new Sala("S001", "sala vip", 30, EstadoSala.DISPONIBLE);
        cine.modificarSala(nueva);
        assertEquals("sala vip", cine.buscarSala("S001").getNombre());
    }

    @Test
    void deshabilitarSala() {
        cine.deshabilitarSala("S001");
        assertTrue(cine.buscarSala("S001").estaDeshabilitado());
    }

    @Test
    void habilitarSala() {
        cine.deshabilitarSala("S001");
        cine.habilitarSala("S001");
        assertTrue(cine.buscarSala("S001").estaDisponible());
    }

    @Test
    void buscarSala() {
        assertNotNull(cine.buscarSala("S001"));
        assertNull(cine.buscarSala("NO-EXISTE"));
    }

    // ===== PERSONAS =====

    @Test
    void registrarCliente() {
        assertSame(cliente, cine.buscarCliente("001"));
    }

    @Test
    void buscarCliente() {
        assertNotNull(cine.buscarCliente("001"));
        assertNull(cine.buscarCliente("999"));
    }

    @Test
    void buscarPersona() {
        assertEquals(cliente, cine.buscarPersona("001"));
    }

    // ===== TARIFAS =====

    @Test
    void registrarTarifa() {
        cine.registrarTarifa(new Tarifa(TipoEntrada.GENERAL, 20000));
        assertEquals(20000, cine.buscarTarifa(TipoEntrada.GENERAL).valor());
    }

    @Test
    void modificarTarifa() {
        cine.modificarTarifa(new Tarifa(TipoEntrada.ESTUDIANTE, 9000));
        assertEquals(9000, cine.buscarTarifa(TipoEntrada.ESTUDIANTE).valor());
    }

    @Test
    void buscarTarifa() {
        assertEquals(15000, cine.buscarTarifa(TipoEntrada.GENERAL).valor());
    }

    // ===== FUNCIONES =====

    @Test
    void programarFuncion() {
        assertEquals(funcion, cine.buscarFuncion("F001"));
    }

    @Test
    void buscarFuncion() {
        assertNotNull(cine.buscarFuncion("F001"));
        assertNull(cine.buscarFuncion("NO-EXISTE"));
    }

    @Test
    void consultarFunciones() {
        assertEquals(1, cine.consultarFunciones().size());
    }

    @Test
    void consultarFuncionesPorFecha() {
        assertEquals(1, cine.consultarFuncionesPorFecha(LocalDate.now()).size());
        assertEquals(0, cine.consultarFuncionesPorFecha(LocalDate.now().plusDays(5)).size());
    }

    // ===== VENTAS =====

    @Test
    void calcularValorEntrada() {
        assertEquals(15000, cine.calcularValorEntrada(funcion, TipoEntrada.GENERAL));
    }

    @Test
    void venderEntrada() throws Exception {
        Venta venta = cine.venderEntrada("V001", cliente, funcion, TipoEntrada.GENERAL, 'A', 1);
        assertNotNull(venta);
        assertFalse(funcion.buscarAsiento('A', 1).estaDisponible());
    }

    @Test
    void venderVariasEntradas() throws Exception {
        List<Venta> ventas = cine.venderVariasEntradas("V001", cliente, funcion,
                TipoEntrada.GENERAL, List.of("A1", "A2"));
        assertEquals(2, ventas.size());
        assertEquals(2, funcion.contarAsientosOcupados());
    }

    @Test
    void buscarVenta() throws Exception {
        cine.venderEntrada("V001", cliente, funcion, TipoEntrada.GENERAL, 'A', 1);
        assertNotNull(cine.buscarVenta("V001"));
        assertNull(cine.buscarVenta("NO-EXISTE"));
    }

    @Test
    void buscarVentasPorCodigo() throws Exception {
        cine.venderVariasEntradas("V001", cliente, funcion, TipoEntrada.GENERAL, List.of("A1", "A2"));
        assertEquals(2, cine.buscarVentasPorCodigo("V001").size());
    }

    @Test
    void cancelarVenta() throws Exception {
        cine.venderEntrada("V001", cliente, funcion, TipoEntrada.GENERAL, 'A', 1);
        cine.cancelarVenta("V001");
        assertFalse(cine.buscarVenta("V001").estaActiva());
    }

    // ===== CONSULTAS =====

    @Test
    void consultarPeliculasDisponibles() {
        assertEquals(1, cine.consultarPeliculasDisponibles().size());
        cine.deshabilitarPelicula("P001");
        assertEquals(0, cine.consultarPeliculasDisponibles().size());
    }

    @Test
    void consultarAsientosDisponibles() throws Exception {
        assertEquals(4, cine.consultarAsientosDisponibles("F001").size());
    }

    @Test
    void consultarDetalleAsientosFuncion() throws Exception {
        String detalle = cine.consultarDetalleAsientosFuncion("F001");
        assertTrue(detalle.contains("Disponible"));
        assertTrue(detalle.contains("coco"));
    }

    // ===== REPORTES =====

    @Test
    void contarEntradasVendidas() throws Exception {
        cine.venderVariasEntradas("V001", cliente, funcion, TipoEntrada.GENERAL, List.of("A1", "A2"));
        assertEquals(2, cine.contarEntradasVendidas());
    }

    @Test
    void calcularTotalIngresos() throws Exception {
        cine.venderVariasEntradas("V001", cliente, funcion, TipoEntrada.GENERAL, List.of("A1", "A2"));
        assertEquals(30000, cine.calcularTotalIngresos(), 0.001);
    }

    @Test
    void obtenerPeliculaMasVendida() throws Exception {
        cine.venderEntrada("V001", cliente, funcion, TipoEntrada.GENERAL, 'A', 1);
        assertSame(pelicula, cine.obtenerPeliculaMasVendida());
    }

    @Test
    void generarReporteOcupacion() throws Exception {
        cine.venderEntrada("V001", cliente, funcion, TipoEntrada.GENERAL, 'A', 1);
        assertNotNull(cine.generarReporteOcupacion("F001"));
    }
}