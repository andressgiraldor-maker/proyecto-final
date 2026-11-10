package com.example.demo.model;

public class Cliente extends Persona
{

    private String gmail;
    private TipoCliente tipoCliente;

    /**
     * metodo constructor de la clase Cliente
     * @param identificacion del cliente
     * @param nombre del cliente
     * @param gmail del cliente
     */
    public Cliente(String identificacion, String nombre, String gmail, TipoCliente tipoCliente)
    {
        super(identificacion, nombre);
        this.gmail = gmail;
        this.tipoCliente = tipoCliente;
    }

    /**
     * metodo para obtener el rol del cliente
     * @return
     */
    @Override
    public String obtenerRol()
    {
        return "CLIENTE";
    }

    public String getGmail()
    {
        return gmail;
    }

    public void setGmail(String gmail)
    {
        this.gmail = gmail;
    }

    public TipoCliente getTipoCliente()
    {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente)
    {
        this.tipoCliente = tipoCliente;
    }
}
