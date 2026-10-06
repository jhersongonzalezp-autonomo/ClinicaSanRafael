package com.example.sanrafael.medico;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sanrafael.R;
import com.example.sanrafael.paciente.Paciente;

public class HistorialMedicoActivity extends AppCompatActivity {

    private ImageView btnBack;
    private LinearLayout navAgenda, navConsulta, layoutTimelineItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_medico);

        btnBack = findViewById(R.id.btnBack);
        navAgenda = findViewById(R.id.navAgenda);
        navConsulta = findViewById(R.id.navConsulta);
        layoutTimelineItems = findViewById(R.id.layoutTimelineItems);

        btnBack.setOnClickListener(v -> finish());

        navAgenda.setOnClickListener(v -> {
            Intent intent = new Intent(HistorialMedicoActivity.this, AgendaMedicoActivity.class);
            startActivity(intent);
            finish();
        });

        navConsulta.setOnClickListener(v -> {
            Intent intent = new Intent(HistorialMedicoActivity.this, RealizarConsultaActivity.class);
            startActivity(intent);
            finish();
        });

        cargarHistorialMedico();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarHistorialMedico();
    }

    private void cargarHistorialMedico() {
        if (layoutTimelineItems == null) return;
        layoutTimelineItems.removeAllViews();

        HistorialMedico historial = Paciente.pacienteActual.getHistorial();
        historial.mostrar();

        for (String diag : historial.getDiagnosticos()) {
            agregarItemTimeline("🩺", "DIAGNÓSTICO", diag, "Dra. Laura Gómez · 2025-09-10");
        }

        for (String trat : historial.getTratamientos()) {
            agregarItemTimeline("💊", "TRATAMIENTO PRESCRITO", trat, "Dra. Laura Gómez · 2025-09-10");
        }
    }

    private void agregarItemTimeline(String icono, String tipo, String contenido, String detalle) {
        LinearLayout row = new LinearLayout(this);
        row.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(0, 12, 0, 12);

        TextView tvIcon = new TextView(this);
        LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(
                (int)(28 * getResources().getDisplayMetrics().density),
                (int)(28 * getResources().getDisplayMetrics().density)
        );
        tvIcon.setLayoutParams(iconParams);
        tvIcon.setText(icono);
        tvIcon.setGravity(Gravity.CENTER);
        row.addView(tvIcon);

        LinearLayout textCol = new LinearLayout(this);
        LinearLayout.LayoutParams colParams = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f
        );
        colParams.setMargins(8, 0, 0, 0);
        textCol.setLayoutParams(colParams);
        textCol.setOrientation(LinearLayout.VERTICAL);

        TextView tvTipo = new TextView(this);
        tvTipo.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        tvTipo.setText(tipo);
        tvTipo.setTextSize(11);
        tvTipo.setTextColor(getResources().getColor(R.color.text_secondary));
        tvTipo.setTypeface(null, android.graphics.Typeface.BOLD);
        textCol.addView(tvTipo);

        TextView tvContenido = new TextView(this);
        tvContenido.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        tvContenido.setText(contenido);
        tvContenido.setTextSize(13);
        tvContenido.setTextColor(getResources().getColor(R.color.text_primary));
        textCol.addView(tvContenido);

        TextView tvDetalle = new TextView(this);
        tvDetalle.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        tvDetalle.setText(detalle);
        tvDetalle.setTextSize(11);
        tvDetalle.setTextColor(getResources().getColor(R.color.text_secondary));
        textCol.addView(tvDetalle);

        row.addView(textCol);
        layoutTimelineItems.addView(row);
    }
}
