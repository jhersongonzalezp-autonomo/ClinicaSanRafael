package com.example.sanrafael.administrativo;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import com.example.sanrafael.R;
import com.example.sanrafael.administrador.FormularioActivity;
import com.example.sanrafael.paciente.Paciente;

public class PacientesActivity extends AppCompatActivity {

    private ExtendedFloatingActionButton nuevoPaciente;
    private TextView sinPacientes, subtituloPacientes;
    private TextInputEditText buscar;
    private LinearLayout listaContenedor, opcionInicio, opcionCitas, opcionPersonal;
    private ImageView volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);

        volver = findViewById(R.id.volver);
        subtituloPacientes = findViewById(R.id.subtituloPacientes);
        nuevoPaciente = findViewById(R.id.nuevoPaciente);
        sinPacientes = findViewById(R.id.sinPacientes);
        buscar = findViewById(R.id.buscar);
        listaContenedor = findViewById(R.id.listaContenedor);

        opcionInicio = findViewById(R.id.opcionInicio);
        opcionCitas = findViewById(R.id.opcionCitas);
        opcionPersonal = findViewById(R.id.opcionPersonal);

        if (volver != null) {
            volver.setOnClickListener(v -> finish());
        }

        nuevoPaciente.setOnClickListener(v -> {
            startActivity(new Intent(PacientesActivity.this, RegistrarPacienteActivity.class));
        });

        if (opcionInicio != null) {
            opcionInicio.setOnClickListener(v -> {
                startActivity(new Intent(PacientesActivity.this, AdminInicioActivity.class));
                finish();
            });
        }

        if (opcionCitas != null) {
            opcionCitas.setOnClickListener(v -> {
                startActivity(new Intent(PacientesActivity.this, AgendarCitaActivity.class));
            });
        }

        if (opcionPersonal != null) {
            opcionPersonal.setOnClickListener(v -> {
                startActivity(new Intent(PacientesActivity.this, FormularioActivity.class));
            });
        }

        if (buscar != null) {
            buscar.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    cargarListaPacientes(s.toString().trim());
                }
                @Override
                public void afterTextChanged(Editable s) {}
            });
        }

        cargarListaPacientes("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        String filtro = buscar != null && buscar.getText() != null ? buscar.getText().toString().trim() : "";
        cargarListaPacientes(filtro);
    }

    private void cargarListaPacientes(String filtro) {
        if (listaContenedor == null) return;
        listaContenedor.removeAllViews();

        int encontrados = 0;

        for (Paciente p : Paciente.listaPacientes) {
            String textoCompleto = (p.getNombreCompleto() + " " + p.getDni() + " " + p.getNumeroHistoria()).toLowerCase();
            if (!filtro.isEmpty() && !textoCompleto.contains(filtro.toLowerCase())) {
                continue;
            }

            encontrados++;
            agregarTarjetaPaciente(p);
        }

        if (subtituloPacientes != null) {
            subtituloPacientes.setText(encontrados + " registrado(s)");
        }

        if (sinPacientes != null) {
            sinPacientes.setVisibility(encontrados == 0 ? View.VISIBLE : View.GONE);
        }
    }

    private void agregarTarjetaPaciente(Paciente paciente) {
        MaterialCardView card = new MaterialCardView(this);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 0, 0, 16);
        card.setLayoutParams(cardParams);
        card.setRadius(32f);
        card.setStrokeWidth(2);

        boolean esSeleccionado = (Paciente.pacienteActual != null && Paciente.pacienteActual.getNumeroHistoria().equals(paciente.getNumeroHistoria()));

        int verdeLuz = ContextCompat.getColor(this, R.color.hospital_light_green);
        int verdeHospital = ContextCompat.getColor(this, R.color.hospital_green);
        int blanco = ContextCompat.getColor(this, R.color.white);
        int bordeStroke = ContextCompat.getColor(this, R.color.input_stroke);

        card.setCardBackgroundColor(esSeleccionado ? verdeLuz : blanco);
        card.setStrokeColor(esSeleccionado ? verdeHospital : bordeStroke);

        LinearLayout cardContent = new LinearLayout(this);
        cardContent.setOrientation(LinearLayout.VERTICAL);
        cardContent.setPadding(24, 24, 24, 24);

        // Header de la tarjeta del paciente
        LinearLayout rowHeader = new LinearLayout(this);
        rowHeader.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        rowHeader.setOrientation(LinearLayout.HORIZONTAL);
        rowHeader.setGravity(Gravity.CENTER_VERTICAL);

        // Badge con Iniciales
        MaterialCardView badge = new MaterialCardView(this);
        LinearLayout.LayoutParams badgeParams = new LinearLayout.LayoutParams((int)(40 * getResources().getDisplayMetrics().density), (int)(40 * getResources().getDisplayMetrics().density));
        badge.setLayoutParams(badgeParams);
        badge.setRadius(40f);
        badge.setStrokeWidth(0);
        badge.setCardBackgroundColor(verdeLuz);

        TextView tvIniciales = new TextView(this);
        tvIniciales.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        tvIniciales.setGravity(Gravity.CENTER);

        String iniciales = "";
        if (paciente.getNombre() != null && !paciente.getNombre().isEmpty()) iniciales += paciente.getNombre().substring(0, 1);
        if (paciente.getApellido() != null && !paciente.getApellido().isEmpty()) iniciales += paciente.getApellido().substring(0, 1);
        tvIniciales.setText(iniciales.toUpperCase());
        tvIniciales.setTextColor(verdeHospital);
        tvIniciales.setTypeface(null, Typeface.BOLD);
        tvIniciales.setTextSize(15);
        badge.addView(tvIniciales);
        rowHeader.addView(badge);

        // Textos del Paciente
        LinearLayout colTextos = new LinearLayout(this);
        LinearLayout.LayoutParams colParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        colParams.setMargins(16, 0, 0, 0);
        colTextos.setLayoutParams(colParams);
        colTextos.setOrientation(LinearLayout.VERTICAL);

        TextView tvNombre = new TextView(this);
        tvNombre.setText(paciente.getNombreCompleto());
        tvNombre.setTextColor(ContextCompat.getColor(this, R.color.black));
        tvNombre.setTypeface(null, Typeface.BOLD);
        tvNombre.setTextSize(16);
        colTextos.addView(tvNombre);

        TextView tvDetalle = new TextView(this);
        tvDetalle.setText(paciente.getNumeroHistoria() + " · " + paciente.getEdad() + " años");
        tvDetalle.setTextColor(ContextCompat.getColor(this, R.color.black));
        tvDetalle.setTextSize(13);
        colTextos.addView(tvDetalle);

        rowHeader.addView(colTextos);

        TextView arrow = new TextView(this);
        arrow.setText("›");
        arrow.setTextSize(22);
        arrow.setTextColor(ContextCompat.getColor(this, R.color.black));
        arrow.setTypeface(null, Typeface.BOLD);
        rowHeader.addView(arrow);

        cardContent.addView(rowHeader);

        // Botones de acción del paciente (Agendar Cita / Facturar) según el mockup
        LinearLayout rowBotones = new LinearLayout(this);
        LinearLayout.LayoutParams btnRowParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        btnRowParams.setMargins(0, 16, 0, 0);
        rowBotones.setLayoutParams(btnRowParams);
        rowBotones.setOrientation(LinearLayout.HORIZONTAL);

        Button btnAgendar = new Button(this, null, com.google.android.material.R.attr.materialButtonOutlinedStyle);
        LinearLayout.LayoutParams btnAgendarParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        btnAgendarParams.setMarginEnd(8);
        btnAgendar.setLayoutParams(btnAgendarParams);
        btnAgendar.setText("📅 Agendar cita");
        btnAgendar.setTextColor(ContextCompat.getColor(this, R.color.black));
        btnAgendar.setTextSize(13);
        btnAgendar.setTypeface(null, Typeface.BOLD);

        Button btnFacturar = new Button(this, null, com.google.android.material.R.attr.materialButtonOutlinedStyle);
        LinearLayout.LayoutParams btnFacturarParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        btnFacturar.setLayoutParams(btnFacturarParams);
        btnFacturar.setText("🧾 Facturar");
        btnFacturar.setTextColor(ContextCompat.getColor(this, R.color.black));
        btnFacturar.setTextSize(13);
        btnFacturar.setTypeface(null, Typeface.BOLD);

        btnAgendar.setOnClickListener(v -> {
            Paciente.pacienteActual = paciente;
            startActivity(new Intent(PacientesActivity.this, AgendarCitaActivity.class));
        });

        btnFacturar.setOnClickListener(v -> {
            Paciente.pacienteActual = paciente;
            startActivity(new Intent(PacientesActivity.this, FacturaActivity.class));
        });

        rowBotones.addView(btnAgendar);
        rowBotones.addView(btnFacturar);

        cardContent.addView(rowBotones);

        card.addView(cardContent);

        card.setOnClickListener(v -> {
            Paciente.pacienteActual = paciente;
            Toast.makeText(this, "Paciente seleccionado: " + paciente.getNombreCompleto(), Toast.LENGTH_SHORT).show();
            cargarListaPacientes(buscar != null && buscar.getText() != null ? buscar.getText().toString().trim() : "");
        });

        listaContenedor.addView(card);
    }
}
