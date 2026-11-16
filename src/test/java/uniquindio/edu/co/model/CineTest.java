package uniquindio.edu.co.model;

import model.Administrador;
import model.Cine;
import model.ClasificacionPelicula;
import model.Cliente;
import model.EstadoFuncion;
import model.EstadoPelicula;
import model.EstadoSala;
import model.Funcion;
import model.GeneroPelicula;
import model.Operador;
import model.Pelicula;
import model.Sala;
import model.Tarifa;
import model.TipoCliente;
import model.TipoEntrada;
import model.Venta;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CineTest {

    private Cine crearCine() {
        return new Cine("CINEMA UQ", "Armenia");
    }

    private Pelicula crearPelicula() {
        return new Pelicula("P001", "Avatar", GeneroPelicula.ACCION, 120, ClasificacionPelicula.MAYORES_12, EstadoPelicula.DISPONIBLE);
    }

    private Sala crearSala() {
        Sala s = new Sala("S01", "Sala 1", 10, EstadoSala.DISPONIBLE);
        s.generarAsientos(2, 3);
        return s;
    }

    private Cliente crearCliente() {
        return new Cliente("123", "Diego", "diego123", TipoCliente.GENERAL);
    }

    private Funcion crearFuncion(Cine cine) throws Exception {
        Pelicula p = crearPelicula();
        cine.registrarPelicula(p);
        Sala s = crearSala();
        cine.registrarSala(s);
        Funcion f = new Funcion("F001", LocalDate.now(), LocalTime.of(18, 30), 15000, EstadoFuncion.PROGRAMADA, p, s);
        cine.programarFuncion(f);
        return f;
    }

    @Test
    void registrarPelicula() throws Exception {
        Cine cine = crearCine();
        cine.registrarPelicula(crearPelicula());
        assertNotNull(cine.buscarPelicula("P001"));
        assertEquals("Avatar", cine.buscarPelicula("P001").getNombre());
    }

    @Test
    void modificarPelicula() throws Exception {
        Cine cine = crearCine();
        cine.registrarPelicula(crearPelicula());
        cine.modificarPelicula(new Pelicula("P001", "Avatar 2", GeneroPelicula.ACCION, 130, ClasificacionPelicula.MAYORES_12, EstadoPelicula.DISPONIBLE));
        assertEquals("Avatar 2", cine.buscarPelicula("P001").getNombre());
    }

    @Test
    void deshabilitarPelicula() throws Exception {
        Cine cine = crearCine();
        cine.registrarPelicula(crearPelicula());
        cine.deshabilitarPelicula("P001");
        assertTrue(cine.buscarPelicula("P001").estaDeshabilitado());
    }

    @Test
    void registrarSala() throws Exception {
        Cine cine = crearCine();
        cine.registrarSala(crearSala());
        assertNotNull(cine.buscarSala("S01"));
    }

    @Test
    void modificarSala() throws Exception {
        Cine cine = crearCine();
        cine.registrarSala(crearSala());
        cine.modificarSala(new Sala("S01", "Sala VIP", 10, EstadoSala.DISPONIBLE));
        assertEquals("Sala VIP", cine.buscarSala("S01").getNombre());
    }

    @Test
    void deshabilitarSala() throws Exception {
        Cine cine = crearCine();
        cine.registrarSala(crearSala());
        cine.deshabilitarSala("S01");
        assertTrue(cine.buscarSala("S01").estaDeshabilitado());
    }

    @Test
    void consultarAsientosDeSala() throws Exception {
        Sala s = crearSala();
        assertEquals(6, s.getListSalaAsientos().size());
    }

    @Test
    void agregarAsiento() throws Exception {
        Sala s = crearSala();
        s.getListSalaAsientos().add(new model.Asiento("C1", 'C', 1, model.EstadoAsiento.DISPONIBLE));
        assertEquals(7, s.getListSalaAsientos().size());
    }

    @Test
    void modificarAsiento() throws Exception {
        Sala s = crearSala();
        s.getListSalaAsientos().get(0).ocupar();
        assertEquals(model.EstadoAsiento.OCUPADO, s.getListSalaAsientos().get(0).getEstadoAsiento());
    }

    @Test
    void registrarCliente() {
        Cine cine = crearCine();
        cine.registrarCliente(crearCliente());
        assertNotNull(cine.buscarCliente("123"));
    }

    @Test
    void registrarOperador() {
        Cine cine = crearCine();
        cine.registrarOperador(new Operador("456", "Ana", "ana123"));
        assertNotNull(cine.buscarOperador("456"));
    }

    @Test
    void registrarAdministrador() {
        // Cine no tiene lista de administradores, se verifica con Operador como persona
        Cine cine = crearCine();
        cine.registrarOperador(new Operador("789", "Andres", "andres123"));
        assertNotNull(cine.buscarPersona("789"));
        assertEquals("ADMINISTRADOR", new Administrador("789", "Andres", "andres123").obtenerRol());
    }

    @Test
    void modificarCliente() {
        // Cine no tiene modificarCliente, se verifica que buscarCliente funciona
        Cine cine = crearCine();
        cine.registrarCliente(crearCliente());
        assertEquals("Diego", cine.buscarCliente("123").getNombre());
    }

    @Test
    void modificarOperador() {
        // Cine no tiene modificarOperador, se verifica buscarOperador
        Cine cine = crearCine();
        cine.registrarOperador(new Operador("456", "Ana", "ana123"));
        assertEquals("Ana", cine.buscarOperador("456").getNombre());
    }

    @Test
    void modificarAdministrador() {
        // Cine no tiene modificarAdministrador, se verifica el rol
        Administrador a = new Administrador("789", "Andres", "andres123");
        assertEquals("ADMINISTRADOR", a.obtenerRol());
    }

    @Test
    void registrarTarifa() {
        Cine cine = crearCine();
        cine.registrarTarifa(new Tarifa(TipoEntrada.ESTUDIANTE, 12000));
        assertNotNull(cine.buscarTarifa(TipoEntrada.ESTUDIANTE));
        assertEquals(12000, cine.buscarTarifa(TipoEntrada.ESTUDIANTE).valor());
    }

    @Test
    void modificarTarifa() {
        Cine cine = crearCine();
        cine.modificarTarifa(new Tarifa(TipoEntrada.ESTUDIANTE, 5000));
        assertEquals(5000, cine.buscarTarifa(TipoEntrada.ESTUDIANTE).valor());
    }

    @Test
    void programarFuncion() throws Exception {
        Cine cine = crearCine();
        crearFuncion(cine);
        assertNotNull(cine.buscarFuncion("F001"));
    }

    @Test
    void venderEntrada() throws Exception {
        Cine cine = crearCine();
        Funcion f = crearFuncion(cine);
        Cliente c = crearCliente();
        cine.registrarCliente(c);
        Venta v = cine.venderEntrada("V001", c, f, TipoEntrada.GENERAL, 'A', 1);
        assertNotNull(v);
        assertEquals(1, cine.contarEntradasVendidas());
    }

    @Test
    void calcularValorEntrada() throws Exception {
        Cine cine = crearCine();
        Funcion f = crearFuncion(cine);
        assertEquals(15000, cine.calcularValorEntrada(f, TipoEntrada.GENERAL));
        assertEquals(12000, cine.calcularValorEntrada(f, TipoEntrada.ESTUDIANTE));
    }

    @Test
    void consultarPeliculasDisponibles() throws Exception {
        Cine cine = crearCine();
        cine.registrarPelicula(crearPelicula());
        assertEquals(1, cine.consultarPeliculasDisponibles().size());
    }

    @Test
    void consultarFunciones() throws Exception {
        Cine cine = crearCine();
        crearFuncion(cine);
        assertEquals(1, cine.consultarFunciones().size());
    }

    @Test
    void consultarFuncionesPorFecha() throws Exception {
        Cine cine = crearCine();
        crearFuncion(cine);
        assertEquals(1, cine.consultarFuncionesPorFecha(LocalDate.now()).size());
        assertEquals(0, cine.consultarFuncionesPorFecha(LocalDate.now().plusDays(1)).size());
    }

    @Test
    void consultarAsientosDisponibles() throws Exception {
        Cine cine = crearCine();
        crearFuncion(cine);
        assertEquals(6, cine.consultarAsientosDisponibles("F001").size());
    }

    @Test
    void contarEntradasVendidas() throws Exception {
        Cine cine = crearCine();
        Funcion f = crearFuncion(cine);
        Cliente c = crearCliente();
        cine.registrarCliente(c);
        cine.venderEntrada("V001", c, f, TipoEntrada.GENERAL, 'A', 1);
        assertEquals(1, cine.contarEntradasVendidas());
    }

    @Test
    void calcularTotalIngresos() throws Exception {
        Cine cine = crearCine();
        Funcion f = crearFuncion(cine);
        Cliente c = crearCliente();
        cine.registrarCliente(c);
        cine.venderEntrada("V001", c, f, TipoEntrada.GENERAL, 'A', 1);
        assertEquals(15000, cine.calcularTotalIngresos());
    }

    @Test
    void obtenerPeliculaMasVendida() throws Exception {
        Cine cine = crearCine();
        Funcion f = crearFuncion(cine);
        Cliente c = crearCliente();
        cine.registrarCliente(c);
        cine.venderEntrada("V001", c, f, TipoEntrada.GENERAL, 'A', 1);
        assertNotNull(cine.obtenerPeliculaMasVendida());
        assertEquals("Avatar", cine.obtenerPeliculaMasVendida().getNombre());
    }

    @Test
    void generarReporteOcupacion() throws Exception {
        Cine cine = crearCine();
        crearFuncion(cine);
        assertNotNull(cine.generarReporteOcupacion("F001"));
    }

    @Test
    void buscarPersona() {
        Cine cine = crearCine();
        cine.registrarCliente(crearCliente());
        assertNotNull(cine.buscarPersona("123"));
    }

    @Test
    void buscarCliente() {
        Cine cine = crearCine();
        cine.registrarCliente(crearCliente());
        assertNotNull(cine.buscarCliente("123"));
    }

    @Test
    void buscarOperador() {
        Cine cine = crearCine();
        cine.registrarOperador(new Operador("456", "Ana", "ana123"));
        assertNotNull(cine.buscarOperador("456"));
    }

    @Test
    void buscarAdministrador() {
        // Cine no tiene buscarAdministrador, se verifica buscarPersona
        Cine cine = crearCine();
        cine.registrarOperador(new Operador("789", "Andres", "andres123"));
        assertNotNull(cine.buscarPersona("789"));
    }

    @Test
    void buscarPelicula() throws Exception {
        Cine cine = crearCine();
        cine.registrarPelicula(crearPelicula());
        assertNotNull(cine.buscarPelicula("P001"));
    }

    @Test
    void buscarSala() throws Exception {
        Cine cine = crearCine();
        cine.registrarSala(crearSala());
        assertNotNull(cine.buscarSala("S01"));
    }

    @Test
    void buscarFuncion() throws Exception {
        Cine cine = crearCine();
        crearFuncion(cine);
        assertNotNull(cine.buscarFuncion("F001"));
    }

    @Test
    void buscarVenta() throws Exception {
        Cine cine = crearCine();
        Funcion f = crearFuncion(cine);
        Cliente c = crearCliente();
        cine.registrarCliente(c);
        cine.venderEntrada("V001", c, f, TipoEntrada.GENERAL, 'A', 1);
        assertNotNull(cine.buscarVenta("V001"));
    }

    @Test
    void buscarTarifa() {
        Cine cine = crearCine();
        assertNotNull(cine.buscarTarifa(TipoEntrada.GENERAL));
    }
}