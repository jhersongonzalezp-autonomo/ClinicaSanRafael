package com.example.sanrafael.paciente;

import com.example.sanrafael.Persona;
import com.example.sanrafael.medico.Consulta;
import com.example.sanrafael.medico.HistorialMedico;

public class Paciente extends Persona {

    private String numeroHistoria;
    private HistorialMedico historial;

    public static Paciente pacienteActual = new Paciente(
            "Juan", "Pérez", "CC", "106168945", "99887766D", 55, "Colombiano", 1.70, "HC-2024-001"
    );

    static {
        if (pacienteActual.getHistorial().getDiagnosticos().isEmpty()) {
            pacienteActual.getHistorial().agregarDiagnostico("Dolor torácico agudo de origen coronario probable.");
        }
        if (pacienteActual.getHistorial().getTratamientos().isEmpty()) {
            pacienteActual.getHistorial().agregarTratamiento("Aspirina 100mg cada 24 horas");
        }
    }

    public Paciente(String nombre, String apellido, String tipoIdentidad, String identidad, String dni, int edad, String nacionalidad, double estatura, String numeroHistoria) {
        super(nombre, apellido, tipoIdentidad, identidad, edad, nacionalidad, estatura);
        this.dni = dni;
        this.numeroHistoria = numeroHistoria;
        this.historial = new HistorialMedico();
    }

    public void agregarConsulta(Consulta consulta) {
        System.out.println("Consulta agregada al paciente " + getNombreCompleto());
    }

    public void mostrarHistorial() {
        System.out.println("Historial de " + getNombreCompleto() + " (N " + numeroHistoria + "):");
        historial.mostrar();
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Paciente: " + getNombreCompleto() + " | DNI: " + dni + " | N Historia: " + numeroHistoria);
    }

    public String getNumeroHistoria() {
        return numeroHistoria;
    }

    public void setNumeroHistoria(String numeroHistoria) {
        this.numeroHistoria = numeroHistoria;
    }

    public HistorialMedico getHistorial() {
        return historial;
    }
}
