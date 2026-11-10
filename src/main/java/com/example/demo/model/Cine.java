package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cine
{

    private String nombre;
    private String direccion;
    private List<Pelicula> peliculas;
    private List<Sala> salas;
    private List<Funcion> funciones;
    private List<Venta> ventas;
    private List<Persona> personas;
    private List<Tarifa> tarifas;
    private List<ReporteOcupacion> reportesOcupacion;

    public Cine(String nombre, String direccion)
    {
        this.nombre = nombre;
        this.direccion = direccion;
        this.peliculas = new ArrayList<>();
        this.salas = new ArrayList<>();
        this.funciones = new ArrayList<>();
        this.ventas = new ArrayList<>();
        this.personas = new ArrayList<>();
        this.tarifas = new ArrayList<>();
        this.reportesOcupacion = new ArrayList<>();
    }

    // ------------------------------------------------------------------
    // Gestion de peliculas
    // ------------------------------------------------------------------

    public void registrarPelicula(Pelicula pelicula)
    {
        Pelicula existente = buscarPelicula(pelicula.getCodigo());

        if (existente != null)
        {
            throw new IllegalArgumentException("Ya existe una pelicula con el codigo " + pelicula.getCodigo());
        }

        peliculas.add(pelicula);
    }

    public void modificarPelicula(Pelicula pelicula)
    {
        Pelicula existente = buscarPelicula(pelicula.getCodigo());

        if (existente == null)
        {
            throw new IllegalArgumentException("No existe una pelicula con el codigo " + pelicula.getCodigo());
        }

        existente.setNombre(pelicula.getNombre());
        existente.setGenero(pelicula.getGenero());
        existente.setDuracionMinutos(pelicula.getDuracionMinutos());
        existente.setClasificacion(pelicula.getClasificacion());
        existente.setEstado(pelicula.getEstado());
    }

    public void deshabilitarPelicula(String codigo)
    {
        Pelicula pelicula = buscarPelicula(codigo);

        if (pelicula == null)
        {
            throw new IllegalArgumentException("No existe una pelicula con el codigo " + codigo);
        }

        pelicula.deshabilitar();
    }

    public void eliminarPelicula(String codigo)
    {
        Pelicula pelicula = buscarPelicula(codigo);

        if (pelicula == null)
        {
            throw new IllegalArgumentException("No existe una pelicula con el codigo " + codigo);
        }

        for (Funcion funcion : funciones)
        {
            if (funcion.getPelicula().getCodigo().equals(codigo))
            {
                throw new IllegalStateException("No se puede eliminar la pelicula porque tiene funciones asociadas");
            }
        }

        peliculas.remove(pelicula);
    }

    // ------------------------------------------------------------------
    // Gestion de salas
    // ------------------------------------------------------------------

    public void registrarSala(Sala sala)
    {
        Sala existente = buscarSala(sala.getCodigo());

        if (existente != null)
        {
            throw new IllegalArgumentException("Ya existe una sala con el codigo " + sala.getCodigo());
        }

        salas.add(sala);
    }

    public void modificarSala(Sala sala)
    {
        Sala existente = buscarSala(sala.getCodigo());

        if (existente == null)
        {
            throw new IllegalArgumentException("No existe una sala con el codigo " + sala.getCodigo());
        }

        existente.setNombre(sala.getNombre());
        existente.setEstado(sala.getEstado());
    }

    public void deshabilitarSala(String codigo)
    {
        Sala sala = buscarSala(codigo);

        if (sala == null)
        {
            throw new IllegalArgumentException("No existe una sala con el codigo " + codigo);
        }

        sala.deshabilitar();
    }

    public void eliminarSala(String codigo)
    {
        Sala sala = buscarSala(codigo);

        if (sala == null)
        {
            throw new IllegalArgumentException("No existe una sala con el codigo " + codigo);
        }

        if (salaTieneFunciones(codigo))
        {
            throw new IllegalStateException("No se puede eliminar la sala porque tiene funciones asociadas");
        }

        salas.remove(sala);
    }

    public List<Asiento> consultarAsientosDeSala(String codigoSala)
    {
        Sala sala = buscarSala(codigoSala);

        if (sala == null)
        {
            throw new IllegalArgumentException("No existe una sala con el codigo " + codigoSala);
        }

        List<Asiento> resultado = sala.getAsientos();
        return resultado;
    }

    public void agregarAsiento(String codigoSala, char fila, int numero)
    {
        Sala sala = obtenerSalaEditable(codigoSala);
        sala.agregarAsiento(fila, numero);
    }

    public void modificarAsiento(String codigoSala, char filaActual, int numeroActual, char filaNueva, int numeroNuevo)
    {
        Sala sala = obtenerSalaEditable(codigoSala);
        sala.modificarAsiento(filaActual, numeroActual, filaNueva, numeroNuevo);
    }

    public void eliminarAsiento(String codigoSala, char fila, int numero)
    {
        Sala sala = obtenerSalaEditable(codigoSala);
        sala.eliminarAsiento(fila, numero);
    }

    /**
     * Las funciones guardan su propia copia de los asientos, por eso no se permite
     * cambiar los asientos de una sala que ya tiene funciones asociadas.
     */
    private Sala obtenerSalaEditable(String codigoSala)
    {
        Sala sala = buscarSala(codigoSala);

        if (sala == null)
        {
            throw new IllegalArgumentException("No existe una sala con el codigo " + codigoSala);
        }

        if (salaTieneFunciones(codigoSala))
        {
            throw new IllegalStateException("No se pueden modificar los asientos de una sala con funciones asociadas");
        }

        return sala;
    }

    private boolean salaTieneFunciones(String codigoSala)
    {
        boolean tiene = false;

        for (Funcion funcion : funciones)
        {
            if (funcion.getSala().getCodigo().equals(codigoSala))
            {
                tiene = true;
            }
        }

        return tiene;
    }

    // ------------------------------------------------------------------
    // Gestion de personas
    // ------------------------------------------------------------------

    public void registrarCliente(Cliente cliente)
    {
        Persona existente = buscarPersona(cliente.getIdentificacion());

        if (existente != null)
        {
            throw new IllegalArgumentException("Ya existe una persona con la identificacion " + cliente.getIdentificacion());
        }

        personas.add(cliente);
    }

    public void registrarOperador(Operador operador)
    {
        Persona existente = buscarPersona(operador.getIdentificacion());

        if (existente != null)
        {
            throw new IllegalArgumentException("Ya existe una persona con la identificacion " + operador.getIdentificacion());
        }

        personas.add(operador);
    }

    public void registrarAdministrador(Administrador administrador)
    {
        Persona existente = buscarPersona(administrador.getIdentificacion());

        if (existente != null)
        {
            throw new IllegalArgumentException("Ya existe una persona con la identificacion " + administrador.getIdentificacion());
        }

        personas.add(administrador);
    }

    public void modificarCliente(Cliente cliente)
    {
        Cliente existente = buscarCliente(cliente.getIdentificacion());

        if (existente == null)
        {
            throw new IllegalArgumentException("No existe un cliente con la identificacion " + cliente.getIdentificacion());
        }

        existente.setNombre(cliente.getNombre());
        existente.setGmail(cliente.getGmail());
        existente.setTipoCliente(cliente.getTipoCliente());
    }

    public void modificarOperador(Operador operador)
    {
        Operador existente = buscarOperador(operador.getIdentificacion());

        if (existente == null)
        {
            throw new IllegalArgumentException("No existe un operador con la identificacion " + operador.getIdentificacion());
        }

        existente.setNombre(operador.getNombre());
        existente.setCodigoEmpleado(operador.getCodigoEmpleado());
    }

    public void modificarAdministrador(Administrador administrador)
    {
        Administrador existente = buscarAdministrador(administrador.getIdentificacion());

        if (existente == null)
        {
            throw new IllegalArgumentException("No existe un administrador con la identificacion " + administrador.getIdentificacion());
        }

        existente.setNombre(administrador.getNombre());
        existente.setCodigoAdministrador(administrador.getCodigoAdministrador());
    }

    public void eliminarPersona(String identificacion)
    {
        Persona persona = buscarPersona(identificacion);

        if (persona == null)
        {
            throw new IllegalArgumentException("No existe una persona con la identificacion " + identificacion);
        }

        for (Venta venta : ventas)
        {
            boolean esCliente = venta.getCliente().getIdentificacion().equals(identificacion);
            boolean esOperador = venta.getOperador() != null && venta.getOperador().getIdentificacion().equals(identificacion);

            if (esCliente || esOperador)
            {
                throw new IllegalStateException("No se puede eliminar la persona porque tiene ventas asociadas");
            }
        }

        personas.remove(persona);
    }

    // ------------------------------------------------------------------
    // Gestion de tarifas
    // ------------------------------------------------------------------

    public void registrarTarifa(Tarifa tarifa)
    {
        Tarifa existente = buscarTarifa(tarifa.tipo());

        if (existente != null)
        {
            throw new IllegalArgumentException("Ya existe una tarifa para el tipo " + tarifa.tipo());
        }

        tarifas.add(tarifa);
    }

    public void modificarTarifa(Tarifa tarifa)
    {
        Tarifa existente = buscarTarifa(tarifa.tipo());

        if (existente == null)
        {
            throw new IllegalArgumentException("No existe una tarifa para el tipo " + tarifa.tipo());
        }

        int posicion = tarifas.indexOf(existente);
        tarifas.set(posicion, tarifa);
    }

    // ------------------------------------------------------------------
    // Gestion de funciones
    // ------------------------------------------------------------------

    public void programarFuncion(Funcion funcion)
    {
        Funcion existente = buscarFuncion(funcion.getCodigo());

        if (existente != null)
        {
            throw new IllegalArgumentException("Ya existe una funcion con el codigo " + funcion.getCodigo());
        }

        if (!funcion.getSala().estaDisponible())
        {
            throw new IllegalStateException("No se puede programar una funcion en una sala fuera de servicio");
        }

        funciones.add(funcion);
    }

    // ------------------------------------------------------------------
    // Ventas
    // ------------------------------------------------------------------

    public Venta venderEntrada(String codigo, Cliente cliente, Funcion funcion, TipoEntrada tipo, char fila, int numero)
    {
        Venta ventaExistente = buscarVenta(codigo);
        if (ventaExistente != null)
        {
            throw new IllegalArgumentException("Ya existe una venta con el codigo " + codigo);
        }

        Cliente clienteRegistrado = buscarCliente(cliente.getIdentificacion());
        if (clienteRegistrado == null)
        {
            throw new IllegalArgumentException("El cliente no esta registrado");
        }

        Funcion funcionRegistrada = buscarFuncion(funcion.getCodigo());
        if (funcionRegistrada == null)
        {
            throw new IllegalArgumentException("La funcion no existe");
        }

        if (!funcionRegistrada.estaProgramada())
        {
            throw new IllegalStateException("La funcion no esta programada");
        }

        if (funcionRegistrada.contarAsientosDisponibles() == 0)
        {
            throw new IllegalStateException("No hay asientos disponibles para la funcion");
        }

        Asiento asiento = funcionRegistrada.buscarAsiento(fila, numero);
        if (asiento == null)
        {
            throw new IllegalArgumentException("El asiento " + fila + numero + " no existe en la funcion");
        }

        if (!asiento.estaDisponible())
        {
            throw new IllegalStateException("El asiento " + asiento.getCodigo() + " esta ocupado");
        }

        Tarifa tarifa = buscarTarifa(tipo);
        if (tarifa == null)
        {
            throw new IllegalArgumentException("No existe una tarifa para el tipo " + tipo);
        }

        double valorEntrada = calcularValorEntrada(funcionRegistrada, tipo);

        asiento.ocupar();

        Venta venta = new Venta(codigo, clienteRegistrado, funcionRegistrada, asiento, tarifa, valorEntrada);
        ventas.add(venta);

        return venta;
    }

    public void cancelarVenta(String codigoVenta)
    {
        Venta venta = buscarVenta(codigoVenta);

        if (venta == null)
        {
            throw new IllegalArgumentException("No existe una venta con el codigo " + codigoVenta);
        }

        venta.cancelar();
    }

    public double calcularValorEntrada(Funcion funcion, TipoEntrada tipo)
    {
        Tarifa tarifa = buscarTarifa(tipo);

        if (tarifa == null)
        {
            throw new IllegalArgumentException("No existe una tarifa para el tipo " + tipo);
        }

        double valor;
        valor = funcion.calcularValorEntrada(tarifa.valor());
        return valor;
    }

    // ------------------------------------------------------------------
    // Consultas
    // ------------------------------------------------------------------

    public List<Pelicula> consultarPeliculasDisponibles()
    {
        List<Pelicula> disponibles = new ArrayList<>();

        for (Pelicula pelicula : peliculas)
        {
            if (pelicula.estaDisponible())
            {
                disponibles.add(pelicula);
            }
        }

        return disponibles;
    }

    public List<Funcion> consultarFunciones()
    {
        List<Funcion> resultado = new ArrayList<>();

        for (Funcion funcion : funciones)
        {
            if (funcion.estaProgramada())
            {
                resultado.add(funcion);
            }
        }

        return resultado;
    }

    public List<Funcion> consultarFuncionesPorFecha(LocalDate fecha)
    {
        List<Funcion> resultado = new ArrayList<>();

        for (Funcion funcion : funciones)
        {
            if (funcion.estaProgramada() && funcion.getFecha().equals(fecha))
            {
                resultado.add(funcion);
            }
        }

        return resultado;
    }

    public List<Asiento> consultarAsientosDisponibles(String codigoFuncion)
    {
        Funcion funcion = buscarFuncion(codigoFuncion);

        if (funcion == null)
        {
            throw new IllegalArgumentException("No existe una funcion con el codigo " + codigoFuncion);
        }

        List<Asiento> disponibles = new ArrayList<>();

        for (Asiento asiento : funcion.getAsientos())
        {
            if (asiento.estaDisponible())
            {
                disponibles.add(asiento);
            }
        }

        return disponibles;
    }

    // ------------------------------------------------------------------
    // Reportes
    // ------------------------------------------------------------------

    public int contarEntradasVendidas()
    {
        int contador = 0;

        for (Venta venta : ventas)
        {
            if (venta.estaActiva())
            {
                contador++;
            }
        }

        return contador;
    }

    public double calcularTotalIngresos()
    {
        double total = 0;

        for (Venta venta : ventas)
        {
            if (venta.estaActiva())
            {
                total += venta.getValorPagado();
            }
        }

        return total;
    }

    public Pelicula obtenerPeliculaMasVendida()
    {
        Pelicula masVendida = null;
        int mayorCantidad = 0;

        for (Pelicula pelicula : peliculas)
        {
            int cantidad = 0;

            for (Venta venta : ventas)
            {
                boolean mismaPelicula = venta.getFuncion().getPelicula().getCodigo().equals(pelicula.getCodigo());
                if (venta.estaActiva() && mismaPelicula)
                {
                    cantidad++;
                }
            }

            if (cantidad > mayorCantidad)
            {
                mayorCantidad = cantidad;
                masVendida = pelicula;
            }
        }

        return masVendida;
    }

    public ReporteOcupacion generarReporteOcupacion(String codigoFuncion)
    {
        Funcion funcion = buscarFuncion(codigoFuncion);

        if (funcion == null)
        {
            throw new IllegalArgumentException("No existe una funcion con el codigo " + codigoFuncion);
        }

        int disponibles = funcion.contarAsientosDisponibles();
        int ocupados = funcion.contarAsientosOcupados();
        double porcentaje = funcion.calcularPorcentajeOcupacion();

        ReporteOcupacion reporte = new ReporteOcupacion(codigoFuncion, disponibles, ocupados, porcentaje);
        reportesOcupacion.add(reporte);

        return reporte;
    }

    // ------------------------------------------------------------------
    // Busquedas
    // ------------------------------------------------------------------

    public Persona buscarPersona(String identificacion)
    {
        Persona encontrada = null;

        for (Persona persona : personas)
        {
            if (persona.getIdentificacion().equals(identificacion))
            {
                encontrada = persona;
            }
        }

        return encontrada;
    }

    public Cliente buscarCliente(String identificacion)
    {
        Cliente encontrado = null;

        for (Persona persona : personas)
        {
            if (persona instanceof Cliente && persona.getIdentificacion().equals(identificacion))
            {
                encontrado = (Cliente) persona;
            }
        }

        return encontrado;
    }

    public Operador buscarOperador(String identificacion)
    {
        Operador encontrado = null;

        for (Persona persona : personas)
        {
            if (persona instanceof Operador && persona.getIdentificacion().equals(identificacion))
            {
                encontrado = (Operador) persona;
            }
        }

        return encontrado;
    }

    public Administrador buscarAdministrador(String identificacion)
    {
        Administrador encontrado = null;

        for (Persona persona : personas)
        {
            if (persona instanceof Administrador && persona.getIdentificacion().equals(identificacion))
            {
                encontrado = (Administrador) persona;
            }
        }

        return encontrado;
    }

    public Pelicula buscarPelicula(String codigo)
    {
        Pelicula encontrada = null;

        for (Pelicula pelicula : peliculas)
        {
            if (pelicula.getCodigo().equals(codigo))
            {
                encontrada = pelicula;
            }
        }

        return encontrada;
    }

    public Sala buscarSala(String codigo)
    {
        Sala encontrada = null;

        for (Sala sala : salas)
        {
            if (sala.getCodigo().equals(codigo))
            {
                encontrada = sala;
            }
        }

        return encontrada;
    }

    public Funcion buscarFuncion(String codigo)
    {
        Funcion encontrada = null;

        for (Funcion funcion : funciones)
        {
            if (funcion.getCodigo().equals(codigo))
            {
                encontrada = funcion;
            }
        }

        return encontrada;
    }

    public Venta buscarVenta(String codigo)
    {
        Venta encontrada = null;

        for (Venta venta : ventas)
        {
            if (venta.getCodigo().equals(codigo))
            {
                encontrada = venta;
            }
        }

        return encontrada;
    }

    public Tarifa buscarTarifa(TipoEntrada tipo)
    {
        Tarifa encontrada = null;

        for (Tarifa tarifa : tarifas)
        {
            if (tarifa.tipo() == tipo)
            {
                encontrada = tarifa;
            }
        }

        return encontrada;
    }

    // ------------------------------------------------------------------
    // Getters y setters
    // ------------------------------------------------------------------

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public List<Pelicula> getPeliculas()
    {
        return peliculas;
    }

    public List<Sala> getSalas()
    {
        return salas;
    }

    public List<Funcion> getFunciones()
    {
        return funciones;
    }

    public List<Venta> getVentas()
    {
        return ventas;
    }

    public List<Persona> getPersonas()
    {
        return personas;
    }

    public List<Tarifa> getTarifas()
    {
        return tarifas;
    }

    public List<ReporteOcupacion> getReportesOcupacion()
    {
        return reportesOcupacion;
    }
}
