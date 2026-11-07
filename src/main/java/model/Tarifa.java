package model;

public class Tarifa {

    private TipoCliente tipoCliente;
    private double valor;

    /**
     * crea tarifa segun el tipo de cliente
     * @param tipoCliente
     */
    public Tarifa(TipoCliente tipoCliente) {
        if (tipoCliente == null) {
            throw new IllegalArgumentException("El tipo de cliente no puede ser nulo");
        }
        this.tipoCliente = tipoCliente;
        this.valor = calcularValor(tipoCliente);
    }

    /**
     * calcula valor de la tarifa
     * @param tipoCliente
     * @return
     */
    private double calcularValor(TipoCliente tipoCliente) {
        switch (tipoCliente) {
            case GENERAL:
                return 15000;
            case ESTUDIANTE:
                return 12000;
            case NINO:
                return 10000;
            case ADULTO_MAYOR:
                return 11000;
            default:
                return 15000;
        }
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Tarifa{" +
                "tipoCliente=" + tipoCliente +
                ", valor=" + valor +
                '}';
    }
}