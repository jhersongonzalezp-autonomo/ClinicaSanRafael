package com.example.sanrafael.medico;

import java.util.ArrayList;
import java.util.List;

public class HistorialMedico {

    private List<String> diagnosticos;
    private List<String> tratamientos;

    public List<String> getDiagnosticos() {
        return diagnosticos;
    }

    public List<String> getTratamientos() {
        return tratamientos;
    }

    public HistorialMedico() {
        this.diagnosticos = new ArrayList<>();
        this.tratamientos = new ArrayList<>();
    }

    public void agregarDiagnostico(String diagnostico) {
        diagnosticos.add(diagnostico);
    }

    public void agregarTratamiento(String tratamiento) {
        tratamientos.add(tratamiento);
    }

    public void mostrar() {
        System.out.println("--- Historial Medico ---");
        System.out.println("Diagnosticos: " + diagnosticos);
        System.out.println("Tratamientos: " + tratamientos);
    }
}
