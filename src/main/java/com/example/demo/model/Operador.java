package com.example.demo.model;

public class Operador extends Persona
{

    private String codigoEmpleado;

    /**
     * metodo constructor de la clase Operador
     * @param identificacion del operador
     * @param nombre del operador
     * @param codigoEmpleado del operador
     */
    public Operador(String identificacion, String nombre, String codigoEmpleado)
    {
        super(identificacion, nombre);
        this.codigoEmpleado = codigoEmpleado;
    }

    /**
     * metodo para obtener el rol del operador
     * @return
     */
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
