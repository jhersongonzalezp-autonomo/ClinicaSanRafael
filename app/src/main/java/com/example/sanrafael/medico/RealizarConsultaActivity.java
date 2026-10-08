package com.example.sanrafael.medico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sanrafael.R;
import com.example.sanrafael.paciente.Paciente;
import com.google.android.material.textfield.TextInputEditText;

public class RealizarConsultaActivity extends AppCompatActivity {

    private ImageView volver;
    private Button guardar, finalizar;
    private TextInputEditText diagnostico, tratamiento, indicaciones;
    private LinearLayout agenda, historial;
    private TextView subtitulo, paciente, info;
    private TextView pasoDiagnostico, pasoTratamiento, pasoCierre;

    private String fechaConsulta = "2025-09-10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_consulta);

        if (getIntent() != null && getIntent().hasExtra("FECHA_CONSULTA")) {
            fechaConsulta = getIntent().getStringExtra("FECHA_CONSULTA");
        }

        volver = findViewById(R.id.volver);
        guardar = findViewById(R.id.guardar);
        finalizar = findViewById(R.id.finalizar);

        diagnostico = findViewById(R.id.diagnostico);
        tratamiento = findViewById(R.id.tratamiento);
        indicaciones = findViewById(R.id.indicaciones);

        subtitulo = findViewById(R.id.subtitulo);
        paciente = findViewById(R.id.paciente);
        info = findViewById(R.id.info);

        pasoDiagnostico = findViewById(R.id.pasoDiagnostico);
        pasoTratamiento = findViewById(R.id.pasoTratamiento);
        pasoCierre = findViewById(R.id.pasoCierre);

        agenda = findViewById(R.id.agenda);
        historial = findViewById(R.id.historial);

        if (subtitulo != null) {
            subtitulo.setText(fechaConsulta + " · Dolor en el pecho");
        }

        Paciente pacienteActual = Paciente.pacienteActual;
        if (pacienteActual != null) {
            if (paciente != null)
                paciente.setText(pacienteActual.getNombreCompleto());
            if (info != null) {
                info.setText(pacienteActual.getNumeroHistoria() + " · DNI " + pacienteActual.getDni() + " · " + pacienteActual.getEdad() + " años");
            }
        }

        volver.setOnClickListener(v -> finish());

        agenda.setOnClickListener(v -> {
            Intent intent = new Intent(RealizarConsultaActivity.this, AgendaMedicoActivity.class);
            startActivity(intent);
            finish();
        });

        historial.setOnClickListener(v -> {
            Intent intent = new Intent(RealizarConsultaActivity.this, HistorialMedicoActivity.class);
            startActivity(intent);
            finish();
        });

        if (pasoDiagnostico != null) pasoDiagnostico.setOnClickListener(v -> diagnostico.requestFocus());
        if (pasoTratamiento != null) pasoTratamiento.setOnClickListener(v -> tratamiento.requestFocus());
        if (pasoCierre != null) pasoCierre.setOnClickListener(v -> finalizar.requestFocus());

        View.OnClickListener guardarListener = v -> guardarConsultaYFinalizar();

        guardar.setOnClickListener(guardarListener);
        finalizar.setOnClickListener(guardarListener);
    }

    private void guardarConsultaYFinalizar() {
        String diagTexto = diagnostico.getText() != null ? diagnostico.getText().toString().trim() : "";
        String tratTexto = tratamiento.getText() != null ? tratamiento.getText().toString().trim() : "";
        String indicTexto = indicaciones.getText() != null ? indicaciones.getText().toString().trim() : "";

        Paciente pacienteActual = Paciente.pacienteActual;
        Medico medico = new Medico("Laura", "Gómez", "DNI", "12345678", 40, "Colombiana", 1.65, "M-001", 5000.0, "Cardióloga", "COL-98765");

        Consulta consulta = new Consulta(fechaConsulta, "Dolor en el pecho", medico, pacienteActual);
        consulta.realizar();

        if (!diagTexto.isEmpty()) {
            medico.diagnosticar(pacienteActual, diagTexto);
        }
        if (!tratTexto.isEmpty()) {
            String tratamientoCompleto = tratTexto + (!indicTexto.isEmpty() ? " (" + indicTexto + ")" : "");
            medico.prescribir(pacienteActual, tratamientoCompleto);
        }

        pacienteActual.getHistorial().mostrar();

        Toast.makeText(this, "Consulta guardada exitosamente en el Historial Médico", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(RealizarConsultaActivity.this, HistorialMedicoActivity.class);
        startActivity(intent);
        finish();
    }
}
