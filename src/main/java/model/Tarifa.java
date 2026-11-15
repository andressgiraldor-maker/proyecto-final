package model;

public record Tarifa(TipoEntrada tipoEntrada, double valor) {
    public Tarifa {
        if (tipoEntrada == null) {
            throw new IllegalArgumentException("El tipo de entrada no puede ser nulo");
        }
        if (valor < 0) {
            throw new IllegalArgumentException("La tarifa no puede ser negativa");
        }
    }
}