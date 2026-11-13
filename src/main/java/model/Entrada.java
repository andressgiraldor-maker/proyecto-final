package model;

import java.time.LocalDate;

public record Entrada(String codigo, String sala, String funcion, LocalDate fecha, String asiento, TipoEntrada tipoEntrada, double valorPagado) {
}