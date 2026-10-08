package com.example.sanrafael.medico;

import com.example.sanrafael.paciente.Paciente;

import java.util.ArrayList;
import java.util.List;

public class Consulta {

    private String fecha;
    private String motivo;
    private Medico medico;
    private Paciente paciente;

    public static List<Consulta> listaConsultas = new ArrayList<>();

    static {
        Medico med = new Medico("Laura", "Gómez", "DNI", "12345678", 40, "Colombiana", 1.65, "M-001", 5000.0, "Cardióloga", "COL-98765");
        Consulta inicial = new Consulta("2025-09-10", "Dolor en el pecho", med, Paciente.pacienteActual);
        listaConsultas.add(inicial);
    }

    public Consulta(String fecha, String motivo, Medico medico, Paciente paciente) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.medico = medico;
        this.paciente = paciente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void realizar() {
        System.out.println("Realizando consulta el " + fecha + " por el motivo: " + motivo);
        if (medico != null && paciente != null) {
            System.out.println("Atendido por: Dr. " + medico.getApellido() + " | Paciente: " + paciente.getNombreCompleto());
        }
    }
}
