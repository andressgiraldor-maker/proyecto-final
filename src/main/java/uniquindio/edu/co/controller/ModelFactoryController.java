package uniquindio.edu.co.controller;

import model.Administrador;
import model.Asiento;
import model.EstadoFuncion;
import model.Cine;
import model.ClasificacionPelicula;
import model.Cliente;
import model.EstadoAsiento;
import model.EstadoPelicula;
import model.EstadoSala;
import model.Funcion;
import model.GeneroPelicula;
import model.Operador;
import model.Pelicula;
import model.Persona;
import model.Sala;
import model.Tarifa;
import model.TipoEntrada;
import model.Venta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ModelFactoryController
{

    private static ModelFactoryController instancia;

    private Cine cine;

    private ModelFactoryController()
    {
        cine = new Cine("CINEMAUQ", "Armenia, Quindio");
        try {
            inicializarDatosPrueba();
        } catch (Exception e) {
            System.out.println("Error datos prueba: " + e.getMessage());
        }
    }

    public static ModelFactoryController getInstance()
    {
        if (instancia == null)
        {
            instancia = new ModelFactoryController();
        }
        return instancia;
    }

    private void inicializarDatosPrueba() throws Exception
    {
        Pelicula interestelar = new Pelicula("P001", "Interestelar", GeneroPelicula.CIENCIA_FICCION, 169, ClasificacionPelicula.MAYORES_12, EstadoPelicula.DISPONIBLE);
        cine.registrarPelicula(interestelar);

        Sala sala1 = new Sala("S01", "Sala 1", 0, EstadoSala.DISPONIBLE);
        sala1.generarAsientos(5, 8);
        cine.registrarSala(sala1);

        Funcion funcion1 = new Funcion("F001", LocalDate.now(), LocalTime.of(18, 30), 15000, EstadoFuncion.PROGRAMADA, interestelar, sala1);
        cine.programarFuncion(funcion1); // <-- ESTA LINEA FALTABA, si no, F001 no existe para vender
    }

    public String getNombreCine()
    {
        return cine.getNombre();
    }

    public List<Pelicula> consultarPeliculas()
    {
        return cine.getListCinePeliculas();
    }

    public List<Pelicula> consultarPeliculasDisponibles()
    {
        return cine.consultarPeliculasDisponibles();
    }

    public void registrarPelicula(Pelicula pelicula) throws Exception
    {
        cine.registrarPelicula(pelicula);
    }

    public void modificarPelicula(Pelicula pelicula)
    {
        cine.modificarPelicula(pelicula);
    }

    public void deshabilitarPelicula(String codigo)
    {
        cine.deshabilitarPelicula(codigo);
    }

    public void habilitarPelicula(String codigo)
    {
        cine.habilitarPelicula(codigo);
    }

    public void eliminarPelicula(String codigo)
    {
        Pelicula p = cine.buscarPelicula(codigo);
        if (p != null) cine.getListCinePeliculas().remove(p);
    }

    public List<Sala> consultarSalas()
    {
        return cine.getListCineSalas();
    }

    public void registrarSala(Sala sala) throws Exception
    {
        cine.registrarSala(sala);
    }

    public void modificarSala(Sala sala)
    {
        cine.modificarSala(sala);
    }

    public void deshabilitarSala(String codigo)
    {
        cine.deshabilitarSala(codigo);
    }

    public void habilitarSala(String codigo)
    {
        cine.habilitarSala(codigo);
    }

    public void eliminarSala(String codigo)
    {
        Sala s = cine.buscarSala(codigo);
        if (s != null) cine.getListCineSalas().remove(s);
    }

    public List<Asiento> consultarAsientosSala(String codigoSala)
    {
        Sala s = cine.buscarSala(codigoSala);
        if (s == null) return new ArrayList<>();
        return s.getListSalaAsientos();
    }

    public void agregarAsiento(String codigoSala, char fila, int numero)
    {
        Sala s = cine.buscarSala(codigoSala);
        if (s != null) {
            String codigoAsiento = codigoSala + "-" + fila + numero;
            s.getListSalaAsientos().add(new Asiento(codigoAsiento, fila, numero, EstadoAsiento.DISPONIBLE));
            s.setCapacidad(s.getListSalaAsientos().size());
        }
    }

    public void modificarAsiento(String codigoSala, char filaActual, int numeroActual, char filaNueva, int numeroNuevo)
    {
        Sala s = cine.buscarSala(codigoSala);
        if (s == null) return;
        Asiento viejo = null;
        for (Asiento a : s.getListSalaAsientos()) {
            if (a.getFila() == filaActual && a.getNumero() == numeroActual) {
                viejo = a;
                break;
            }
        }
        if (viejo != null) {
            s.getListSalaAsientos().remove(viejo);
            String codigoNuevo = codigoSala + "-" + filaNueva + numeroNuevo;
            s.getListSalaAsientos().add(new Asiento(codigoNuevo, filaNueva, numeroNuevo, EstadoAsiento.DISPONIBLE));
        }
    }

    public void eliminarAsiento(String codigoSala, char fila, int numero)
    {
        Sala s = cine.buscarSala(codigoSala);
        if (s != null) {
            s.getListSalaAsientos().removeIf(a -> a.getFila() == fila && a.getNumero() == numero);
            s.setCapacidad(s.getListSalaAsientos().size());
        }
    }

    public List<Persona> consultarPersonas()
    {
        return cine.getListCinePersonas();
    }

    public void registrarCliente(Cliente cliente)
    {
        cine.registrarCliente(cliente);
    }

    public void registrarOperador(Operador operador)
    {
        cine.registrarOperador(operador);
    }

    public void registrarAdministrador(Administrador administrador)
    {
        cine.getListCinePersonas().add(administrador);
    }

    public void modificarCliente(Cliente cliente)
    {
        cine.getListCineClientes().removeIf(c -> c.getIdentificacion().equals(cliente.getIdentificacion()));
        cine.getListCineClientes().add(cliente);
        cine.getListCinePersonas().removeIf(p -> p.getIdentificacion().equals(cliente.getIdentificacion()));
        cine.getListCinePersonas().add(cliente);
    }

    public void modificarOperador(Operador operador)
    {
        cine.getListCineOperadores().removeIf(o -> o.getIdentificacion().equals(operador.getIdentificacion()));
        cine.getListCineOperadores().add(operador);
        cine.getListCinePersonas().removeIf(p -> p.getIdentificacion().equals(operador.getIdentificacion()));
        cine.getListCinePersonas().add(operador);
    }

    public void modificarAdministrador(Administrador administrador)
    {
        for (Persona p : cine.getListCinePersonas()) {
            if (p.getIdentificacion().equals(administrador.getIdentificacion())) {
                p.setNombre(administrador.getNombre());
                break;
            }
        }
    }

    public void eliminarPersona(String identificacion)
    {
        cine.getListCinePersonas().removeIf(p -> p.getIdentificacion().equals(identificacion));
        cine.getListCineClientes().removeIf(c -> c.getIdentificacion().equals(identificacion));
        cine.getListCineOperadores().removeIf(o -> o.getIdentificacion().equals(identificacion));
    }

    public List<Funcion> consultarFunciones()
    {
        return cine.consultarFunciones();
    }

    // ===== FUNCIONES =====
    public void programarFuncion(Funcion funcion) throws Exception {
        cine.programarFuncion(funcion);
    }

    public Funcion buscarFuncion(String codigo) {
        return cine.buscarFuncion(codigo);
    }

    public List<Funcion> consultarFuncionesPorFecha(LocalDate fecha) {
        return cine.consultarFuncionesPorFecha(fecha);
    }

    public List<Asiento> consultarAsientosDisponibles(String codigoFuncion) throws Exception {
        return cine.consultarAsientosDisponibles(codigoFuncion);
    }

    public String consultarDetalleAsientosFuncion(String codigoFuncion) throws Exception {
        return cine.consultarDetalleAsientosFuncion(codigoFuncion);
    }

    // ===== VENTAS - OPERADOR =====
    public Venta venderEntrada(String codVenta, String codFuncion, String codAsiento, String idCliente, TipoEntrada tipo) throws Exception {
        Funcion funcion = cine.buscarFuncion(codFuncion);
        if (funcion == null) throw new Exception("La funcion no existe: " + codFuncion);

        Cliente cliente = cine.buscarCliente(idCliente);
        if (cliente == null) throw new Exception("Cliente no existe: " + idCliente);

        if (codAsiento == null || codAsiento.length() < 2)
            throw new Exception("Codigo asiento invalido: " + codAsiento);
        char fila = Character.toUpperCase(codAsiento.charAt(0));
        int numero = Integer.parseInt(codAsiento.substring(1));

        return cine.venderEntrada(codVenta, cliente, funcion, tipo, fila, numero);
    }

    public List<Venta> venderVariasEntradas(String codVenta, String codFuncion, String asientosTexto, String idCliente, TipoEntrada tipo) throws Exception {
        if (asientosTexto == null || asientosTexto.isBlank()) throw new Exception("Debes escribir al menos un asiento. Ej: A1,A2");
        Funcion funcion = cine.buscarFuncion(codFuncion);
        if (funcion == null) throw new Exception("La funcion no existe: " + codFuncion);
        Cliente cliente = cine.buscarCliente(idCliente);
        if (cliente == null) throw new Exception("Cliente no existe: " + idCliente);

        List<String> lista = new ArrayList<>();
        for (String s : asientosTexto.split(",")) {
            if (!s.trim().isEmpty()) lista.add(s.trim());
        }
        if (lista.isEmpty()) throw new Exception("No se detectaron asientos");
        return cine.venderVariasEntradas(codVenta, cliente, funcion, tipo, lista);
    }

    public void cancelarVenta(String codigoVenta) throws Exception {
        cine.cancelarVenta(codigoVenta);
    }

    public List<Venta> consultarVentas() {
        return cine.getListCineVentas();
    }

    // ===== TARIFAS =====
    public void registrarTarifa(Tarifa tarifa) {
        cine.registrarTarifa(tarifa);
    }

    public void modificarTarifa(Tarifa tarifa) {
        cine.modificarTarifa(tarifa);
    }

    public List<Tarifa> consultarTarifas() {
        return cine.getListCineTarifas();
    }

    public Tarifa buscarTarifa(TipoEntrada tipo) {
        return cine.buscarTarifa(tipo);
    }

    // ===== REPORTES =====
    public int contarEntradasVendidas() {
        return cine.contarEntradasVendidas();
    }

    public double calcularTotalIngresos() {
        return cine.calcularTotalIngresos();
    }

    public Pelicula obtenerPeliculaMasVendida() {
        return cine.obtenerPeliculaMasVendida();
    }

    public String obtenerPeliculaMasVendidaNombre() {
        Pelicula p = cine.obtenerPeliculaMasVendida();
        return p == null ? "Sin ventas" : p.getNombre();
    }

    public String consultarDisponibilidadFuncion(String codigoFuncion) throws Exception {
        Funcion f = cine.buscarFuncion(codigoFuncion);
        if (f == null) throw new Exception("Funcion no existe");
        return "Funcion: " + f.getPelicula().getNombre() + "\n"
                + "Sala: " + f.getSala().getNombre() + " | Hora: " + f.getHora() + "\n"
                + "Disponibles: " + f.contarAsientosDisponibles()
                + " | Ocupados: " + f.contarAsientosOcupados();
    }

    public String consultarOcupacionFuncion(String codigoFuncion) throws Exception {
        Funcion f = cine.buscarFuncion(codigoFuncion);
        if (f == null) throw new Exception("Funcion no existe: " + codigoFuncion);
        return "Disponibles: " + f.contarAsientosDisponibles()
                + " | Ocupados: " + f.contarAsientosOcupados()
                + " | Ocupacion: " + String.format("%.1f%%", f.calcularPorcentajeOcupacion());
    }

    // ===== BUSQUEDAS PARA LA GUI =====
    public Cliente buscarCliente(String identificacion) {
        return cine.buscarCliente(identificacion);
    }

    public Pelicula buscarPelicula(String codigo) {
        return cine.buscarPelicula(codigo);
    }

    public Sala buscarSala(String codigo) {
        return cine.buscarSala(codigo);
    }
}