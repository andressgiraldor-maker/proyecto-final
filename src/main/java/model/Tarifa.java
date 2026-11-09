package model;

import javax.swing.*;

public record Tarifa(double valor) {
    public Tarifa {
        if (valor < 0) {
            JOptionPane.showMessageDialog(null, "la tarifa no puede ser nagtiva");
        }
    }
}