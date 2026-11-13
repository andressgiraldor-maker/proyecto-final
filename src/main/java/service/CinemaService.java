package service;

import exception.*;
import model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CinemaService {

    private Cine cine;
    private List<Pelicula> listPeliculas;
    private List<Sala> listSalas;
    private List<Funcion> listFunciones;
    private List<Venta> listVentas;
    private List<Cliente> listClientes;

    public CinemaService(Cine cine) {
        this.cine = cine;
        this.listPeliculas = new ArrayList<>();
        this.listSalas = new ArrayList<>();
        this.listFunciones = new ArrayList<>();
        this.listVentas = new ArrayList<>();
        this.listClientes = new ArrayList<>();
    }

    // ---------- PELICULA ----------

    public void crearPelicula(Pelicula pelicula) throws CodigoDuplicadoException {
        for (Pelicula p : listPeliculas) {
            if (p.getCodigo().equals(pelicula.getCodigo())) {
                throw new CodigoDuplicadoException("Ya existe una pelicula con codigo " + pelicula.getCodigo());
            }
        }
        listPeliculas.add(pelicula);
    }

    public Pelicula buscarPelicula(String codigo) {
        for (Pelicula p : listPeliculas) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }

    // ---------- SALA ----------

    public void crearSala(Sala sala) throws CodigoDuplicadoException {
        for (Sala s : listSalas) {
            if (s.getCodigo().equals(sala.getCodigo())) {
                throw new CodigoDuplicadoException("Ya existe una sala con codigo " + sala.getCodigo());
            }
        }
        listSalas.add(sala);
    }

    public Sala buscarSala(String codigo) {
        for (Sala s : listSalas) {
            if (s.getCodigo().equals(codigo)) {
                return s;
            }
        }
        return null;
    }

    // ---------- CLIENTE ----------

    public void crearCliente(Cliente cliente) throws CodigoDuplicadoException {
        for (Cliente c : listClientes) {
            if (c.getIdentificacion().equals(cliente.getIdentificacion())) {
                throw new CodigoDuplicadoException("Ya existe un cliente con identificacion " + cliente.getIdentificacion());
            }
        }
        listClientes.add(cliente);
    }

    public Cliente buscarCliente(String identificacion) {
        for (Cliente c : listClientes) {
            if (c.getIdentificacion().equals(identificacion)) {
                return c;
            }
        }
        return null;
    }

    // ---------- FUNCION ----------

    public void crearFuncion(Funcion funcion) throws CodigoDuplicadoException, SalaNoDisponibleException {
        for (Funcion f : listFunciones) {
            if (f.getCodigo().equals(funcion.getCodigo())) {
                throw new CodigoDuplicadoException("Ya existe una funcion con codigo " + funcion.getCodigo());
            }
        }
        if (funcion.getSala().getEstadoSala() != EstadoSala.DISPONIBLE) {
            throw new SalaNoDisponibleException("La sala " + funcion.getSala().getCodigo() + " no esta disponible");
        }
        listFunciones.add(funcion);
    }

    public Funcion buscarFuncion(String codigo) throws FuncionNoEncontradaException {
        for (Funcion f : listFunciones) {
            if (f.getCodigo().equals(codigo)) {
                return f;
            }
        }
        throw new FuncionNoEncontradaException("No se encontro la funcion con codigo " + codigo);
    }

    // ---------- VENTA - LO MAS IMPORTANTE ----------

    public Venta venderEntrada(String codigoVenta, String codigoFuncion, String codigoAsiento, String identificacionCliente)
            throws FuncionNoEncontradaException, AsientoOcupadoException, SinAsientosDisponiblesException, CodigoDuplicadoException {

        // 1. Buscar la funcion, si no existe lanza excepcion
        Funcion funcion = buscarFuncion(codigoFuncion);

        // 2. Verificar que la funcion este programada
        if (funcion.getEstadoFuncion() != EstadoFuncion.PROGRAMADA) {
            throw new FuncionNoEncontradaException("La funcion " + codigoFuncion + " no esta programada");
        }

        // 3. Buscar el cliente
        Cliente cliente = buscarCliente(identificacionCliente);
        if (cliente == null) {
            throw new CodigoDuplicadoException("No existe cliente con identificacion " + identificacionCliente);
        }

        // 4. Buscar el asiento dentro de la funcion
        Asiento asientoElegido = null;
        for (Asiento a : funcion.getListFuncionAsientos()) {
            if (a.getCodigo().equals(codigoAsiento)) {
                asientoElegido = a;
                break;
            }
        }
        if (asientoElegido == null) {
            throw new SinAsientosDisponiblesException("No se encontro el asiento " + codigoAsiento + " en la funcion");
        }

        // 5. Verificar que el asiento este disponible, si no, lanza la excepcion
        if (asientoElegido.getEstado() != EstadoAsiento.DISPONIBLE) {
            throw new AsientoOcupadoException("El asiento " + codigoAsiento + " ya esta ocupado");
        }

        // 6. Verificar que no exista otra venta con ese codigo
        for (Venta v : listVentas) {
            if (v.getCodigo().equals(codigoVenta)) {
                throw new CodigoDuplicadoException("Ya existe una venta con codigo " + codigoVenta);
            }
        }

        // 7. Crear la venta y marcar el asiento como ocupado
        Venta venta = new Venta(codigoVenta, LocalDate.now(), funcion.getValorBase(), EstadoVenta.ACTIVADA, funcion, cine, asientoElegido, cliente);
        asientoElegido.setEstado(EstadoAsiento.OCUPADO);
        listVentas.add(venta);
        funcion.getListFuncionVentas().add(venta);
        asientoElegido.getListAsientoVentas().add(venta);

        return venta;
    }

    public void cancelarVenta(String codigoVenta) throws VentaNoEncontradaException {
        Venta ventaEncontrada = null;
        for (Venta v : listVentas) {
            if (v.getCodigo().equals(codigoVenta)) {
                ventaEncontrada = v;
                break;
            }
        }
        if (ventaEncontrada == null) {
            throw new VentaNoEncontradaException("No se encontro la venta con codigo " + codigoVenta);
        }
        // Liberar el asiento y marcar la venta como cancelada
        ventaEncontrada.getAsiento().setEstado(EstadoAsiento.DISPONIBLE);
        ventaEncontrada.setEstadoVenta(EstadoVenta.CANCELADA);
    }

    public Venta buscarVenta(String codigo) throws VentaNoEncontradaException {
        for (Venta v : listVentas) {
            if (v.getCodigo().equals(codigo)) {
                return v;
            }
        }
        throw new VentaNoEncontradaException("No se encontro la venta con codigo " + codigo);
    }

    // ---------- REPORTES ----------

    public List<Asiento> listarAsientosDisponibles(String codigoFuncion) throws FuncionNoEncontradaException {
        Funcion funcion = buscarFuncion(codigoFuncion);
        List<Asiento> disponibles = new ArrayList<>();
        for (Asiento a : funcion.getListFuncionAsientos()) {
            if (a.getEstado() == EstadoAsiento.DISPONIBLE) {
                disponibles.add(a);
            }
        }
        return disponibles;
    }

    public double calcularTotalVentas() {
        double total = 0;
        for (Venta v : listVentas) {
            if (v.getEstadoVenta() == EstadoVenta.ACTIVADA) {
                total += v.getValorPagado();
            }
        }
        return total;
    }

    // ---------- GETTERS ----------

    public List<Pelicula> getListPeliculas() { return listPeliculas; }
    public List<Sala> getListSalas() { return listSalas; }
    public List<Funcion> getListFunciones() { return listFunciones; }
    public List<Venta> getListVentas() { return listVentas; }
    public List<Cliente> getListClientes() { return listClientes; }
    public Cine getCine() { return cine; }
}