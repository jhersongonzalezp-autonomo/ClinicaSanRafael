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

        btnBack.setOnClickListener(v -> finish());

        navTurno.setOnClickListener(v -> {
            Intent intent = new Intent(AdministrarMedicamentoActivity.this, TurnoEnfermeroActivity.class);
            startActivity(intent);
            finish();
        });

        navSignos.setOnClickListener(v -> {
            Intent intent = new Intent(AdministrarMedicamentoActivity.this, SignosVitalesActivity.class);
            startActivity(intent);
            finish();
        });

        btnRegistrarAdmin.setOnClickListener(v -> {
            String medicamentoNombre = etMedicamento.getText() != null ? etMedicamento.getText().toString().trim() : "";
            String dosisStr = etDosis.getText() != null ? etDosis.getText().toString().trim() : "";

            if (medicamentoNombre.isEmpty()) {
                etMedicamento.setError("Ingrese el nombre del medicamento");
                return;
            }

            Paciente paciente = new Paciente("Juan", "Pérez", "CC", "106168945", "99887766D", 55, "Colombiano", 1.70, "HC-2024-001");
            Enfermero enfermero = new Enfermero("Carlos", "Ruiz", "CC", "11223344", 30, "Colombiano", 1.75, "EMP-003", 3000.0, "Turno Mañana");

            String detalleMed = medicamentoNombre + (!dosisStr.isEmpty() ? " (" + dosisStr + " mg)" : "");

            enfermero.administrarMedicamento(paciente, detalleMed);

            Toast.makeText(this, "Administración registrada y agregada al historial de Juan Pérez", Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
