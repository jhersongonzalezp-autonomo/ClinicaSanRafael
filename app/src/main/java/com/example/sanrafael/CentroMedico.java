package com.example.sanrafael;

import java.util.ArrayList;
import java.util.List;

import com.example.sanrafael.medico.Consulta;
import com.example.sanrafael.paciente.Paciente;

public class CentroMedico {

    private String nombre;
    private final List<Empleado> empleados;
    private final List<Paciente> pacientes;
    private final List<Consulta> consultas;

    public CentroMedico(String nombre) {
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
        this.pacientes = new ArrayList<>();
        this.consultas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void contratar(Empleado empleado) {
        empleados.add(empleado);
        System.out.println("Nombre: "+ empleado.getNombreCompleto()+ "//  Codigo : " + empleado.getIdEmpleado());
    }

    public void registrarPaciente(Paciente paciente) {
        pacientes.add(paciente);
        System.out.println(paciente.getNombreCompleto());
    }

    public void agendarConsulta(Consulta consulta) {
        consultas.add(consulta);
        System.out.println("Consulta agendada exitosamente.");
    }

    public void mostrarPersonal() {
        System.out.println("=== Personal de " + nombre + " ===");
        for (Empleado emp : empleados) {
            emp.mostrarInformacion();
        }
    }

    public void mostrarPacientes() {
        System.out.println("=== Pacientes de " + nombre + " ===");
        for (Paciente pac : pacientes) {
            pac.mostrarInformacion();
        }
    }
}
