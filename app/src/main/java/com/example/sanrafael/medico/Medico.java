package com.example.sanrafael.medico;

import com.example.sanrafael.Empleado;
import com.example.sanrafael.paciente.Paciente;

public class Medico extends Empleado {

    private String especialidad;
    private String numeroColegiado;

    public Medico(String nombre, String apellido, String tipoIdentidad, String identidad, int edad, String nacionalidad, double estatura,
                  String idEmpleado, double sueldo, String especialidad, String numeroColegiado) {
        super(nombre, apellido, tipoIdentidad, identidad, edad, nacionalidad, estatura, idEmpleado, sueldo, "17/09/2026");
        this.especialidad = especialidad;
        this.numeroColegiado = numeroColegiado;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(String numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }

    public void diagnosticar(Paciente paciente, String diagnostico) {
        System.out.println("El médico " + getNombreCompleto() + " diagnosticó a " + paciente.getNombreCompleto() + ": " + diagnostico);
        if (paciente.getHistorial() != null) {
            paciente.getHistorial().agregarDiagnostico(diagnostico);
        }
    }

    public void prescribir(Paciente paciente, String tratamiento) {
        System.out.println("Prescripción de " + tratamiento + " para " + paciente.getNombreCompleto() + " por el Dr. " + getApellido());
        if (paciente.getHistorial() != null) {
            paciente.getHistorial().agregarTratamiento(tratamiento);
        }
    }

    @Override
    public double calcularSueldo() {
        return this.sueldo;
    }

    @Override
    public String generarInformacion() {
        return super.generarInformacion()
                + "\nEspecialidad: " + especialidad
                + "\nColegiado: " + numeroColegiado;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Colegiado: " + numeroColegiado);
    }
}
