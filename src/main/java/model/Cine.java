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
    //Atributos de la clase Cine
    private String nombre;
    private String direccion;
//Relaciones de la clase cine
    private List<Funcion> listCineFunciones;
    private List<Venta> listCineVentas;
    private List<Pelicula> listCinePeliculas;
    private List<Sala> listCineSalas;
    private List<Persona> listCinePersonas;
    private List<Cliente> listCineClientes;
    private List<Operador> listCineOperadores;
    private List<Tarifa> listCineTarifas;

    /**
     * metodo constructor de la clase Cine
     * @param nombre del cine
     * @param direccion del cine
     */
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
        listCineTarifas.add(new Tarifa(TipoEntrada.GENERAL, 15000));
        listCineTarifas.add(new Tarifa(TipoEntrada.ESTUDIANTE, 12000));
        listCineTarifas.add(new Tarifa(TipoEntrada.NINO, 10000));
        listCineTarifas.add(new Tarifa(TipoEntrada.ADULTO_MAYOR, 11000));
    }

    // ===== PELICULAS - Administrador =====

    /**
     * metodo para regsitraar pelicula en cine
     * @param pelicula pelicula
     * @throws CodigoDuplicadoException
     */
    public void registrarPelicula(Pelicula pelicula) throws CodigoDuplicadoException {
        if (buscarPelicula(pelicula.getCodigo()) != null) {
            throw new CodigoDuplicadoException("Ya existe una película con código: " + pelicula.getCodigo());
        }
        listCinePeliculas.add(pelicula);
    }

    /**
     * metodo para modificar una pelicula
     * @param pelicula pelicula
     */
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

    /**
     * metodo para deshbilitar una pelicula
     * @param codigo de la pelicula
     */
    public void deshabilitarPelicula(String codigo) {
        Pelicula p = buscarPelicula(codigo);
        if (p != null) p.deshabilitar();
    }

    /**
     * metodo para habilitar una sala
     * @param codigo de la sala
     */
    public void habilitarPelicula(String codigo) {
        Pelicula p = buscarPelicula(codigo);
        if (p != null) p.habilitar();
    }

    /**
     * metodo para regitrar una sala en cine
     * @param sala sala
     * @throws CodigoDuplicadoException
     */
    public void registrarSala(Sala sala) throws CodigoDuplicadoException {
        if (buscarSala(sala.getCodigo()) != null) {
            throw new CodigoDuplicadoException("Ya existe una sala con código: " + sala.getCodigo());
        }
        listCineSalas.add(sala);
    }

    /**
     * metodo para modifcar una sala
     * @param sala sala
     */
    public void modificarSala(Sala sala) {
        Sala actual = buscarSala(sala.getCodigo());
        if (actual != null) {
            actual.setNombre(sala.getNombre());
            actual.setCapacidad(sala.getCapacidad());
            actual.setEstadoSala(sala.getEstadoSala());
        }
    }

    /**
     * metodo para deshabiliatar una sala
     * @param codigo de la sala
     */
    public void deshabilitarSala(String codigo) {
        Sala s = buscarSala(codigo);
        if (s != null) s.deshabilitar();
    }

    /**
     * metodo para habilitar una sala
     * @param codigo de la sala
     */
    public void habilitarSala(String codigo) {
        Sala s = buscarSala(codigo);
        if (s != null) s.habilitar();
    }

    /**
     * metodo para regustra un cliente
     * @param cliente cliente
     */
    public void registrarCliente(Cliente cliente) {
        if (buscarCliente(cliente.getIdentificacion()) == null) {
            listCineClientes.add(cliente);
            listCinePersonas.add(cliente);
        }
    }

    /**
     * metodo para registrar un operador
     * @param operador operador
     */
    public void registrarOperador(Operador operador) {
        if (buscarOperador(operador.getIdentificacion()) == null) {
            listCineOperadores.add(operador);
            listCinePersonas.add(operador);
        }
    }


    /**
     * metodo para registrar una tarifa en cine
     * @param tarifa tarifa
     */
    public void registrarTarifa(Tarifa tarifa) {
        listCineTarifas.removeIf(t -> t.tipoEntrada() == tarifa.tipoEntrada());
        listCineTarifas.add(tarifa);
    }

    /**
     * metodo para modifar una tarifa en cine
     * @param tarifa tarifa
     */
    public void modificarTarifa(Tarifa tarifa) {
        registrarTarifa(tarifa);
    }

    /**
     * metodo para programar una funcion
     * @param funcion funcion
     * @throws SalaNoDisponibleException
     */
    public void programarFuncion(Funcion funcion) throws SalaNoDisponibleException {
        if (!funcion.getSala().estaDisponible()) {
            throw new SalaNoDisponibleException("La sala está FUERA_DE_SERVICIO: " + funcion.getSala().getCodigo());
        }
        listCineFunciones.add(funcion);
    }

    /**
     * metodo para calcular el valor de una entrada
     * @param funcion funcion
     * @param tipo de entrada
     * @return
     */
    public double calcularValorEntrada(Funcion funcion, TipoEntrada tipo) {
        Tarifa tarifa = buscarTarifa(tipo);
        if (tarifa == null) return funcion.getValorBase();
        return tarifa.valor();
    }

    /**
     * metodo para vender una entrada
     * @param codigo de la venta
     * @param cliente de la venta
     * @param funcion de la venta
     * @param tipo de la venta
     * @param fila de la venta
     * @param numero de la venta
     * @return
     * @throws FuncionNoEncontradaException
     * @throws SinAsientosDisponiblesException
     * @throws AsientoOcupadoException
     */
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
        Venta venta = new Venta(codigo, cliente, funcion, asiento, tipo, LocalDateTime.now(), valor);
        listCineVentas.add(venta);
        funcion.getListFuncionVentas().add(venta);
        return venta;
    }

    /**
     * metodo para vender varias entradas
     * @param codigo de la entrada
     * @param cliente de la entrada
     * @param funcion de la entrada
     * @param tipo de entrada
     * @param codigosAsientos de la entrada
     * @return
     * @throws Exception
     */
    public List<Venta> venderVariasEntradas(String codigo, Cliente cliente, Funcion funcion, TipoEntrada tipo, List<String> codigosAsientos) throws Exception {
        List<Venta> ventas = new ArrayList<>();
        // Validar primero que TODOS estén libres, para no vender A1 y que A2 falle a la mitad
        for (String cod : codigosAsientos) {
            String limpio = cod.trim().toUpperCase();
            char fila = limpio.charAt(0);
            int numero = Integer.parseInt(limpio.substring(1));
            Asiento a = funcion.buscarAsiento(fila, numero);
            if (a == null) throw new AsientoOcupadoException("El asiento " + limpio + " no existe");
            if (!a.estaDisponible()) throw new AsientoOcupadoException("El asiento " + limpio + " está OCUPADO");
        }
        for (String cod : codigosAsientos) {
            String limpio = cod.trim().toUpperCase();
            char fila = limpio.charAt(0);
            int numero = Integer.parseInt(limpio.substring(1));
            ventas.add(venderEntrada(codigo, cliente, funcion, tipo, fila, numero));
        }
        return ventas;
    }

    /**
     * metodo para canelar una venta
     * @param codigoVenta venta
     * @throws VentaNoEncontradaException
     */
    public void cancelarVenta(String codigoVenta) throws VentaNoEncontradaException {
        List<Venta> ventas = buscarVentasPorCodigo(codigoVenta);
        if (ventas.isEmpty()) {
            throw new VentaNoEncontradaException("La venta no existe: " + codigoVenta);
        }
        for (Venta v : ventas) {
            v.cancelar();
        }
    }

    /**
     * metodo para busacr una venta por su codigo
     * @param codigo de la venta
     * @return
     */
    public List<Venta> buscarVentasPorCodigo(String codigo) {
        List<Venta> resultado = new ArrayList<>();
        for (Venta v : listCineVentas) {
            if (v.getCodigo().equals(codigo)) resultado.add(v);
        }
        return resultado;
    }

    /**
     * metodo para consultar peliculas disponibles
     * @return
     */
    public List<Pelicula> consultarPeliculasDisponibles() {
        List<Pelicula> resultado = new ArrayList<>();
        for (Pelicula p : listCinePeliculas) {
            if (p.estaDisponible()) resultado.add(p);
        }
        return resultado;
    }

    /**
     * metodo para consultar funciones
     * @return
     */
    public List<Funcion> consultarFunciones() {
        return new ArrayList<>(listCineFunciones);
    }

    /**
     * metodo para consultar las funciones por fecha
     * @param fecha
     * @return
     */
    public List<Funcion> consultarFuncionesPorFecha(LocalDate fecha) {
        List<Funcion> resultado = new ArrayList<>();
        for (Funcion f : listCineFunciones) {
            if (f.getFecha().equals(fecha) && f.estaProgramada()) resultado.add(f);
        }
        return resultado;
    }

    /**
     * metodo para consulatar asiento disponibles
     * @param codigoFuncion de la funcion
     * @return
     * @throws FuncionNoEncontradaException
     */
    public List<Asiento> consultarAsientosDisponibles(String codigoFuncion) throws FuncionNoEncontradaException {
        Funcion f = buscarFuncion(codigoFuncion);
        if (f == null) throw new FuncionNoEncontradaException("Función no encontrada: " + codigoFuncion);
        return f.getAsientosDisponibles();
    }

    /**
     * metodo para consultar el detalle de asientos por funcion
     * @param codigoFuncion de la funcion
     * @return
     * @throws FuncionNoEncontradaException
     */
    public String consultarDetalleAsientosFuncion(String codigoFuncion) throws FuncionNoEncontradaException {
        Funcion f = buscarFuncion(codigoFuncion);
        if (f == null) throw new FuncionNoEncontradaException("Función no encontrada: " + codigoFuncion);
        StringBuilder sb = new StringBuilder();
        sb.append("Funcion ").append(f.getCodigo()).append(" - ").append(f.getPelicula().getNombre()).append("\n\n");
        for (Asiento a : f.getListFuncionAsientos()) {
            sb.append(a.getCodigo()).append(" ").append(a.estaDisponible() ? "Disponible" : "Ocupado").append("\n");
        }
        return sb.toString();
    }

    /**
     * metodo para contar las entradas mas vendidas
     * @return
     */
    public int contarEntradasVendidas() {
        int total = 0;
        for (Venta v : listCineVentas) {
            if (v.estaActiva()) total++;
        }
        return total;
    }

    /**
     * emtodo para calcular total de ingresos
     * @return
     */
    public double calcularTotalIngresos() {
        double total = 0;
        for (Venta v : listCineVentas) {
            if (v.estaActiva()) total += v.getValorPagado();
        }
        return total;
    }

    /**
     * metodo para obtener la pelicula mas vendida
     * @return
     */
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

    /**
     * metodo para generar un reporte por ocupacion
     * @param codigoFuncion funcion
     * @return
     * @throws FuncionNoEncontradaException
     */
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

    /**
     * metodo para buscar una persona
     * @param identificacion de la persona
     * @return
     */
    public Persona buscarPersona(String identificacion) {
        for (Persona p : listCinePersonas) {
            if (p.getIdentificacion().equals(identificacion)) return p;
        }
        return null;
    }

    /**
     * metodo para busacr un cliente
     * @param identificacion del cliente
     * @return
     */
    public Cliente buscarCliente(String identificacion) {
        for (Cliente c : listCineClientes) {
            if (c.getIdentificacion().equals(identificacion)) return c;
        }
        return null;
    }

    /**
     * metodo para busacr un operador por codigo
     * @param identificacion del operador
     * @return
     */
    public Operador buscarOperador(String identificacion) {
        for (Operador o : listCineOperadores) {
            if (o.getIdentificacion().equals(identificacion)) return o;
        }
        return null;
    }

    /**
     * metodo para buscar una pelicula por su codigo
     * @param codigo de la pelicula
     * @return
     */
    public Pelicula buscarPelicula(String codigo) {
        for (Pelicula p : listCinePeliculas) {
            if (p.getCodigo().equals(codigo)) return p;
        }
        return null;
    }

    /**
     * metodo para busacr una sala
     * @param codigo de la sala
     * @return
     */
    public Sala buscarSala(String codigo) {
        for (Sala s : listCineSalas) {
            if (s.getCodigo().equals(codigo)) return s;
        }
        return null;
    }

    /**
     * metodo para busacr una funcion
     * @param codigo de la funcion
     * @return
     */
    public Funcion buscarFuncion(String codigo) {
        for (Funcion f : listCineFunciones) {
            if (f.getCodigo().equals(codigo)) return f;
        }
        return null;
    }

    /**
     * metodo para buscar una venta por su codigo
     * @param codigo de  la venta
     * @return
     */
    public Venta buscarVenta(String codigo) {
        for (Venta v : listCineVentas) {
            if (v.getCodigo().equals(codigo)) return v;
        }
        return null;
    }

    /**
     * metodo para buscar una tarifa
     * @param tipo de entrada
     * @return
     */
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