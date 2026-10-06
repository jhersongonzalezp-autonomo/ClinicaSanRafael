package com.example.sanrafael.administrativo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;

import com.example.sanrafael.R;

public class AdminInicioActivity extends AppCompatActivity {

    private MaterialCardView cardRegistrarPaciente, cardAgendarCita, cardVerPacientes, cardGenerarFactura;
    private TextView tvTotalPacientes, tvTotalConsultas, tvTotalEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_inicio);

        cardRegistrarPaciente = findViewById(R.id.cardRegistrarPaciente);
        cardAgendarCita = findViewById(R.id.cardAgendarCita);
        cardVerPacientes = findViewById(R.id.cardVerPacientes);
        cardGenerarFactura = findViewById(R.id.cardGenerarFactura);

        tvTotalPacientes = findViewById(R.id.tvTotalPacientes);
        tvTotalConsultas = findViewById(R.id.tvTotalConsultas);
        tvTotalEmpleados = findViewById(R.id.tvTotalEmpleados);

        if (tvTotalPacientes != null) tvTotalPacientes.setText("1");
        if (tvTotalConsultas != null) tvTotalConsultas.setText("1");
        if (tvTotalEmpleados != null) tvTotalEmpleados.setText("3");

        cardRegistrarPaciente.setOnClickListener(v -> {
            startActivity(new Intent(AdminInicioActivity.this, RegistrarPacienteActivity.class));
        });

        cardAgendarCita.setOnClickListener(v -> {
            startActivity(new Intent(AdminInicioActivity.this, AgendarCitaActivity.class));
        });

        cardVerPacientes.setOnClickListener(v -> {
            startActivity(new Intent(AdminInicioActivity.this, PacientesActivity.class));
        });

        cardGenerarFactura.setOnClickListener(v -> {
            startActivity(new Intent(AdminInicioActivity.this, FacturaActivity.class));
        });
    }
}
