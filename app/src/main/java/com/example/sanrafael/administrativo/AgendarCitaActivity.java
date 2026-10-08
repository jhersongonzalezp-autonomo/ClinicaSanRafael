package com.example.sanrafael.administrativo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import com.example.sanrafael.R;
import com.example.sanrafael.administrador.FormularioActivity;
import com.example.sanrafael.medico.Consulta;
import com.example.sanrafael.medico.Medico;
import com.example.sanrafael.paciente.Paciente;

import java.util.ArrayList;
import java.util.List;

public class AgendarCitaActivity extends AppCompatActivity {

    private MaterialAutoCompleteTextView paciente, medico;
    private TextInputEditText motivo;
    private TextView resumenCita, resumenDetalle, mesAno;
    private Button confirmar;
    private ImageView volver;
    private LinearLayout opcionInicio, opcionPacientes, opcionPersonal;

    private MaterialCardView lunes, martes, miercoles, jueves, viernes, sabado;
    private TextView labelLunes, numLunes, labelMartes, numMartes, labelMiercoles, numMiercoles;
    private TextView labelJueves, numJueves, labelViernes, numViernes, labelSabado, numSabado;

    private Paciente pacienteSeleccionado;
    private String fechaSeleccionada = "2025-09-10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_cita);

        volver = findViewById(R.id.volver);
        paciente = findViewById(R.id.paciente);
        medico = findViewById(R.id.medico);
        motivo = findViewById(R.id.motivo);
        resumenCita = findViewById(R.id.resumenCita);
        resumenDetalle = findViewById(R.id.resumenDetalle);
        mesAno = findViewById(R.id.mesAno);
        confirmar = findViewById(R.id.confirmar);

        opcionInicio = findViewById(R.id.opcionInicio);
        opcionPacientes = findViewById(R.id.opcionPacientes);
        opcionPersonal = findViewById(R.id.opcionPersonal);

        lunes = findViewById(R.id.lunes);
        martes = findViewById(R.id.martes);
        miercoles = findViewById(R.id.miercoles);
        jueves = findViewById(R.id.jueves);
        viernes = findViewById(R.id.viernes);
        sabado = findViewById(R.id.sabado);

        labelLunes = findViewById(R.id.labelLunes);
        numLunes = findViewById(R.id.numLunes);
        labelMartes = findViewById(R.id.labelMartes);
        numMartes = findViewById(R.id.numMartes);
        labelMiercoles = findViewById(R.id.labelMiercoles);
        numMiercoles = findViewById(R.id.numMiercoles);
        labelJueves = findViewById(R.id.labelJueves);
        numJueves = findViewById(R.id.numJueves);
        labelViernes = findViewById(R.id.labelViernes);
        numViernes = findViewById(R.id.numViernes);
        labelSabado = findViewById(R.id.labelSabado);
        numSabado = findViewById(R.id.numSabado);

        if (volver != null) {
            volver.setOnClickListener(v -> finish());
        }

        if (opcionInicio != null) {
            opcionInicio.setOnClickListener(v -> {
                startActivity(new Intent(AgendarCitaActivity.this, AdminInicioActivity.class));
                finish();
            });
        }

        if (opcionPacientes != null) {
            opcionPacientes.setOnClickListener(v -> {
                startActivity(new Intent(AgendarCitaActivity.this, PacientesActivity.class));
                finish();
            });
        }

        if (opcionPersonal != null) {
            opcionPersonal.setOnClickListener(v -> {
                startActivity(new Intent(AgendarCitaActivity.this, FormularioActivity.class));
            });
        }

        pacienteSeleccionado = Paciente.pacienteActual;

        configurarDropdownPacientes();

        if (medico != null) {
            medico.setText("Dra. Laura Gómez — Cardiología");
        }

        if (motivo != null) {
            motivo.setText("Dolor en el pecho");
        }

        configurarChipsFechas();
        actualizarResumen();

