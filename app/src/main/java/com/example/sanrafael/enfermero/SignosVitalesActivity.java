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

        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        if (navTurno != null) {
            navTurno.setOnClickListener(v -> {
                Intent intent = new Intent(SignosVitalesActivity.this, TurnoEnfermeroActivity.class);
                startActivity(intent);
                finish();
            });
        }

        if (navMedicacion != null) {
            navMedicacion.setOnClickListener(v -> {
                Intent intent = new Intent(SignosVitalesActivity.this, AdministrarMedicamentoActivity.class);
                startActivity(intent);
                finish();
            });
        }

        btnGuardarSignos.setOnClickListener(v -> {
            Paciente pacienteTarget = Paciente.pacienteActual != null ? Paciente.pacienteActual : (!Paciente.listaPacientes.isEmpty() ? Paciente.listaPacientes.get(0) : null);
            
            if (pacienteTarget == null) {
                Toast.makeText(this, "No hay un paciente seleccionado.", Toast.LENGTH_SHORT).show();
                return;
            }

            Enfermero enfermero = new Enfermero("Carlos", "Ruiz", "CC", "11223344", 30, "Colombiano", 1.75, "EMP-003", 3000.0, "Turno Mañana");
            enfermero.tomarSignosVitales(pacienteTarget);

            String presion = etPresion.getText() != null ? etPresion.getText().toString().trim() : "";
            String temp = etTemperatura.getText() != null ? etTemperatura.getText().toString().trim() : "";
            String registroSignos = "Signos vitales tomados" + (!presion.isEmpty() ? " - PA: " + presion : "") + (!temp.isEmpty() ? " - Temp: " + temp + "°C" : "");

            pacienteTarget.getHistorial().agregarTratamiento(registroSignos);

            Toast.makeText(this, "Signos vitales guardados con éxito para " + pacienteTarget.getNombreCompleto(), Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
