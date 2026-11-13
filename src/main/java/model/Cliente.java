package model;

public class Cliente extends Persona {

    private String gmail;

    /**
     * metodo para crear un cliente
     *
     * @param identificacion del cliente
     * @param nombre         del cliente
     * @param gmail          del cliente
     */
    public Cliente(String identificacion, String nombre, String gmail) {
        super(identificacion, nombre);
        if (gmail == null || gmail.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo del cliente no puede estar vacio");
        }
        this.gmail = gmail;
    }
    public String getGmail() {
        return gmail;
    }
    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "gmail='" + gmail + '\'' +
                '}';
    }
    @Override
    public String obtenerRol() {
        return "Cliente";
    }
}