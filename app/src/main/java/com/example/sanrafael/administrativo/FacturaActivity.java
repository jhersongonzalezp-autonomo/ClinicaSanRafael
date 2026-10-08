package com.example.sanrafael.administrativo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import com.example.sanrafael.R;
import com.example.sanrafael.administrador.FormularioActivity;
import com.example.sanrafael.paciente.Paciente;

import java.util.ArrayList;
import java.util.List;

public class FacturaActivity extends AppCompatActivity {

    private MaterialAutoCompleteTextView paciente;
    private TextView nombrePaciente, numeroHistoria, dniPaciente, fechaConsulta, nombreMedico, total;
    private MaterialButton emitir, compartir;
    private ImageView volver;
    private LinearLayout opcionInicio, opcionPacientes, opcionCitas, opcionPersonal;

    private Paciente pacienteSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

        volver = findViewById(R.id.volver);
        paciente = findViewById(R.id.paciente);

        nombrePaciente = findViewById(R.id.nombrePaciente);
        numeroHistoria = findViewById(R.id.numeroHistoria);
        dniPaciente = findViewById(R.id.dniPaciente);
        fechaConsulta = findViewById(R.id.fechaConsulta);
        nombreMedico = findViewById(R.id.nombreMedico);
        total = findViewById(R.id.total);

        emitir = findViewById(R.id.emitir);
        compartir = findViewById(R.id.compartir);

        opcionInicio = findViewById(R.id.opcionInicio);
        opcionPacientes = findViewById(R.id.opcionPacientes);
        opcionCitas = findViewById(R.id.opcionCitas);
        opcionPersonal = findViewById(R.id.opcionPersonal);

        if (volver != null) {
            volver.setOnClickListener(v -> finish());
        }

        if (opcionInicio != null) {
            opcionInicio.setOnClickListener(v -> {
                startActivity(new Intent(FacturaActivity.this, AdminInicioActivity.class));
                finish();
            });
        }

        if (opcionPacientes != null) {
            opcionPacientes.setOnClickListener(v -> {
                startActivity(new Intent(FacturaActivity.this, PacientesActivity.class));
                finish();
            });
        }

        if (opcionCitas != null) {
            opcionCitas.setOnClickListener(v -> {
                startActivity(new Intent(FacturaActivity.this, AgendarCitaActivity.class));
                finish();
            });
        }

        if (opcionPersonal != null) {
            opcionPersonal.setOnClickListener(v -> {
                startActivity(new Intent(FacturaActivity.this, FormularioActivity.class));
            });
        }

        pacienteSeleccionado = Paciente.pacienteActual;

        configurarDropdownPacientes();
        actualizarDatosFactura();

        Administrativo adminObj = new Administrativo("María", "Rodríguez", "DNI", "11223344", 32, "Colombiana", 1.60, "A-101", 2000.0, "Recepción", "Recepcionista");

        emitir.setOnClickListener(v -> {
            if (pacienteSeleccionado == null) pacienteSeleccionado = Paciente.pacienteActual;
            adminObj.generarFactura(pacienteSeleccionado);
            Toast.makeText(this, "Factura emitida correctamente para " + pacienteSeleccionado.getNombreCompleto(), Toast.LENGTH_SHORT).show();
            finish();
        });

        compartir.setOnClickListener(v -> {
            if (pacienteSeleccionado == null) pacienteSeleccionado = Paciente.pacienteActual;
            Toast.makeText(this, "Compartiendo factura de " + pacienteSeleccionado.getNombreCompleto() + "...", Toast.LENGTH_SHORT).show();
        });
    }

    private void configurarDropdownPacientes() {
        if (paciente == null) return;

        List<String> nombres = new ArrayList<>();
        for (Paciente p : Paciente.listaPacientes) {
            nombres.add(p.getNombreCompleto() + " — " + p.getNumeroHistoria());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, nombres);
        paciente.setAdapter(adapter);

        if (pacienteSeleccionado != null) {
            paciente.setText(pacienteSeleccionado.getNombreCompleto() + " — " + pacienteSeleccionado.getNumeroHistoria(), false);
        }

        paciente.setOnItemClickListener((parent, view, position, id) -> {
            if (position >= 0 && position < Paciente.listaPacientes.size()) {
                pacienteSeleccionado = Paciente.listaPacientes.get(position);
                Paciente.pacienteActual = pacienteSeleccionado;
                actualizarDatosFactura();
            }
        });
    }

    private void actualizarDatosFactura() {
        if (pacienteSeleccionado == null) pacienteSeleccionado = Paciente.pacienteActual;
        if (pacienteSeleccionado == null) return;

        if (nombrePaciente != null) nombrePaciente.setText(pacienteSeleccionado.getNombreCompleto());
        if (numeroHistoria != null) numeroHistoria.setText(pacienteSeleccionado.getNumeroHistoria());
        if (dniPaciente != null) dniPaciente.setText(pacienteSeleccionado.getDni());
        if (fechaConsulta != null) fechaConsulta.setText("2025-09-10");
        if (nombreMedico != null) nombreMedico.setText("Dra. Laura Gómez");
        if (total != null) total.setText("$150,000 COP");
    }
}
