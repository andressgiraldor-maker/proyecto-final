package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;

public record Entrada(
        String codigoVenta,
        String pelicula,
        String sala,
        String asiento,
        LocalDate fecha,
        LocalTime hora,
        String tipoEntrada,
        double valorPagado)
        {
}
