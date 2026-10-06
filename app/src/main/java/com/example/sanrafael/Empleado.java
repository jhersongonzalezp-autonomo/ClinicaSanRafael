package com.example.sanrafael;

import java.io.Serializable;

public abstract class Empleado extends Persona implements Serializable {

    protected String idEmpleado;
    protected double sueldo;
    protected String fechaIngreso;

    // 10-parameter constructor (7 persona + 3 employee)
    public Empleado(String nombre, String apellido, String tipoIdentidad, String identidad, int edad, String nacionalidad, double estatura, String idEmpleado, double sueldo, String fechaIngreso) {
        super(nombre, apellido, tipoIdentidad, identidad, edad, nacionalidad, estatura);
        this.idEmpleado = idEmpleado;
        this.sueldo = sueldo;
        this.fechaIngreso = fechaIngreso;
    }

    public String getIdEmpleado() {

        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {

        this.idEmpleado = idEmpleado;
    }

    public double getSueldo() {

        return sueldo;
    }

    public void setSueldo(double sueldo) {

        this.sueldo = sueldo;
    }

    public String getFechaIngreso() {

        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {

        this.fechaIngreso = fechaIngreso;
    }

    public abstract double calcularSueldo();

    @Override
    public void mostrarInformacion() {
        System.out.println("ID Empleado: " + idEmpleado + " | Nombre: " + getNombreCompleto() + " | DNI: "
                + identidad + " | Sueldo: $" + calcularSueldo());
    }

    @Override
    public String generarInformacion() {
        return super.generarInformacion() + "\nID Empleado: " + idEmpleado + "\nSueldo: $" + sueldo +
                "\nFecha Ingreso: " + fechaIngreso;
    }
}
