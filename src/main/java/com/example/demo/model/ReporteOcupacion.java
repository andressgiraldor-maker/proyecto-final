package com.example.demo.model;

public record ReporteOcupacion(
        String codigoFuncion,
        int asientosDisponibles,
        int asientosOcupados,
        double porcentajeOcupacion)
        {
}