        if (motivo != null) {
            motivo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    actualizarResumen();
                }
                @Override
                public void afterTextChanged(Editable s) {}
            });
        }

        confirmar.setOnClickListener(v -> {
            String textoMotivo = motivo.getText() != null ? motivo.getText().toString().trim() : "Consulta general";

            if (pacienteSeleccionado == null) {
                pacienteSeleccionado = Paciente.pacienteActual;
            }

            Medico medicoObj = new Medico("Laura", "Gómez", "DNI", "12345678", 40, "Colombiana", 1.65, "M-001", 5000.0, "Cardióloga", "COL-98765");
            Administrativo adminObj = new Administrativo("María", "Rodríguez", "DNI", "11223344", 32, "Colombiana", 1.60, "A-101", 2000.0, "Recepción", "Recepcionista");

            Consulta consulta = new Consulta(fechaSeleccionada, textoMotivo, medicoObj, pacienteSeleccionado);
            Consulta.listaConsultas.add(consulta);

            adminObj.agendarCita(pacienteSeleccionado, medicoObj, fechaSeleccionada);

            Toast.makeText(this, "Cita agendada exitosamente para " + pacienteSeleccionado.getNombreCompleto(), Toast.LENGTH_LONG).show();
            finish();
        });
    }

    private void configurarChipsFechas() {
        if (lunes == null) return;
        lunes.setOnClickListener(v -> seleccionarDia(lunes, labelLunes, numLunes, "2025-09-08"));
        martes.setOnClickListener(v -> seleccionarDia(martes, labelMartes, numMartes, "2025-09-09"));
        miercoles.setOnClickListener(v -> seleccionarDia(miercoles, labelMiercoles, numMiercoles, "2025-09-10"));
        jueves.setOnClickListener(v -> seleccionarDia(jueves, labelJueves, numJueves, "2025-09-11"));
        viernes.setOnClickListener(v -> seleccionarDia(viernes, labelViernes, numViernes, "2025-09-12"));
        sabado.setOnClickListener(v -> seleccionarDia(sabado, labelSabado, numSabado, "2025-09-13"));
    }

    private void seleccionarDia(MaterialCardView tarjetaDia, TextView label, TextView numero, String fechaISO) {
        resetearChips();

        fechaSeleccionada = fechaISO;

        int verde = ContextCompat.getColor(this, R.color.hospital_green);
        int blanco = ContextCompat.getColor(this, R.color.white);

        tarjetaDia.setCardBackgroundColor(verde);
        tarjetaDia.setStrokeColor(verde);
        label.setTextColor(blanco);
        numero.setTextColor(blanco);

        actualizarResumen();
    }

    private void resetearChips() {
        int blanco = ContextCompat.getColor(this, R.color.white);
        int borde = ContextCompat.getColor(this, R.color.input_stroke);
        int negro = ContextCompat.getColor(this, R.color.black);

        MaterialCardView[] diasTarjetas = {lunes, martes, miercoles, jueves, viernes, sabado};
        TextView[] diasEtiquetas = {labelLunes, labelMartes, labelMiercoles, labelJueves, labelViernes, labelSabado};
        TextView[] diasNumeros = {numLunes, numMartes, numMiercoles, numJueves, numViernes, numSabado};

        for (int i = 0; i < diasTarjetas.length; i++) {
            if (diasTarjetas[i] != null) {
                diasTarjetas[i].setCardBackgroundColor(blanco);
                diasTarjetas[i].setStrokeColor(borde);
            }
            if (diasEtiquetas[i] != null) diasEtiquetas[i].setTextColor(negro);
            if (diasNumeros[i] != null) diasNumeros[i].setTextColor(negro);
        }
    }

    private void configurarDropdownPacientes() {
        if (paciente == null) return;

        List<String> nombres = new ArrayList<>();
        for (Paciente p : Paciente.listaPacientes) {
            nombres.add(p.getNombreCompleto() + " — " + p.getNumeroHistoria());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, nombres);
        paciente.setAdapter(adapter);

        if (pacienteSeleccionado != null) {
            paciente.setText(pacienteSeleccionado.getNombreCompleto() + " — " + pacienteSeleccionado.getNumeroHistoria(), false);
        }

        paciente.setOnItemClickListener((parent, view, position, id) -> {
            if (position >= 0 && position < Paciente.listaPacientes.size()) {
                pacienteSeleccionado = Paciente.listaPacientes.get(position);
                Paciente.pacienteActual = pacienteSeleccionado;
                actualizarResumen();
            }
        });
    }

    private void actualizarResumen() {
        String nombrePaciente = pacienteSeleccionado != null ? pacienteSeleccionado.getNombreCompleto() : "Paciente";
        String textoMotivo = motivo != null && motivo.getText() != null ? motivo.getText().toString().trim() : "Consulta general";

        if (resumenCita != null) {
            resumenCita.setText(nombrePaciente + " con Dra. Laura Gómez");
        }
        if (resumenDetalle != null) {
            resumenDetalle.setText(fechaSeleccionada + " · " + textoMotivo);
        }
    }
}
