package com.example.sanrafael.medico;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sanrafael.R;

public class AgendaMedicoActivity extends AppCompatActivity {

    private Button btnIniciarConsulta;
    private LinearLayout navConsulta, navHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_medico);

        btnIniciarConsulta = findViewById(R.id.btnIniciarConsulta);
        navConsulta = findViewById(R.id.navConsulta);
        navHistorial = findViewById(R.id.navHistorial);

        btnIniciarConsulta.setOnClickListener(v -> {
            Intent intent = new Intent(AgendaMedicoActivity.this, RealizarConsultaActivity.class);
            startActivity(intent);
        });

        navConsulta.setOnClickListener(v -> {
            Intent intent = new Intent(AgendaMedicoActivity.this, RealizarConsultaActivity.class);
            startActivity(intent);
        });

        navHistorial.setOnClickListener(v -> {
            Intent intent = new Intent(AgendaMedicoActivity.this, HistorialMedicoActivity.class);
            startActivity(intent);
        });
    }
}
