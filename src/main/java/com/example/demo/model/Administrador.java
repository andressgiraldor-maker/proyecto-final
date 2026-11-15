package com.example.demo.model;

public class Administrador extends Persona
{

    private String codigoAdministrador;

    /**
     * metodo constructor de la clase aminitrador
     * @param identificacion del administrador
     * @param nombre del adminitrador
     * @param codigoAdministrador del adminitrador
     */
    public Administrador(String identificacion, String nombre, String codigoAdministrador)
    {
        super(identificacion, nombre);
        this.codigoAdministrador = codigoAdministrador;
    }

    /**
     * metodo para obtener el rol
     * @return
     */
    @Override
    public String obtenerRol()
    {
        return "ADMINISTRADOR";
    }

    public String getCodigoAdministrador()
    {
        return codigoAdministrador;
    }

    public void setCodigoAdministrador(String codigoAdministrador)
    {
        this.codigoAdministrador = codigoAdministrador;
    }
}
