package model;

public class Cliente extends Persona {

    private String codigoCliente;

    /**
     * metodo para crear un cliente
     * @param identificacion del cliente
     * @param nombre del cliente
     * @param codigoCliente del cliente
     */
    public Cliente(String identificacion, String nombre, String codigoCliente) {
        super(identificacion, nombre);
        if (codigoCliente == null || codigoCliente.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo del cliente no puede estar vacio");
        }
        this.codigoCliente = codigoCliente;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    @Override
    public String toString() {
        return super.toString() + " - Codigo: " + codigoCliente;
    }
}