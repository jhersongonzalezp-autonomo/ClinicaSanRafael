package com.example.sanrafael.administrativo;

import com.example.sanrafael.Empleado;
import com.example.sanrafael.medico.Medico;
import com.example.sanrafael.paciente.Paciente;

public class Administrativo extends Empleado {

    private String departamento;
    private String cargo;

    public Administrativo(String nombre, String apellido, String tipoIdentidad, String identidad, int edad, String nacionalidad, double estatura, String idEmpleado, double sueldo, String departamento, String cargo) {
        super(nombre, apellido, tipoIdentidad, identidad, edad, nacionalidad, estatura, idEmpleado, sueldo, "17/09/2026");
        this.departamento = departamento;
        this.cargo = cargo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public double calcularSueldo() {
        return this.sueldo;
    }

    public void agendarCita(Paciente paciente, Medico medico, String fecha) {
        System.out.println("Cita agendada para " + paciente.getNombreCompleto() + " con el Dr. " + medico.getNombreCompleto()+ " en la fecha: " + fecha);
    }

    public void generarFactura(Paciente paciente) {
        System.out.println("Generando factura para el paciente " + paciente.getNombreCompleto());
    }

    @Override
    public String generarInformacion() {
        return super.generarInformacion()
                + "\nDepartamento: " + departamento
                + "\nCargo: " + cargo;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Departamento: " + departamento);
        System.out.println("Cargo: " + cargo);
    }
}
