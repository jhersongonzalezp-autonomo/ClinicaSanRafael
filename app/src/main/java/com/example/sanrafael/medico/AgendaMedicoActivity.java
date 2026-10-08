package com.example.sanrafael.medico;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.sanrafael.R;
import com.example.sanrafael.paciente.Paciente;
import com.google.android.material.card.MaterialCardView;

public class AgendaMedicoActivity extends AppCompatActivity {

    private Button iniciar;
    private LinearLayout opcionConsulta, opcionHistorial;

    private MaterialCardView lunes, martes, miercoles, jueves, viernes, sabado;
    private TextView labelLunes, numLunes, labelMartes, numMartes, labelMiercoles, numMiercoles;
    private TextView labelJueves, numJueves, labelViernes, numViernes, labelSabado, numSabado;

    private TextView fecha, paciente, info, motivo, estado;
    private MaterialCardView tarjetaEstado;

    private String fechaSeleccionada = "2025-09-10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_medico);

        iniciar = findViewById(R.id.iniciar);
        opcionConsulta = findViewById(R.id.opcionConsulta);
        opcionHistorial = findViewById(R.id.opcionHistorial);

        fecha = findViewById(R.id.fecha);
        paciente = findViewById(R.id.paciente);
        info = findViewById(R.id.info);
        motivo = findViewById(R.id.motivo);
        estado = findViewById(R.id.estado);
        tarjetaEstado = findViewById(R.id.tarjetaEstado);

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

        Paciente pacienteActual = Paciente.pacienteActual;
        if (paciente != null && pacienteActual != null) {
            paciente.setText(pacienteActual.getNombreCompleto());
            info.setText(pacienteActual.getNumeroHistoria() + " · " + pacienteActual.getEdad() + " años");
        }

        iniciar.setOnClickListener(v -> {
            Intent intent = new Intent(AgendaMedicoActivity.this, RealizarConsultaActivity.class);
            intent.putExtra("FECHA_CONSULTA", fechaSeleccionada);
            startActivity(intent);
        });

        opcionConsulta.setOnClickListener(v -> {
            Intent intent = new Intent(AgendaMedicoActivity.this, RealizarConsultaActivity.class);
            startActivity(intent);
        });

        opcionHistorial.setOnClickListener(v -> {
            Intent intent = new Intent(AgendaMedicoActivity.this, HistorialMedicoActivity.class);
            startActivity(intent);
        });

        configurarDias();
    }

    private void configurarDias() {
        lunes.setOnClickListener(v -> seleccionarDia(lunes, labelLunes, numLunes, "LUNES 8 DE SEPTIEMBRE", "2025-09-08", "Control postoperatorio", "Atendido", "#E8F5E9", "#1B5E20"));
        martes.setOnClickListener(v -> seleccionarDia(martes, labelMartes, numMartes, "MARTES 9 DE SEPTIEMBRE", "2025-09-09", "Evaluación de riesgo cardiovascular", "Atendido", "#E8F5E9", "#1B5E20"));
        miercoles.setOnClickListener(v -> seleccionarDia(miercoles, labelMiercoles, numMiercoles, "MIÉRCOLES 10 DE SEPTIEMBRE", "2025-09-10", "Dolor en el pecho", "Por atender", "#FFEBEE", "#C62828"));
        jueves.setOnClickListener(v -> seleccionarDia(jueves, labelJueves, numJueves, "JUEVES 11 DE SEPTIEMBRE", "2025-09-11", "Control de hipertensión arterial", "Pendiente", "#FFF3E0", "#E65100"));
        viernes.setOnClickListener(v -> seleccionarDia(viernes, labelViernes, numViernes, "VIERNES 12 DE SEPTIEMBRE", "2025-09-12", "Revisión de electrocardiograma", "Pendiente", "#FFF3E0", "#E65100"));
        sabado.setOnClickListener(v -> seleccionarDia(sabado, labelSabado, numSabado, "SÁBADO 13 DE SEPTIEMBRE", "2025-09-13", "Consulta de seguimiento general", "Pendiente", "#FFF3E0", "#E65100"));
    }

    private void seleccionarDia(MaterialCardView tarjetaDia, TextView label, TextView numero, String textoFecha, String fechaISO, String textoMotivo, String textoEstado, String colorFondoBadge, String colorTextoBadge) {
        resetearDias();

        fechaSeleccionada = fechaISO;

        int verde = ContextCompat.getColor(this, R.color.hospital_green);
        int verdeClaro = ContextCompat.getColor(this, R.color.hospital_light_green);

        tarjetaDia.setCardBackgroundColor(verdeClaro);
        tarjetaDia.setStrokeColor(verde);
        label.setTextColor(verde);
        numero.setTextColor(verde);

        if (fecha != null) fecha.setText(textoFecha);
        if (motivo != null) motivo.setText("Motivo: " + textoMotivo);
        if (estado != null) estado.setText(textoEstado);

        if (tarjetaEstado != null) {
            tarjetaEstado.setCardBackgroundColor(Color.parseColor(colorFondoBadge));
        }
        if (estado != null) {
            estado.setTextColor(Color.parseColor(colorTextoBadge));
        }
    }

    private void resetearDias() {
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
}
