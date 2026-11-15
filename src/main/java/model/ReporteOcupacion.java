package model;

public record ReporteOcupacion(
        String codigoFuncion,
        int asientosDisponibles,
        int asientosOcupados,
        double porcentajeOcupacion
) {}