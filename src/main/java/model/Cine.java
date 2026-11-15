package model;

import exception.AsientoOcupadoException;
import exception.CodigoDuplicadoException;
import exception.FuncionNoEncontradaException;
import exception.SalaNoDisponibleException;
import exception.SinAsientosDisponiblesException;
import exception.VentaNoEncontradaException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cine {

    private String nombre;
    private String direccion;

    private List<Funcion> listCineFunciones;
    private List<Venta> listCineVentas;
    private List<Pelicula> listCinePeliculas;
    private List<Sala> listCineSalas;
    private List<Persona> listCinePersonas;
    private List<Cliente> listCineClientes;
    private List<Operador> listCineOperadores;
    private List<Tarifa> listCineTarifas;

    public Cine(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.listCineFunciones = new ArrayList<>();
        this.listCineVentas = new ArrayList<>();
        this.listCinePeliculas = new ArrayList<>();
        this.listCineSalas = new ArrayList<>();
        this.listCinePersonas = new ArrayList<>();
        this.listCineClientes = new ArrayList<>();
        this.listCineOperadores = new ArrayList<>();
        this.listCineTarifas = new ArrayList<>();
        // Tarifas por defecto - segun TU TipoEntrada real
        listCineTarifas.add(new Tarifa(TipoEntrada.GENERAL, 15000));
        listCineTarifas.add(new Tarifa(TipoEntrada.ESTUDIANTE, 12000));
        listCineTarifas.add(new Tarifa(TipoEntrada.NINO, 10000));
        listCineTarifas.add(new Tarifa(TipoEntrada.ADULTO_MAYOR, 11000));
    }

    // ===== PELICULAS - Administrador =====
    public void registrarPelicula(Pelicula pelicula) throws CodigoDuplicadoException {
        if (buscarPelicula(pelicula.getCodigo()) != null) {
            throw new CodigoDuplicadoException("Ya existe una película con código: " + pelicula.getCodigo());
        }
        listCinePeliculas.add(pelicula);
    }

    public void modificarPelicula(Pelicula pelicula) {
        Pelicula actual = buscarPelicula(pelicula.getCodigo());
        if (actual != null) {
            actual.setNombre(pelicula.getNombre());
            actual.setGeneroPelicula(pelicula.getGeneroPelicula());
            actual.setDuracionMinutos(pelicula.getDuracionMinutos());
            actual.setClasificacionPelicula(pelicula.getClasificacionPelicula());
            actual.setEstadoPelicula(pelicula.getEstadoPelicula());
        }
    }

    public void deshabilitarPelicula(String codigo) {
        Pelicula p = buscarPelicula(codigo);
        if (p != null) p.deshabilitar();
    }

    // ===== SALAS - Administrador =====
    public void registrarSala(Sala sala) throws CodigoDuplicadoException {
        if (buscarSala(sala.getCodigo()) != null) {
            throw new CodigoDuplicadoException("Ya existe una sala con código: " + sala.getCodigo());
        }
        listCineSalas.add(sala);
    }

    public void modificarSala(Sala sala) {
        Sala actual = buscarSala(sala.getCodigo());
        if (actual != null) {
            actual.setNombre(sala.getNombre());
            actual.setCapacidad(sala.getCapacidad());
            actual.setEstadoSala(sala.getEstadoSala());
        }
    }

    public void deshabilitarSala(String codigo) {
        Sala s = buscarSala(codigo);
        if (s != null) s.deshabilitar();
    }

    // ===== PERSONAS =====
    public void registrarCliente(Cliente cliente) {
        if (buscarCliente(cliente.getIdentificacion()) == null) {
            listCineClientes.add(cliente);
            listCinePersonas.add(cliente);
        }
    }

    public void registrarOperador(Operador operador) {
        if (buscarOperador(operador.getIdentificacion()) == null) {
            listCineOperadores.add(operador);
            listCinePersonas.add(operador);
        }
    }

    // ===== TARIFAS =====
    public void registrarTarifa(Tarifa tarifa) {
        listCineTarifas.removeIf(t -> t.tipoEntrada() == tarifa.tipoEntrada());
        listCineTarifas.add(tarifa);
    }

    public void modificarTarifa(Tarifa tarifa) {
        registrarTarifa(tarifa);
    }

    // ===== FUNCIONES - Administrador =====
    public void programarFuncion(Funcion funcion) throws SalaNoDisponibleException {
        if (!funcion.getSala().estaDisponible()) {
            throw new SalaNoDisponibleException("La sala está FUERA_DE_SERVICIO: " + funcion.getSala().getCodigo());
        }
        listCineFunciones.add(funcion);
    }

    // ===== VENTAS - Operador =====
    public double calcularValorEntrada(Funcion funcion, TipoEntrada tipo) {
        Tarifa tarifa = buscarTarifa(tipo);
        if (tarifa == null) return funcion.getValorBase();
        return tarifa.valor();
    }

    public Venta venderEntrada(String codigo, Cliente cliente, Funcion funcion, TipoEntrada tipo, char fila, int numero)
            throws FuncionNoEncontradaException, SinAsientosDisponiblesException, AsientoOcupadoException {

        if (funcion == null || buscarFuncion(funcion.getCodigo()) == null) {
            throw new FuncionNoEncontradaException("La función no existe: " + codigo);
        }
        if (funcion.contarAsientosDisponibles() == 0) {
            throw new SinAsientosDisponiblesException("No hay asientos disponibles en esta función");
        }

        Asiento asiento = funcion.buscarAsiento(fila, numero);
        if (asiento == null) {
            throw new AsientoOcupadoException("El asiento " + fila + numero + " no existe");
        }
        if (!asiento.estaDisponible()) {
            throw new AsientoOcupadoException("El asiento " + fila + numero + " está OCUPADO");
        }

        double valor = calcularValorEntrada(funcion, tipo);

        asiento.ocupar();
        // Venta de 7 argumentos - el estado ACTIVA se pone adentro de Venta
        Venta venta = new Venta(codigo, cliente, funcion, asiento, tipo, LocalDateTime.now(), valor);
        listCineVentas.add(venta);
        funcion.getListFuncionVentas().add(venta);
        return venta;
    }

    public void cancelarVenta(String codigoVenta) throws VentaNoEncontradaException {
        Venta venta = buscarVenta(codigoVenta);
        if (venta == null) {
            throw new VentaNoEncontradaException("La venta no existe: " + codigoVenta);
        }
        venta.cancelar();
    }

    // ===== CONSULTAS - Operador =====
    public List<Pelicula> consultarPeliculasDisponibles() {
        List<Pelicula> resultado = new ArrayList<>();
        for (Pelicula p : listCinePeliculas) {
            if (p.estaDisponible()) resultado.add(p);
        }
        return resultado;
    }

    public List<Funcion> consultarFunciones() {
        return new ArrayList<>(listCineFunciones);
    }

    public List<Funcion> consultarFuncionesPorFecha(LocalDate fecha) {
        List<Funcion> resultado = new ArrayList<>();
        for (Funcion f : listCineFunciones) {
            if (f.getFecha().equals(fecha) && f.estaProgramada()) resultado.add(f);
        }
        return resultado;
    }

    public List<Asiento> consultarAsientosDisponibles(String codigoFuncion) throws FuncionNoEncontradaException {
        Funcion f = buscarFuncion(codigoFuncion);
        if (f == null) throw new FuncionNoEncontradaException("Función no encontrada: " + codigoFuncion);
        return f.getAsientosDisponibles();
    }

    // ===== REPORTES =====
    public int contarEntradasVendidas() {
        int total = 0;
        for (Venta v : listCineVentas) {
            if (v.estaActiva()) total++;
        }
        return total;
    }

    public double calcularTotalIngresos() {
        double total = 0;
        for (Venta v : listCineVentas) {
            if (v.estaActiva()) total += v.getValorPagado();
        }
        return total;
    }

    public Pelicula obtenerPeliculaMasVendida() {
        Map<Pelicula, Integer> conteo = new HashMap<>();
        for (Venta v : listCineVentas) {
            if (!v.estaActiva()) continue;
            Pelicula p = v.getFuncion().getPelicula();
            conteo.put(p, conteo.getOrDefault(p, 0) + 1);
        }
        Pelicula mayor = null;
        int max = 0;
        for (Map.Entry<Pelicula, Integer> e : conteo.entrySet()) {
            if (e.getValue() > max) { max = e.getValue(); mayor = e.getKey(); }
        }
        return mayor;
    }

    public ReporteOcupacion generarReporteOcupacion(String codigoFuncion) throws FuncionNoEncontradaException {
        Funcion f = buscarFuncion(codigoFuncion);
        if (f == null) throw new FuncionNoEncontradaException("Función no encontrada: " + codigoFuncion);
        return new ReporteOcupacion(
                f.getCodigo(),
                f.contarAsientosDisponibles(),
                f.contarAsientosOcupados(),
                f.calcularPorcentajeOcupacion()
        );
    }

    // ===== BUSCAR =====
    public Persona buscarPersona(String identificacion) {
        for (Persona p : listCinePersonas) {
            if (p.getIdentificacion().equals(identificacion)) return p;
        }
        return null;
    }
    public Cliente buscarCliente(String identificacion) {
        for (Cliente c : listCineClientes) {
            if (c.getIdentificacion().equals(identificacion)) return c;
        }
        return null;
    }
    public Operador buscarOperador(String identificacion) {
        for (Operador o : listCineOperadores) {
            if (o.getIdentificacion().equals(identificacion)) return o;
        }
        return null;
    }
    public Pelicula buscarPelicula(String codigo) {
        for (Pelicula p : listCinePeliculas) {
            if (p.getCodigo().equals(codigo)) return p;
        }
        return null;
    }
    public Sala buscarSala(String codigo) {
        for (Sala s : listCineSalas) {
            if (s.getCodigo().equals(codigo)) return s;
        }
        return null;
    }
    public Funcion buscarFuncion(String codigo) {
        for (Funcion f : listCineFunciones) {
            if (f.getCodigo().equals(codigo)) return f;
        }
        return null;
    }
    public Venta buscarVenta(String codigo) {
        for (Venta v : listCineVentas) {
            if (v.getCodigo().equals(codigo)) return v;
        }
        return null;
    }
    public Tarifa buscarTarifa(TipoEntrada tipo) {
        for (Tarifa t : listCineTarifas) {
            if (t.tipoEntrada() == tipo) return t;
        }
        return null;
    }

    // ===== Getters / Setters =====
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public List<Funcion> getListCineFunciones() { return listCineFunciones; }
    public List<Venta> getListCineVentas() { return listCineVentas; }
    public List<Pelicula> getListCinePeliculas() { return listCinePeliculas; }
    public List<Sala> getListCineSalas() { return listCineSalas; }
    public List<Persona> getListCinePersonas() { return listCinePersonas; }
    public List<Cliente> getListCineClientes() { return listCineClientes; }
    public List<Operador> getListCineOperadores() { return listCineOperadores; }
    public List<Tarifa> getListCineTarifas() { return listCineTarifas; }

    @Override
    public String toString() {
        return "Cine{" + "nombre='" + nombre + '\'' + ", direccion='" + direccion + '\'' + '}';
    }
}