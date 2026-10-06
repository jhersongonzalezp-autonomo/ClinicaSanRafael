package com.example.sanrafael.administrativo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import com.example.sanrafael.R;

public class PacientesActivity extends AppCompatActivity {

    private ExtendedFloatingActionButton fabRegistrarPaciente;
    private TextView tvSinPacientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);

        fabRegistrarPaciente = findViewById(R.id.fabRegistrarPaciente);
        tvSinPacientes = findViewById(R.id.tvSinPacientes);

        if (tvSinPacientes != null) {
            tvSinPacientes.setVisibility(android.view.View.GONE);
        }

        fabRegistrarPaciente.setOnClickListener(v -> {
            startActivity(new Intent(PacientesActivity.this, RegistrarPacienteActivity.class));
        });
    }
}
