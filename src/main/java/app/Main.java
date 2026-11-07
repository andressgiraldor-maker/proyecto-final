package app;

import model.*;
import service.CinemaService;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Crear Cine - tu constructor es de 6 datos
        Cine cine = new Cine("Cine Armenia", "Armenia", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        CinemaService servicio = new CinemaService(cine);

        try {
            // 2. Datos de prueba
            Cliente cliente = new Cliente("123", "Juan Perez", "juan@gmail.com");
            servicio.crearCliente(cliente);

            Pelicula peli = new Pelicula("P001", "Avatar", 180, GeneroPelicula.CIENCIA_FICCION, EstadoPelicula.DISPONIBLE, ClasificacionPelicula.MAYORES_12, cine, new ArrayList<>());
            servicio.crearPelicula(peli);

            Sala sala = new Sala("S001", "Sala 1", 20, EstadoSala.DISPONIBLE, new ArrayList<>(), new ArrayList<>(), cine);
            servicio.crearSala(sala);

            Funcion funcion = new Funcion("F001", LocalDate.now(), LocalTime.of(19, 0), 15000, EstadoFuncion.PROGRAMADA, new ArrayList<>(), cine, new ArrayList<>(), peli, sala);
            servicio.crearFuncion(funcion);

            // Crear 5 asientos para esa funcion
            for (int i = 1; i <= 5; i++) {
                Asiento a = new Asiento("A" + i, 'A', i, EstadoAsiento.DISPONIBLE, funcion, new ArrayList<>());
                funcion.getListFuncionAsientos().add(a);
            }
            System.out.println("Datos de prueba creados: Cliente 123, Pelicula P001, Sala S001, Funcion F001 con asientos A1-A5");
        } catch (Exception e) {
            System.out.println("Error creando datos: " + e.getMessage());
        }

        // 3. Menu interactivo
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- CINE ARMENIA ---");
            System.out.println("1. Crear cliente");
            System.out.println("2. Vender entrada");
            System.out.println("3. Cancelar venta");
            System.out.println("4. Ver asientos disponibles de F001");
            System.out.println("5. Ver total ventas");
            System.out.println("0. Salir");
            System.out.print("Elige: ");
            opcion = sc.nextInt();
            sc.nextLine();

            try {
                if (opcion == 1) {
                    System.out.print("Identificacion: ");
                    String id = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Gmail: ");
                    String gmail = sc.nextLine();
                    servicio.crearCliente(new Cliente(id, nombre, gmail));
                    System.out.println("Cliente creado!");
                }
                else if (opcion == 2) {
                    System.out.print("Codigo venta (ej V001): ");
                    String codVenta = sc.nextLine();
                    System.out.print("Codigo funcion (F001): ");
                    String codFuncion = sc.nextLine();
                    System.out.print("Codigo asiento (A1-A5): ");
                    String codAsiento = sc.nextLine();
                    System.out.print("Identificacion cliente (123): ");
                    String idCliente = sc.nextLine();
                    Venta v = servicio.venderEntrada(codVenta, codFuncion, codAsiento, idCliente);
                    System.out.println("Venta exitosa! Valor: " + v.getValorPagado());
                }
                else if (opcion == 3) {
                    System.out.print("Codigo venta a cancelar: ");
                    String codVenta = sc.nextLine();
                    servicio.cancelarVenta(codVenta);
                    System.out.println("Venta cancelada, asiento liberado!");
                }
                else if (opcion == 4) {
                    System.out.println("Disponibles: " + servicio.listarAsientosDisponibles("F001"));
                }
                else if (opcion == 5) {
                    System.out.println("Total ventas activas: $" + servicio.calcularTotalVentas());
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
        System.out.println("Chao!");
        sc.close();
    }
}