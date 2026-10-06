package com.example.sanrafael;

import java.io.Serializable;

public class Persona implements Serializable {

    protected String nombre;
    protected String apellido;
    protected String tipoIdentidad;
    protected String identidad;
    protected String dni;
    protected int edad;
    protected String nacionalidad;
    protected double estatura;


    public Persona(String nombre, String apellido, String tipoIdentidad, String identidad, int edad, String nacionalidad, double estatura) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoIdentidad = tipoIdentidad;
        this.identidad = identidad;
        this.dni = identidad;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
        this.estatura = estatura;
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipoIdentidad() {
        return tipoIdentidad;
    }

    public void setTipoIdentidad(String tipoIdentidad) {
        this.tipoIdentidad = tipoIdentidad;
    }

    public String getIdentidad() {
        return identidad;
    }

    public void setIdentidad(String identidad) {
        this.identidad = identidad;
        this.dni = identidad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
        this.identidad = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public String generarInformacion() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre completo: ").append(getNombreCompleto()).append("\n");
        sb.append("Identidad: ").append(tipoIdentidad).append(" nº ").append(identidad).append("\n");
        sb.append("Edad: ").append(edad).append(" años\n");
        sb.append("Nacionalidad: ").append(nacionalidad).append("\n");
        sb.append("Estatura: ").append(estatura).append(" m");
        return sb.toString();
    }

    public void mostrarInformacion() {
        System.out.println(generarInformacion());
    }
}
