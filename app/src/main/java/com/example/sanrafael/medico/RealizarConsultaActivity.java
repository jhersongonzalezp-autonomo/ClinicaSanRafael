package com.example.sanrafael.medico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

import com.example.sanrafael.R;
import com.example.sanrafael.paciente.Paciente;

public class RealizarConsultaActivity extends AppCompatActivity {

    private ImageView btnBack;
    private Button btnGuardarConsulta, btnFinalizarConsulta;
    private TextInputEditText etDiagnostico, etTratamiento, etIndicaciones;
    private LinearLayout navAgenda, navHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_consulta);

        btnBack = findViewById(R.id.btnBack);
        btnGuardarConsulta = findViewById(R.id.btnGuardarConsulta);
        btnFinalizarConsulta = findViewById(R.id.btnFinalizarConsulta);
        etDiagnostico = findViewById(R.id.etDiagnostico);
        etTratamiento = findViewById(R.id.etTratamiento);
        etIndicaciones = findViewById(R.id.etIndicaciones);

        navAgenda = findViewById(R.id.navAgenda);
        navHistorial = findViewById(R.id.navHistorial);

        btnBack.setOnClickListener(v -> finish());

        navAgenda.setOnClickListener(v -> {
            Intent intent = new Intent(RealizarConsultaActivity.this, AgendaMedicoActivity.class);
            startActivity(intent);
            finish();
        });

        navHistorial.setOnClickListener(v -> {
            Intent intent = new Intent(RealizarConsultaActivity.this, HistorialMedicoActivity.class);
            startActivity(intent);
            finish();
        });

        View.OnClickListener guardarListener = v -> {
            String diag = etDiagnostico.getText() != null ? etDiagnostico.getText().toString().trim() : "";
            String trat = etTratamiento.getText() != null ? etTratamiento.getText().toString().trim() : "";
            String indic = etIndicaciones.getText() != null ? etIndicaciones.getText().toString().trim() : "";

            Paciente paciente = Paciente.pacienteActual;
            Medico medico = new Medico("Laura", "Gómez", "DNI", "12345678", 40, "Colombiana", 1.65, "M-001", 5000.0, "Cardióloga", "COL-98765");

            Consulta consulta = new Consulta("2025-09-10", "Dolor en el pecho", medico, paciente);
            consulta.realizar();

            if (!diag.isEmpty()) {
                paciente.getHistorial().agregarDiagnostico(diag);
                medico.diagnosticar(paciente, diag);
            }
            if (!trat.isEmpty()) {
                String tratamientoCompleto = trat + (!indic.isEmpty() ? " (" + indic + ")" : "");
                paciente.getHistorial().agregarTratamiento(tratamientoCompleto);
                medico.prescribir(paciente, tratamientoCompleto);
            }

            paciente.getHistorial().mostrar();

            Toast.makeText(this, "Consulta guardada en el Historial Médico de " + paciente.getNombreCompleto(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RealizarConsultaActivity.this, HistorialMedicoActivity.class);
            startActivity(intent);
            finish();
        };

        btnGuardarConsulta.setOnClickListener(guardarListener);
        btnFinalizarConsulta.setOnClickListener(guardarListener);
    }
}
