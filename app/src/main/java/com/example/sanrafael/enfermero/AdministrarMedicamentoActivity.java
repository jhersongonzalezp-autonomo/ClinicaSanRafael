package com.example.sanrafael.enfermero;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

import com.example.sanrafael.R;
import com.example.sanrafael.paciente.Paciente;

public class AdministrarMedicamentoActivity extends AppCompatActivity {

    private ImageView btnBack;
    private Button btnRegistrarAdmin;
    private TextInputEditText etMedicamento, etDosis;
    private LinearLayout navTurno, navSignos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_medicamento);

        btnBack = findViewById(R.id.btnBack);
        btnRegistrarAdmin = findViewById(R.id.btnRegistrarAdmin);
        etMedicamento = findViewById(R.id.etMedicamento);
        etDosis = findViewById(R.id.etDosis);

        navTurno = findViewById(R.id.navTurno);
        navSignos = findViewById(R.id.navSignos);

        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        if (navTurno != null) {
            navTurno.setOnClickListener(v -> {
                Intent intent = new Intent(AdministrarMedicamentoActivity.this, TurnoEnfermeroActivity.class);
                startActivity(intent);
                finish();
            });
        }

        if (navSignos != null) {
            navSignos.setOnClickListener(v -> {
                Intent intent = new Intent(AdministrarMedicamentoActivity.this, SignosVitalesActivity.class);
                startActivity(intent);
                finish();
            });
        }

        btnRegistrarAdmin.setOnClickListener(v -> {
            String medicamentoNombre = etMedicamento.getText() != null ? etMedicamento.getText().toString().trim() : "";
            String dosisStr = etDosis.getText() != null ? etDosis.getText().toString().trim() : "";

            if (medicamentoNombre.isEmpty()) {
                etMedicamento.setError("Ingrese el nombre del medicamento");
                return;
            }

            Paciente pacienteTarget = Paciente.pacienteActual != null ? Paciente.pacienteActual : (!Paciente.listaPacientes.isEmpty() ? Paciente.listaPacientes.get(0) : null);

            if (pacienteTarget == null) {
                Toast.makeText(this, "No hay un paciente seleccionado.", Toast.LENGTH_SHORT).show();
                return;
            }

            Enfermero enfermero = new Enfermero("Carlos", "Ruiz", "CC", "11223344", 30, "Colombiano", 1.75, "EMP-003", 3000.0, "Turno Mañana");

            String detalleMed = medicamentoNombre + (!dosisStr.isEmpty() ? " (" + dosisStr + " mg)" : "");

            enfermero.administrarMedicamento(pacienteTarget, detalleMed);
            pacienteTarget.getHistorial().agregarTratamiento("Medicamento administrado: " + detalleMed);

            Toast.makeText(this, "Administración registrada y agregada al historial de " + pacienteTarget.getNombreCompleto(), Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
