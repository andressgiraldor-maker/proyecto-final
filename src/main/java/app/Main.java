package app;

import model.*;
import service.CinemaService;
import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cine cine = new Cine("Cine Armenia", "Armenia");
        CinemaService servicio = new CinemaService(cine);

        try {
            servicio.crearCliente(new Cliente("123", "Juan Perez", "juan@gmail.com", TipoCliente.GENERAL));
            Pelicula peli = new Pelicula("P001", "Avatar", GeneroPelicula.CIENCIA_FICCION, 180, ClasificacionPelicula.MAYORES_12, EstadoPelicula.DISPONIBLE);
            servicio.crearPelicula(peli);
            Sala sala = new Sala("S001", "Sala 1", 20, EstadoSala.DISPONIBLE);
            sala.generarAsientos(1, 5);
            servicio.crearSala(sala);
            servicio.crearFuncion(new Funcion("F001", LocalDate.now(), LocalTime.of(19, 0), 15000, EstadoFuncion.PROGRAMADA, peli, sala));
            System.out.println("Datos de prueba: Cliente 123, Pelicula P001, Sala S001, Funcion F001 (A1-A5)");
        } catch (Exception e) {
            System.out.println("Error datos: " + e.getMessage());
        }

        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n========== CINE ARMENIA ==========");
            System.out.println("--- OPERADOR ---");
            System.out.println("1. Crear cliente");
            System.out.println("2. Vender entrada");
            System.out.println("3. Cancelar venta");
            System.out.println("4. Ver asientos disponibles de una funcion");
            System.out.println("5. Consultar disponibilidad (A1 Disponible/Ocupado)");
            System.out.println("6. Ver peliculas disponibles");
            System.out.println("7. Ver todas las funciones");
            System.out.println("8. Ver funciones por fecha");
            System.out.println("--- ADMINISTRADOR ---");
            System.out.println("9. Registrar pelicula");
            System.out.println("10. Modificar pelicula");
            System.out.println("11. Deshabilitar pelicula");
            System.out.println("12. Registrar sala");
            System.out.println("13. Modificar sala");
            System.out.println("14. Deshabilitar sala");
            System.out.println("15. Programar funcion");
            System.out.println("16. Registrar / Modificar tarifa");
            System.out.println("--- REPORTES ---");
            System.out.println("17. Total entradas vendidas");
            System.out.println("18. Total ingresos");
            System.out.println("19. Pelicula mas vendida");
            System.out.println("20. Ocupacion de una funcion");
            System.out.println("0. Salir");
            System.out.print("Elige: ");
            try { opcion = Integer.parseInt(sc.nextLine()); }
            catch (Exception e) { opcion = -1; }

            try {
                if (opcion == 1) {
                    System.out.print("Identificacion: "); String id = sc.nextLine();
                    System.out.print("Nombre: "); String nom = sc.nextLine();
                    System.out.print("Gmail: "); String gmail = sc.nextLine();
                    System.out.print("Tipo cliente (GENERAL): "); TipoCliente tc = TipoCliente.valueOf(sc.nextLine().toUpperCase().trim());
                    servicio.crearCliente(new Cliente(id, nom, gmail, tc));
                    System.out.println("Cliente creado!");
                }
                else if (opcion == 2) {
                    System.out.print("Codigo venta (V001): "); String cv = sc.nextLine();
                    System.out.print("Codigo funcion (F001): "); String cf = sc.nextLine();
                    System.out.print("Codigo asiento (A1): "); String ca = sc.nextLine().toUpperCase().trim();
                    System.out.print("ID cliente (123): "); String idc = sc.nextLine();
                    System.out.print("Tipo entrada (GENERAL / ESTUDIANTE / NINO / ADULTO_MAYOR): ");
                    TipoEntrada t = TipoEntrada.valueOf(sc.nextLine().toUpperCase().trim());
                    Venta v = servicio.venderEntrada(cv, cf, ca, idc, t);
                    System.out.println("Venta exitosa! Valor: " + v.getValorPagado());
                }
                else if (opcion == 3) {
                    System.out.print("Codigo venta a cancelar: "); servicio.cancelarVenta(sc.nextLine());
                    System.out.println("Venta cancelada, asiento liberado!");
                }
                else if (opcion == 4) {
                    System.out.print("Codigo funcion: ");
                    List<Asiento> d = servicio.listarAsientosDisponibles(sc.nextLine());
                    for (Asiento a : d) System.out.println(a.getFila() + "" + a.getNumero() + " Disponible");
                    System.out.println("Total: " + d.size());
                }
                else if (opcion == 5) {
                    System.out.print("Codigo funcion: "); System.out.println(servicio.consultarDisponibilidad(sc.nextLine()));
                }
                else if (opcion == 6) {
                    for (Pelicula p : servicio.listarPeliculasDisponibles())
                        System.out.println(p.getCodigo() + " | " + p.getNombre() + " | " + p.getGeneroPelicula() + " | " + p.getDuracionMinutos() + " min");
                }
                else if (opcion == 7) {
                    for (Funcion f : servicio.listarFunciones())
                        System.out.println(f.getCodigo() + " | " + f.getPelicula().getNombre() + " | Sala " + f.getSala().getNombre() + " | " + f.getFecha() + " " + f.getHora() + " | Disp: " + f.contarAsientosDisponibles());
                }
                else if (opcion == 8) {
                    System.out.print("Fecha (AAAA-MM-DD): "); LocalDate fe = LocalDate.parse(sc.nextLine());
                    for (Funcion f : servicio.listarFuncionesPorFecha(fe))
                        System.out.println(f.getCodigo() + " | " + f.getPelicula().getNombre() + " | " + f.getHora());
                }
                else if (opcion == 9) {
                    System.out.print("Codigo: "); String cod = sc.nextLine();
                    System.out.print("Nombre: "); String nom = sc.nextLine();
                    System.out.print("Genero (CIENCIA_FICCION / ACCION / COMEDIA): "); GeneroPelicula g = GeneroPelicula.valueOf(sc.nextLine().toUpperCase().trim());
                    System.out.print("Duracion: "); int dur = Integer.parseInt(sc.nextLine());
                    System.out.print("Clasificacion (MAYORES_12 / TODO_PUBLICO): "); ClasificacionPelicula cl = ClasificacionPelicula.valueOf(sc.nextLine().toUpperCase().trim());
                    servicio.crearPelicula(new Pelicula(cod, nom, g, dur, cl, EstadoPelicula.DISPONIBLE));
                    System.out.println("Pelicula registrada!");
                }
                else if (opcion == 10) {
                    System.out.print("Codigo de la pelicula a modificar: "); String cod = sc.nextLine();
                    System.out.print("Nuevo nombre: "); String nom = sc.nextLine();
                    System.out.print("Nuevo genero: "); GeneroPelicula g = GeneroPelicula.valueOf(sc.nextLine().toUpperCase().trim());
                    System.out.print("Nueva duracion: "); int dur = Integer.parseInt(sc.nextLine());
                    System.out.print("Nueva clasificacion: "); ClasificacionPelicula cl = ClasificacionPelicula.valueOf(sc.nextLine().toUpperCase().trim());
                    servicio.modificarPelicula(new Pelicula(cod, nom, g, dur, cl, EstadoPelicula.DISPONIBLE));
                    System.out.println("Pelicula modificada!");
                }
                else if (opcion == 11) {
                    System.out.print("Codigo pelicula a deshabilitar: "); servicio.deshabilitarPelicula(sc.nextLine());
                    System.out.println("Pelicula deshabilitada! Ya no sale en la opcion 6");
                }
                else if (opcion == 12) {
                    System.out.print("Codigo sala: "); String cod = sc.nextLine();
                    System.out.print("Nombre: "); String nom = sc.nextLine();
                    System.out.print("Filas: "); int fi = Integer.parseInt(sc.nextLine());
                    System.out.print("Columnas: "); int co = Integer.parseInt(sc.nextLine());
                    Sala s = new Sala(cod, nom, 0, EstadoSala.DISPONIBLE);
                    s.generarAsientos(fi, co);
                    servicio.crearSala(s);
                    System.out.println("Sala creada con " + s.getCapacidad() + " asientos!");
                }
                else if (opcion == 13) {
                    System.out.print("Codigo sala a modificar: "); String cod = sc.nextLine();
                    System.out.print("Nuevo nombre: "); String nom = sc.nextLine();
                    System.out.print("Nueva capacidad: "); int cap = Integer.parseInt(sc.nextLine());
                    servicio.modificarSala(new Sala(cod, nom, cap, EstadoSala.DISPONIBLE));
                    System.out.println("Sala modificada!");
                }
                else if (opcion == 14) {
                    System.out.print("Codigo sala a deshabilitar: "); servicio.deshabilitarSala(sc.nextLine());
                    System.out.println("Sala deshabilitada!");
                }
                else if (opcion == 15) {
                    System.out.print("Codigo funcion: "); String cod = sc.nextLine();
                    System.out.print("Codigo pelicula: "); Pelicula p = cine.buscarPelicula(sc.nextLine());
                    System.out.print("Codigo sala: "); Sala s = cine.buscarSala(sc.nextLine());
                    if (p == null || s == null) throw new Exception("Pelicula o sala no existe");
                    System.out.print("Fecha (AAAA-MM-DD): "); LocalDate fe = LocalDate.parse(sc.nextLine());
                    System.out.print("Hora (19:00): "); LocalTime ho = LocalTime.parse(sc.nextLine());
                    System.out.print("Valor base: "); double va = Double.parseDouble(sc.nextLine());
                    servicio.crearFuncion(new Funcion(cod, fe, ho, va, EstadoFuncion.PROGRAMADA, p, s));
                    System.out.println("Funcion programada!");
                }
                else if (opcion == 16) {
                    System.out.print("Tipo entrada (GENERAL / ESTUDIANTE / NINO / ADULTO_MAYOR): ");
                    TipoEntrada t = TipoEntrada.valueOf(sc.nextLine().toUpperCase().trim());
                    System.out.print("Nuevo valor: "); double va = Double.parseDouble(sc.nextLine());
                    servicio.registrarTarifa(new Tarifa(t, va));
                    System.out.println("Tarifa actualizada!");
                }
                else if (opcion == 17) {
                    System.out.println("Total entradas vendidas (activas): " + servicio.totalEntradasVendidas());
                }
                else if (opcion == 18) {
                    System.out.println("Total ingresos: $" + servicio.calcularTotalVentas());
                }
                else if (opcion == 19) {
                    System.out.println("Pelicula mas vendida: " + servicio.peliculaMasVendida());
                }
                else if (opcion == 20) {
                    System.out.print("Codigo funcion: "); System.out.println(servicio.ocupacionFuncion(sc.nextLine()));
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
        System.out.println("Chao!");
        sc.close();
    }
}