package com.example.sanrafael.medico;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.sanrafael.R;
import com.example.sanrafael.paciente.Paciente;

public class HistorialMedicoActivity extends AppCompatActivity {

    private ImageView volver;
    private LinearLayout agenda, consulta, lista;
    private TextView todo, diagnosticos, tratamientos;
    private TextView paciente, info;

    private enum FiltroHistorial {TODO, DIAGNOSTICOS, TRATAMIENTOS}

    private FiltroHistorial filtroActual = FiltroHistorial.TODO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_medico);

        volver = findViewById(R.id.volver);
        agenda = findViewById(R.id.agenda);
        consulta = findViewById(R.id.consulta);
        lista = findViewById(R.id.lista);

        todo = findViewById(R.id.todo);
        diagnosticos = findViewById(R.id.diagnosticos);
        tratamientos = findViewById(R.id.tratamientos);

        paciente = findViewById(R.id.paciente);
        info = findViewById(R.id.info);

        Paciente pacienteActual = Paciente.pacienteActual;
        if (pacienteActual != null) {
            if (paciente != null)
                paciente.setText(pacienteActual.getNombreCompleto());
            if (info != null) {
                info.setText(pacienteActual.getNumeroHistoria() + " · DNI " + pacienteActual.getDni() + " · " + pacienteActual.getEdad() + " años");
            }
        }

        volver.setOnClickListener(v -> finish());

        agenda.setOnClickListener(v -> {
            Intent intent = new Intent(HistorialMedicoActivity.this, AgendaMedicoActivity.class);
            startActivity(intent);
            finish();
        });

        consulta.setOnClickListener(v -> {
            Intent intent = new Intent(HistorialMedicoActivity.this, RealizarConsultaActivity.class);
            startActivity(intent);
            finish();
        });

        todo.setOnClickListener(v -> cambiarFiltro(FiltroHistorial.TODO));
        diagnosticos.setOnClickListener(v -> cambiarFiltro(FiltroHistorial.DIAGNOSTICOS));
        tratamientos.setOnClickListener(v -> cambiarFiltro(FiltroHistorial.TRATAMIENTOS));

        cargarHistorialMedico();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarHistorialMedico();
    }

    private void cambiarFiltro(FiltroHistorial filtro) {
        this.filtroActual = filtro;

        int verde = ContextCompat.getColor(this, R.color.hospital_green);
        int verdeClaro = ContextCompat.getColor(this, R.color.hospital_light_green);
        int negro = ContextCompat.getColor(this, R.color.black);

        todo.setBackgroundColor(Color.TRANSPARENT);
        todo.setTextColor(negro);
        diagnosticos.setBackgroundColor(Color.TRANSPARENT);
        diagnosticos.setTextColor(negro);
        tratamientos.setBackgroundColor(Color.TRANSPARENT);
        tratamientos.setTextColor(negro);

        switch (filtro) {
            case TODO:
                todo.setBackgroundColor(verdeClaro);
                todo.setTextColor(verde);
                break;
            case DIAGNOSTICOS:
                diagnosticos.setBackgroundColor(verdeClaro);
                diagnosticos.setTextColor(verde);
                break;
            case TRATAMIENTOS:
                tratamientos.setBackgroundColor(verdeClaro);
                tratamientos.setTextColor(verde);
                break;
        }

        cargarHistorialMedico();
    }

    private void cargarHistorialMedico() {
        if (lista == null) return;
        lista.removeAllViews();

        HistorialMedico historial = Paciente.pacienteActual.getHistorial();
        historial.mostrar();

        if (filtroActual == FiltroHistorial.TODO || filtroActual == FiltroHistorial.DIAGNOSTICOS) {
            for (String diag : historial.getDiagnosticos()) {
                agregarItemTimeline("🩺", "DIAGNÓSTICO", diag, "Dra. Laura Gómez · Cardiología");
            }
        }

        if (filtroActual == FiltroHistorial.TODO || filtroActual == FiltroHistorial.TRATAMIENTOS) {
            for (String trat : historial.getTratamientos()) {
                agregarItemTimeline("💊", "TRATAMIENTO PRESCRITO", trat, "Dra. Laura Gómez · Cardiología");
            }
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
        row.setPadding(0, 16, 0, 16);

        TextView iconoVista = new TextView(this);
        LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(
                (int) (32 * getResources().getDisplayMetrics().density),
                (int) (32 * getResources().getDisplayMetrics().density)
        );
        iconoVista.setLayoutParams(iconParams);
        iconoVista.setText(icono);
        iconoVista.setTextSize(18);
        iconoVista.setGravity(Gravity.CENTER);
        row.addView(iconoVista);

        LinearLayout columnaTexto = new LinearLayout(this);
        LinearLayout.LayoutParams colParams = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f
        );
        colParams.setMargins(12, 0, 0, 0);
        columnaTexto.setLayoutParams(colParams);
        columnaTexto.setOrientation(LinearLayout.VERTICAL);

        TextView tipoTexto = new TextView(this);
        tipoTexto.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        tipoTexto.setText(tipo);
        tipoTexto.setTextSize(12);
        tipoTexto.setTextColor(ContextCompat.getColor(this, R.color.black));
        tipoTexto.setTypeface(null, Typeface.BOLD);
        columnaTexto.addView(tipoTexto);

        TextView contenidoTexto = new TextView(this);
        contenidoTexto.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        contenidoTexto.setText(contenido);
        contenidoTexto.setTextSize(14);
        contenidoTexto.setTextColor(ContextCompat.getColor(this, R.color.black));
        contenidoTexto.setTypeface(null, Typeface.BOLD);
        contenidoTexto.setPadding(0, 2, 0, 2);
        columnaTexto.addView(contenidoTexto);

        TextView detalleTexto = new TextView(this);
        detalleTexto.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        detalleTexto.setText(detalle);
        detalleTexto.setTextSize(12);
        detalleTexto.setTextColor(Color.parseColor("#333333"));
        columnaTexto.addView(detalleTexto);

        row.addView(columnaTexto);
        lista.addView(row);
    }
}
