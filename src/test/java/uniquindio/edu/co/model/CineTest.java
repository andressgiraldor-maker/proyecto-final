package uniquindio.edu.co.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CineTest {
    Asiento asiento = new Asiento('A', 1);
    Cine cine = new Cine("cinemaUQ", "carrera 19");
    Pelicula pelicula = new Pelicula("P004", "cars", Genero.ANIMACION, 120, Clasificacion.MAYORES_7);
    Sala sala = new Sala("S001", "Sala 1");
    Cliente cliente = new Cliente("12", "raul", "raul@gmail", TipoCliente.GENERAL);
    Operador operador = new Operador("13", "andres", "001");
    Administrador admin = new Administrador("14", "yeferson", "003");
    Tarifa tarifa = new Tarifa(TipoEntrada.GENERAL, 2000);
    Funcion funcion = new Funcion("F001", pelicula, sala, LocalDate.now(), LocalTime.now(), 10000);
    Venta venta = new Venta("001", cliente, funcion, asiento, tarifa, 15000);


    @Test
    void registrarPelicula() {
        Pelicula pelicula = new Pelicula("P001", "rapidos y furiosos", Genero.CIENCIA_FICCION, 120, Clasificacion.MAYORES_12);

        cine.registrarPelicula(pelicula);

        assertNotNull(cine.buscarPelicula("P001"));
        assertEquals(pelicula, cine.buscarPelicula("P001"));
    }

    @Test
    void modificarPelicula() {
        Pelicula original = new Pelicula("P001", "rapidos y furiosos", Genero.CIENCIA_FICCION, 120, Clasificacion.MAYORES_12);
        cine.registrarPelicula(original);

        Pelicula modificada = new Pelicula("P001", "up", Genero.CIENCIA_FICCION, 120, Clasificacion.MAYORES_7);

        cine.modificarPelicula(modificada);

        Pelicula resultado = cine.buscarPelicula("P001");
        assertEquals("up", resultado.getNombre());
        assertEquals(modificada.getGenero(), resultado.getGenero());
        assertEquals(modificada.getDuracionMinutos(), resultado.getDuracionMinutos());
        assertEquals(modificada.getClasificacion(), resultado.getClasificacion());
        assertEquals(modificada.getEstado(), resultado.getEstado());
    }

    @Test
    void deshabilitarPelicula() {
        Pelicula pelicula = new Pelicula("P001", "rapidos y furiosos", Genero.CIENCIA_FICCION, 120, Clasificacion.MAYORES_7);
        cine.registrarPelicula(pelicula);

        cine.deshabilitarPelicula("P001");
        assertEquals(EstadoPelicula.NO_DISPONIBLE, pelicula.getEstado());
    }

    @Test
    void eliminarPelicula() {
        cine.registrarPelicula(pelicula);

        cine.eliminarPelicula("P004");

        assertNull(cine.buscarPelicula("P004"));
    }

    @Test
    void registrarSala() {

        cine.registrarSala(sala);

        assertNotNull(cine.buscarSala("S001"));
        assertEquals(sala, cine.buscarSala("S001"));
    }

    @Test
    void modificarSala() {
        cine.registrarSala(sala);

        Sala modificada = new Sala("S001", "Sala VIP");

        cine.modificarSala(modificada);

        Sala resultado = cine.buscarSala("S001");
        assertEquals("Sala VIP", resultado.getNombre());
        assertEquals(modificada.getEstado(), resultado.getEstado());
    }

    @Test
    void deshabilitarSala() {
        cine.registrarSala(sala);

        cine.deshabilitarSala("S001");

        // Ajusta al nombre real de tu enum de estado:
        assertEquals(EstadoSala.FUERA_DE_SERVICIO, sala.getEstado());
    }

    @Test
    void eliminarSala() {
        cine.registrarSala(sala);

        cine.eliminarSala("S001");

        assertNull(cine.buscarSala("S001"));
    }

    @Test
    void consultarAsientosDeSala() {
        cine.registrarSala(sala);

        List<Asiento> asientos = cine.consultarAsientosDeSala("S001");

        assertNotNull(asientos);
        assertEquals(sala.getAsientos(), asientos);
    }

    @Test
    void agregarAsiento() {
        cine.registrarSala(sala);
        int cantidadInicial = cine.consultarAsientosDeSala("S001").size();

        cine.agregarAsiento("S001", 'A', 1);

        List<Asiento> asientos = cine.consultarAsientosDeSala("S001");
        assertEquals(cantidadInicial + 1, asientos.size());
    }


    @Test
    void modificarAsiento() {
        cine.registrarSala(new Sala("S001", "Sala 1"));
        cine.agregarAsiento("S001", 'A', 1);
        int cantidadAntes = cine.consultarAsientosDeSala("S001").size();

        cine.modificarAsiento("S001", 'A', 1, 'B', 2);

        assertEquals(cantidadAntes, cine.consultarAsientosDeSala("S001").size());
    }

    @Test
    void eliminarAsiento() {
        cine.registrarSala(new Sala("S001", "Sala 1"));
        cine.agregarAsiento("S001", 'A', 1);
        int cantidadAntes = cine.consultarAsientosDeSala("S001").size();

        cine.eliminarAsiento("S001", 'A', 1);

        assertEquals(cantidadAntes - 1, cine.consultarAsientosDeSala("S001").size());
    }

    @Test
    void registrarCliente() {

        cine.registrarCliente(cliente);

        assertNotNull(cine.buscarPersona("12"));
        assertEquals(cliente, cine.buscarPersona("12"));
    }

    @Test
    void registrarOperador() {

        cine.registrarOperador(operador);

        assertNotNull(cine.buscarPersona("13"));
        assertEquals(operador, cine.buscarPersona("13"));
    }

    @Test
    void registrarAdministrador() {
        cine.registrarAdministrador(admin);

        assertNotNull(cine.buscarPersona("14"));
        assertSame(admin, cine.buscarPersona("14"));
    }

    @Test
    void modificarCliente() {
        Cliente cliente2 = new Cliente("1001", "Ana", "ana@gmail.com", TipoCliente.GENERAL);
        cine.registrarCliente(cliente2);

        Cliente modificado = new Cliente("1001", "Ana Maria", "anamaria@gmail.com", TipoCliente.ADULTO_MAYOR);

        cine.modificarCliente(modificado);

        Cliente resultado = cine.buscarCliente("1001");
        assertEquals("Ana Maria", resultado.getNombre());
        assertEquals("anamaria@gmail.com", resultado.getGmail());
        assertEquals(TipoCliente.ADULTO_MAYOR, resultado.getTipoCliente());

    }

    @Test
    void modificarOperador() {
        Operador operador1 = new Operador("2001", "Carlos", "E001");
        cine.registrarOperador(operador1);

        Operador modificado = new Operador("2001", "Carlos Andres", "E002" );

        cine.modificarOperador(modificado);

        Operador resultado = cine.buscarOperador("2001");
        assertEquals("Carlos Andres", resultado.getNombre());
        assertEquals("E002", resultado.getCodigoEmpleado());
    }

    @Test
    void modificarAdministrador() {
        Administrador admin1 = new Administrador("2001", "Carlos", "A001" );
        cine.registrarAdministrador(admin1);

        Administrador modificado = new Administrador("2001", "Carlos Andres", "A002");

        cine.modificarAdministrador(modificado);

        Administrador resultado = cine.buscarAdministrador("2001");
        assertEquals("Carlos Andres", resultado.getNombre());
        assertEquals("A002", resultado.getCodigoAdministrador());
    }

    @Test
    void eliminarPersona() {
        cine.registrarCliente(new Cliente("1001", "Ana","Ana@gamil", TipoCliente.GENERAL));

        cine.eliminarPersona("1001");

        assertNull(cine.buscarPersona("1001"));
    }

    @Test
    void registrarTarifa() {
        cine.registrarTarifa(tarifa);

        assertNotNull(cine.buscarTarifa(TipoEntrada.GENERAL));
        assertEquals(tarifa, cine.buscarTarifa(TipoEntrada.GENERAL));

    }

    @Test
    void modificarTarifa() {
        cine.registrarTarifa(tarifa);

        cine.modificarTarifa(tarifa);

        assertEquals(tarifa, cine.buscarTarifa(tarifa.tipo()));


    }

    @Test
    void programarFuncion() {
        cine.programarFuncion(funcion);

        assertNotNull(cine.buscarFuncion(funcion.getCodigo()));
        assertEquals(funcion, cine.buscarFuncion(funcion.getCodigo()));
    }

    @Test
    void venderEntrada() {

        assertNotNull(venta);
        assertEquals("001", venta.getCodigo());

    }

    @Test
    void calcularValorEntrada() {
        cine.registrarTarifa(tarifa);

        double resultado = cine.calcularValorEntrada(funcion, tarifa.tipo());

        assertEquals(funcion.calcularValorEntrada(tarifa.valor()), resultado, 0.001);
    }

    @Test
    void consultarPeliculasDisponibles() {
        List<Pelicula> resultado = cine.consultarPeliculasDisponibles();

        assertNotNull(resultado);
        assertEquals(0, resultado.size());
    }

    @Test
    void consultarFunciones() {
        cine.programarFuncion(funcion);

        List<Funcion> resultado = cine.consultarFunciones();

        assertEquals(1, resultado.size());
        assertSame(funcion, resultado.get(0));
    }

    @Test
    void consultarFuncionesPorFecha() {
        cine.programarFuncion(funcion);

        List<Funcion> resultado = cine.consultarFuncionesPorFecha(funcion.getFecha());

        assertEquals(1, cine.consultarFuncionesPorFecha(funcion.getFecha()).size());


    }

    @Test
    void consultarAsientosDisponibles() {
        cine.registrarCliente(cliente);
        cine.registrarTarifa(tarifa);
        cine.programarFuncion(funcion);

        List<Asiento> resultado = cine.consultarAsientosDisponibles(funcion.getCodigo());

        assertEquals(funcion.getAsientos().size(), resultado.size());

    }

    @Test
    void contarEntradasVendidas() {
        sala = new Sala("S001", "Sala 1");
        sala.agregarAsiento('A', 1);
        sala.agregarAsiento('A', 2);

        funcion = new Funcion("F001", pelicula, sala,LocalDate.now(), LocalTime.now(), 10000);

        cine.registrarSala(sala);
        cine.registrarCliente(cliente);
        cine.registrarTarifa(tarifa);
        cine.programarFuncion(funcion);


        cine.venderEntrada("V001", cliente, funcion, TipoEntrada.GENERAL, 'A', 1);

        assertEquals(1, cine.contarEntradasVendidas());
    }

    @Test
    void calcularTotalIngresos() {
        sala = new Sala("S002", "Sala 2");
        sala.agregarAsiento('A', 1);
        sala.agregarAsiento('A', 2);

        funcion = new Funcion("F001", pelicula, sala,LocalDate.now(), LocalTime.now(), 10000);

        cine.registrarSala(sala);
        cine.registrarCliente(cliente);
        cine.registrarTarifa(tarifa);
        cine.programarFuncion(funcion);

        Venta venta = cine.venderEntrada("V001", cliente, funcion, TipoEntrada.GENERAL, 'A', 1);

        assertEquals(venta.getValorPagado(), cine.calcularTotalIngresos(), 0.001);
    }

    @Test
    void obtenerPeliculaMasVendida() {
        sala = new Sala("S003", "Sala 3");
        sala.agregarAsiento('A', 1);
        sala.agregarAsiento('A', 2);

        funcion = new Funcion("F001", pelicula, sala,LocalDate.now(), LocalTime.now(), 10000);

        cine.registrarPelicula(pelicula);
        cine.registrarSala(sala);
        cine.registrarCliente(cliente);
        cine.registrarTarifa(tarifa);
        cine.programarFuncion(funcion);

        cine.venderEntrada("V001", cliente, funcion, TipoEntrada.GENERAL, 'A', 1);

        assertSame(pelicula, cine.obtenerPeliculaMasVendida());
    }

    @Test
    void generarReporteOcupacion() {
        cine.programarFuncion(funcion);

        ReporteOcupacion reporte = cine.generarReporteOcupacion(funcion.getCodigo());

        assertNotNull(reporte);
        assertEquals(funcion.getCodigo(), reporte.codigoFuncion());
    }

    @Test
    void buscarPersona() {
        cine.registrarCliente(cliente);

        Persona encontrada = cine.buscarPersona(cliente.getIdentificacion());

        assertNotNull(encontrada);
        assertEquals(cliente, encontrada);
    }

    @Test
    void buscarCliente() {
        cine.registrarCliente(cliente);

        assertNull(cine.buscarPersona("9999"));
    }

    @Test
    void buscarOperador() {

        cine.registrarOperador(operador);

        Persona encontrada = cine.buscarPersona(operador.getIdentificacion());

        assertNotNull(encontrada);
        assertEquals(operador, encontrada);
    }

    @Test
    void buscarAdministrador() {
        cine.registrarAdministrador(admin);

        Persona encontrada = cine.buscarPersona(admin.getIdentificacion());

        assertNotNull(encontrada);
        assertEquals(admin, encontrada);
    }

    @Test
    void buscarPelicula() {
        cine.registrarPelicula(pelicula);

        Pelicula encontrada = cine.buscarPelicula(pelicula.getCodigo());

        assertNotNull(encontrada);
        assertEquals(pelicula, encontrada);
    }

    @Test
    void buscarSala() {
        cine.registrarSala(sala);

        Sala encontrada = cine.buscarSala(sala.getCodigo());

        assertNotNull(encontrada);
        assertEquals(sala, encontrada);
    }

    @Test
    void buscarFuncion() {
        cine.programarFuncion(funcion);

        Funcion encontrada = cine.buscarFuncion(funcion.getCodigo());

        assertNotNull(encontrada);
        assertEquals(funcion, encontrada);
    }

    @Test
    void buscarVenta() {
        sala = new Sala("S004", "Sala 4");
        sala.agregarAsiento('A', 1);
        sala.agregarAsiento('A', 2);

        funcion = new Funcion("F001", pelicula, sala,LocalDate.now(), LocalTime.now(), 10000);

        cine.registrarSala(sala);
        cine.registrarCliente(cliente);
        cine.registrarTarifa(tarifa);
        cine.programarFuncion(funcion);
        Venta venta = cine.venderEntrada("V001", cliente, funcion, TipoEntrada.GENERAL, 'A', 1);

        Venta encontrada = cine.buscarVenta("V001");

        assertNotNull(encontrada);
        assertSame(venta, encontrada);
    }
}