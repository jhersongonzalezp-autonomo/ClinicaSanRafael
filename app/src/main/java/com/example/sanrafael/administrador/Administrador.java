package com.example.sanrafael.administrador;

import com.example.sanrafael.Empleado;

public class Administrador extends Empleado {

    private String permisos;
    private int numero_administrador;

    public Administrador(String nombre, String apellido, String tipoIdentidad, String identidad, int edad,
                         String nacionalidad, double estatura, String idEmpleado, double sueldo, String fechaIngreso, String permisos) {
        super(nombre, apellido, tipoIdentidad, identidad, edad, nacionalidad, estatura, idEmpleado, sueldo, fechaIngreso);
        this.permisos = permisos;
    }

    public void setNumero_administrador(int numero_administrador) {
        this.numero_administrador = numero_administrador;
    }

    public int getNumero_administrador() {
        return numero_administrador;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    @Override
    public double calcularSueldo() {
        return sueldo;
    }

    @Override
    public String generarInformacion() {
        return super.generarInformacion() + "\nN.º Administrador: " + numero_administrador + "\nPermisos: " + permisos;
    }
}
