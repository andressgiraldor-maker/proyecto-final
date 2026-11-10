package com.example.demo.model;

public class Cliente extends Persona
{

    private String gmail;
    private TipoCliente tipoCliente;

    public Cliente(String identificacion, String nombre, String gmail, TipoCliente tipoCliente)
    {
        super(identificacion, nombre);
        this.gmail = gmail;
        this.tipoCliente = tipoCliente;
    }

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
