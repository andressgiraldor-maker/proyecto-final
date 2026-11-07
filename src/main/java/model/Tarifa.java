package model;

public record Tarifa(double valor) {
    public Tarifa {
        if (valor < 0) {
            throw new IllegalArgumentException("El valor no puede ser negativo");
        }
    }
}