package com.example.sanrafael.administrativo;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

import com.example.sanrafael.R;
import com.example.sanrafael.paciente.Paciente;

public class FacturaActivity extends AppCompatActivity {

    private TextView tvFacturaPaciente, tvFacturaHistoria, tvFacturaDni, tvFacturaFechaConsulta, tvFacturaMedico, tvFacturaTotal;
    private MaterialButton btnEmitirFactura, btnCompartir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

        tvFacturaPaciente = findViewById(R.id.tvFacturaPaciente);
        tvFacturaHistoria = findViewById(R.id.tvFacturaHistoria);
        tvFacturaDni = findViewById(R.id.tvFacturaDni);
        tvFacturaFechaConsulta = findViewById(R.id.tvFacturaFechaConsulta);
        tvFacturaMedico = findViewById(R.id.tvFacturaMedico);
        tvFacturaTotal = findViewById(R.id.tvFacturaTotal);

        btnEmitirFactura = findViewById(R.id.btnEmitirFactura);
        btnCompartir = findViewById(R.id.btnCompartir);

        Paciente p = Paciente.pacienteActual;
        if (tvFacturaPaciente != null) tvFacturaPaciente.setText(p.getNombreCompleto());
        if (tvFacturaHistoria != null) tvFacturaHistoria.setText(p.getNumeroHistoria());
        if (tvFacturaDni != null) tvFacturaDni.setText(p.getDni());
        if (tvFacturaFechaConsulta != null) tvFacturaFechaConsulta.setText("2025-09-10");
        if (tvFacturaMedico != null) tvFacturaMedico.setText("Laura Gómez");
        if (tvFacturaTotal != null) tvFacturaTotal.setText("$150,000 COP");

        Administrativo admin = new Administrativo("María", "Rodríguez", "DNI", "11223344", 32, "Colombiana", 1.60, "A-101", 2000.0, "Recepción", "Recepcionista");

        btnEmitirFactura.setOnClickListener(v -> {
            admin.generarFactura(p);
            Toast.makeText(this, "Factura emitida correctamente para " + p.getNombreCompleto(), Toast.LENGTH_SHORT).show();
            finish();
        });

        btnCompartir.setOnClickListener(v -> {
            Toast.makeText(this, "Compartiendo factura...", Toast.LENGTH_SHORT).show();
        });
    }
}
