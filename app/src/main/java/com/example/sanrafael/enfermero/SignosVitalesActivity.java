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

public class SignosVitalesActivity extends AppCompatActivity {

    private ImageView btnBack;
    private Button btnGuardarSignos;
    private TextInputEditText etPresion, etFrecCardiaca, etTemperatura, etSaturacion, etFrecRespiratoria, etPeso, etObservaciones;
    private LinearLayout navTurno, navMedicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signos_vitales);

        btnBack = findViewById(R.id.btnBack);
        btnGuardarSignos = findViewById(R.id.btnGuardarSignos);
        etPresion = findViewById(R.id.etPresion);
        etFrecCardiaca = findViewById(R.id.etFrecCardiaca);
        etTemperatura = findViewById(R.id.etTemperatura);
        etSaturacion = findViewById(R.id.etSaturacion);
        etFrecRespiratoria = findViewById(R.id.etFrecRespiratoria);
        etPeso = findViewById(R.id.etPeso);
        etObservaciones = findViewById(R.id.etObservaciones);

        navTurno = findViewById(R.id.navTurno);
        navMedicacion = findViewById(R.id.navMedicacion);

        btnBack.setOnClickListener(v -> finish());

        navTurno.setOnClickListener(v -> {
            Intent intent = new Intent(SignosVitalesActivity.this, TurnoEnfermeroActivity.class);
            startActivity(intent);
            finish();
        });

        navMedicacion.setOnClickListener(v -> {
            Intent intent = new Intent(SignosVitalesActivity.this, AdministrarMedicamentoActivity.class);
            startActivity(intent);
            finish();
        });

        btnGuardarSignos.setOnClickListener(v -> {
            Paciente paciente = new Paciente("Juan", "Pérez", "CC", "106168945", "99887766D", 55, "Colombiano", 1.70, "HC-2024-001");
            Enfermero enfermero = new Enfermero("Carlos", "Ruiz", "CC", "11223344", 30, "Colombiano", 1.75, "EMP-003", 3000.0, "Turno Mañana");

            enfermero.tomarSignosVitales(paciente);

            Toast.makeText(this, "Signos vitales guardados con éxito para Juan Pérez", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
