package com.example.sanrafael.enfermero;

import com.example.sanrafael.Empleado;
import com.example.sanrafael.paciente.Paciente;

public class Enfermero extends Empleado {

    protected String turno;

    public Enfermero(String nombre, String apellido, String tipoIdentidad, String identidad, int edad, String nacionalidad, double estatura, String idEmpleado, double sueldo, String turno) {
        super(nombre, apellido, tipoIdentidad, identidad, edad, nacionalidad, estatura, idEmpleado, sueldo, "17/09/2026");
        this.turno = turno;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public double calcularSueldo() {
        return this.sueldo;
    }

    public void tomarSignosVitales(Paciente paciente) {
        System.out.println("Tomando signos vitales al paciente " + paciente.getNombreCompleto() + " por el enfermero/a " + getNombreCompleto());
    }

    public void administrarMedicamento(Paciente paciente, String medicamento) {
        System.out.println("Administrando " + medicamento + " al paciente " + paciente.getNombreCompleto());
        if (paciente.getHistorial() != null) {
            paciente.getHistorial().agregarTratamiento(medicamento);
        }
    }

    @Override
    public String generarInformacion() {
        return super.generarInformacion() + "\nTurno: " + turno;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Turno: " + turno);
    }
}
