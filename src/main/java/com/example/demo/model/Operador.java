package com.example.demo.model;

public class Operador extends Persona
{

    private String codigoEmpleado;

    public Operador(String identificacion, String nombre, String codigoEmpleado)
    {
        super(identificacion, nombre);
        this.codigoEmpleado = codigoEmpleado;
    }

    @Override
    public String obtenerRol()
    {
        return "OPERADOR";
    }

    public String getCodigoEmpleado()
    {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado)
    {
        this.codigoEmpleado = codigoEmpleado;
    }
}
