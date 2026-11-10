package com.example.demo.model;

/**
 * Tarifa asociada a un tipo de entrada.
 * El valor representa el descuento como fraccion (0.0 = sin descuento, 0.2 = 20%).
 */
public record Tarifa(TipoEntrada tipo, double valor)
{
}
