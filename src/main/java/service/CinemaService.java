package service;

import model.*;
import java.time.LocalDate;
import java.util.List;

public class CinemaService {
    private Cine cine;

    public CinemaService(Cine cine) {
        this.cine = cine;
    }

    public Cine getCine() { return cine; }

    // ===== CLIENTE =====
    public void crearCliente(Cliente c) {
        cine.registrarCliente(c);
    }

    public Cliente buscarCliente(String id) {
        return cine.buscarCliente(id);
    }

    // ===== PELICULAS - ADMIN =====
    public void crearPelicula(Pelicula p) throws Exception {
        cine.registrarPelicula(p);
    }

    public void modificarPelicula(Pelicula p) {
        cine.modificarPelicula(p);
    }

    public void deshabilitarPelicula(String codigo) {
        cine.deshabilitarPelicula(codigo);
    }

    public List<Pelicula> listarPeliculas() {
        return cine.getListCinePeliculas();
    }

    public List<Pelicula> listarPeliculasDisponibles() {
        return cine.consultarPeliculasDisponibles();
    }

    // ===== SALAS - ADMIN =====
    public void crearSala(Sala s) throws Exception {
        cine.registrarSala(s);
    }

    public void modificarSala(Sala s) {
        cine.modificarSala(s);
    }

    public void deshabilitarSala(String codigo) {
        cine.deshabilitarSala(codigo);
    }

    public List<Sala> listarSalas() {
        return cine.getListCineSalas();
    }

    // ===== FUNCIONES - ADMIN =====
    public void crearFuncion(Funcion f) throws Exception {
        cine.programarFuncion(f);
    }

    public Funcion buscarFuncion(String codigo) {
        return cine.buscarFuncion(codigo);
    }

    public List<Funcion> listarFunciones() {
        return cine.consultarFunciones();
    }

    public List<Funcion> listarFuncionesPorFecha(LocalDate fecha) {
        return cine.consultarFuncionesPorFecha(fecha);
    }

    // ===== VENTAS - OPERADOR =====
    // Adaptador: el Main pasa codigos, aqui buscamos los objetos y llamamos a Cine.venderEntrada
    public Venta venderEntrada(String codVenta, String codFuncion, String codAsiento, String idCliente, TipoEntrada tipo) throws Exception {
        Funcion funcion = cine.buscarFuncion(codFuncion);
        if (funcion == null) throw new Exception("La funcion no existe: " + codFuncion);

        Cliente cliente = cine.buscarCliente(idCliente);
        if (cliente == null) throw new Exception("Cliente no existe: " + idCliente);

        // codAsiento es como "A1" -> fila='A', numero=1
        if (codAsiento == null || codAsiento.length() < 2)
            throw new Exception("Codigo de asiento invalido: " + codAsiento);
        char fila = codAsiento.charAt(0);
        int numero = Integer.parseInt(codAsiento.substring(1));

        return cine.venderEntrada(codVenta, cliente, funcion, tipo, fila, numero);
    }

    public void cancelarVenta(String codVenta) throws Exception {
        cine.cancelarVenta(codVenta);
    }

    // ===== CONSULTAS =====
    public List<Asiento> listarAsientosDisponibles(String codFuncion) throws Exception {
        return cine.consultarAsientosDisponibles(codFuncion);
    }

    public String consultarDisponibilidad(String codFuncion) throws Exception {
        Funcion f = cine.buscarFuncion(codFuncion);
        if (f == null) throw new Exception("Funcion no existe");
        StringBuilder sb = new StringBuilder();
        sb.append("Funcion: ").append(f.getPelicula().getNombre()).append("\n");
        sb.append("Sala: ").append(f.getSala().getNombre()).append(" | Hora: ").append(f.getHora()).append("\n");
        sb.append("Total: ").append(f.getListFuncionAsientos().size())
                .append(" | Disponibles: ").append(f.contarAsientosDisponibles())
                .append(" | Ocupados: ").append(f.contarAsientosOcupados()).append("\n");
        for (Asiento a : f.getListFuncionAsientos()) {
            sb.append(a.getFila()).append(a.getNumero()).append(" ")
                    .append(a.estaDisponible() ? "Disponible" : "Ocupado").append("\n");
        }
        return sb.toString();
    }

    // ===== REPORTES =====
    public int totalEntradasVendidas() {
        return cine.contarEntradasVendidas();
    }

    public double calcularTotalVentas() {
        return cine.calcularTotalIngresos();
    }

    public String peliculaMasVendida() {
        Pelicula p = cine.obtenerPeliculaMasVendida();
        return p == null ? "Sin ventas" : p.getNombre();
    }

    public String ocupacionFuncion(String codFuncion) throws Exception {
        Funcion f = cine.buscarFuncion(codFuncion);
        if (f == null) throw new Exception("Funcion no existe: " + codFuncion);
        int disp = f.contarAsientosDisponibles();
        int ocup = f.contarAsientosOcupados();
        double porc = f.calcularPorcentajeOcupacion();
        return "Disponibles: " + disp + " | Ocupados: " + ocup
                + " | Ocupacion: " + String.format("%.1f%%", porc);
    }

    public double getValorTarifa(TipoEntrada tipo) {
        Tarifa t = cine.buscarTarifa(tipo);
        return t == null ? 15000 : t.valor();
    }

    public void registrarTarifa(Tarifa t) {
        cine.registrarTarifa(t);
    }

    public void modificarTarifa(Tarifa t) {
        cine.modificarTarifa(t);
    }
}