package com.example.sanrafael.enfermero;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sanrafael.R;

public class TurnoEnfermeroActivity extends AppCompatActivity {

    private Button btnIrSignos, btnIrMedicar;
    private LinearLayout navSignos, navMedicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turno_enfermero);

        btnIrSignos = findViewById(R.id.btnIrSignos);
        btnIrMedicar = findViewById(R.id.btnIrMedicar);
        navSignos = findViewById(R.id.navSignos);
        navMedicacion = findViewById(R.id.navMedicacion);

        btnIrSignos.setOnClickListener(v -> {
            Intent intent = new Intent(TurnoEnfermeroActivity.this, SignosVitalesActivity.class);
            startActivity(intent);
        });

        btnIrMedicar.setOnClickListener(v -> {
            Intent intent = new Intent(TurnoEnfermeroActivity.this, AdministrarMedicamentoActivity.class);
            startActivity(intent);
        });

        navSignos.setOnClickListener(v -> {
            Intent intent = new Intent(TurnoEnfermeroActivity.this, SignosVitalesActivity.class);
            startActivity(intent);
        });

        navMedicacion.setOnClickListener(v -> {
            Intent intent = new Intent(TurnoEnfermeroActivity.this, AdministrarMedicamentoActivity.class);
            startActivity(intent);
        });
    }
}
