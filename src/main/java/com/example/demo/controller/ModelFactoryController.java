package com.example.demo.controller;

import com.example.demo.model.Administrador;
import com.example.demo.model.Asiento;
import com.example.demo.model.Cine;
import com.example.demo.model.Clasificacion;
import com.example.demo.model.Cliente;
import com.example.demo.model.Funcion;
import com.example.demo.model.Genero;
import com.example.demo.model.Operador;
import com.example.demo.model.Pelicula;
import com.example.demo.model.Persona;
import com.example.demo.model.Sala;
import com.example.demo.model.Tarifa;
import com.example.demo.model.TipoEntrada;
import com.example.demo.model.Venta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ModelFactoryController
{

    private static ModelFactoryController instancia;

    private Cine cine;

    private ModelFactoryController()
    {
        cine = new Cine("CINEMAUQ", "Armenia, Quindio");
        inicializarDatosPrueba();
    }

    public static ModelFactoryController getInstance()
    {
        if (instancia == null)
        {
            instancia = new ModelFactoryController();
        }
        return instancia;
    }

    private void inicializarDatosPrueba()
    {
        cine.registrarTarifa(new Tarifa(TipoEntrada.GENERAL, 0.0));
        cine.registrarTarifa(new Tarifa(TipoEntrada.ESTUDIANTE, 0.2));
        cine.registrarTarifa(new Tarifa(TipoEntrada.NINO, 0.3));
        cine.registrarTarifa(new Tarifa(TipoEntrada.ADULTO_MAYOR, 0.25));

        Pelicula interestelar = new Pelicula("P001", "Interestelar", Genero.CIENCIA_FICCION, 169, Clasificacion.MAYORES_12);
        cine.registrarPelicula(interestelar);

        Sala sala1 = new Sala("S01", "Sala 1");
        sala1.generarAsientos(5, 8);
        cine.registrarSala(sala1);

        Funcion funcion1 = new Funcion("F001", interestelar, sala1, LocalDate.now(), LocalTime.of(18, 30), 15000);
        cine.programarFuncion(funcion1);
    }

    public String getNombreCine()
    {
        return cine.getNombre();
    }

    public List<Pelicula> consultarPeliculas()
    {
        List<Pelicula> peliculas = cine.getPeliculas();
        return peliculas;
    }

    public List<Pelicula> consultarPeliculasDisponibles()
    {
        List<Pelicula> peliculas = cine.consultarPeliculasDisponibles();
        return peliculas;
    }

    public void registrarPelicula(Pelicula pelicula)
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

    public void eliminarPelicula(String codigo)
    {
        cine.eliminarPelicula(codigo);
    }

    public List<Sala> consultarSalas()
    {
        List<Sala> salas = cine.getSalas();
        return salas;
    }

    public void registrarSala(Sala sala)
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

    public void eliminarSala(String codigo)
    {
        cine.eliminarSala(codigo);
    }

    public List<Asiento> consultarAsientosSala(String codigoSala)
    {
        List<Asiento> asientos = cine.consultarAsientosDeSala(codigoSala);
        return asientos;
    }

    public void agregarAsiento(String codigoSala, char fila, int numero)
    {
        cine.agregarAsiento(codigoSala, fila, numero);
    }

    public void modificarAsiento(String codigoSala, char filaActual, int numeroActual, char filaNueva, int numeroNuevo)
    {
        cine.modificarAsiento(codigoSala, filaActual, numeroActual, filaNueva, numeroNuevo);
    }

    public void eliminarAsiento(String codigoSala, char fila, int numero)
    {
        cine.eliminarAsiento(codigoSala, fila, numero);
    }

    public List<Persona> consultarPersonas()
    {
        List<Persona> personas = cine.getPersonas();
        return personas;
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
        cine.registrarAdministrador(administrador);
    }

    public void modificarCliente(Cliente cliente)
    {
        cine.modificarCliente(cliente);
    }

    public void modificarOperador(Operador operador)
    {
        cine.modificarOperador(operador);
    }

    public void modificarAdministrador(Administrador administrador)
    {
        cine.modificarAdministrador(administrador);
    }

    public void eliminarPersona(String identificacion)
    {
        cine.eliminarPersona(identificacion);
    }

    public List<Funcion> consultarFunciones()
    {
        List<Funcion> funciones = cine.consultarFunciones();
        return funciones;
    }

    // ===== FUNCIONES =====
    public void programarFuncion(Funcion funcion) {
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

    public void cancelarVenta(String codigoVenta) throws Exception {
        cine.cancelarVenta(codigoVenta);
    }

    public List<Venta> consultarVentas() {
        return cine.getVentas();
    }

    // ===== TARIFAS =====
    public void registrarTarifa(Tarifa tarifa) {
        cine.registrarTarifa(tarifa);
    }

    public void modificarTarifa(Tarifa tarifa) {
        cine.modificarTarifa(tarifa);
    }

    public List<Tarifa> consultarTarifas() {
        return cine.getTarifas();
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