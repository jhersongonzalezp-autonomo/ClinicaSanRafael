package com.example.sanrafael.medico;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sanrafael.R;
import com.example.sanrafael.paciente.Paciente;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class AgendaMedicoActivity extends AppCompatActivity {

    private Button iniciar;
    private LinearLayout opcionConsulta, opcionHistorial;
    private RecyclerView rvConsultas;

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

        rvConsultas = findViewById(R.id.rvConsultas);
        if (rvConsultas != null) {
            rvConsultas.setLayoutManager(new LinearLayoutManager(this));
            rvConsultas.setAdapter(new ConsultaAdapter(Consulta.listaConsultas));
        }

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

        if (opcionHistorial != null) {
            opcionHistorial.setOnClickListener(v -> {
                Intent intent = new Intent(AgendaMedicoActivity.this, HistorialMedicoActivity.class);
                startActivity(intent);
            });
        }

        configurarDias();
    }

    private void configurarDias() {
        if (lunes != null) lunes.setOnClickListener(v -> seleccionarDia(lunes, labelLunes, numLunes, "LUNES 8 DE SEPTIEMBRE", "2025-09-08", "Control postoperatorio", "Atendido", "#E8F5E9", "#1B5E20"));
        if (martes != null) martes.setOnClickListener(v -> seleccionarDia(martes, labelMartes, numMartes, "MARTES 9 DE SEPTIEMBRE", "2025-09-09", "Evaluación de riesgo cardiovascular", "Atendido", "#E8F5E9", "#1B5E20"));
        if (miercoles != null) miercoles.setOnClickListener(v -> seleccionarDia(miercoles, labelMiercoles, numMiercoles, "MIÉRCOLES 10 DE SEPTIEMBRE", "2025-09-10", "Dolor en el pecho", "Por atender", "#FFEBEE", "#C62828"));
        if (jueves != null) jueves.setOnClickListener(v -> seleccionarDia(jueves, labelJueves, numJueves, "JUEVES 11 DE SEPTIEMBRE", "2025-09-11", "Control de hipertensión arterial", "Pendiente", "#FFF3E0", "#E65100"));
        if (viernes != null) viernes.setOnClickListener(v -> seleccionarDia(viernes, labelViernes, numViernes, "VIERNES 12 DE SEPTIEMBRE", "2025-09-12", "Revisión de electrocardiograma", "Pendiente", "#FFF3E0", "#E65100"));
        if (sabado != null) sabado.setOnClickListener(v -> seleccionarDia(sabado, labelSabado, numSabado, "SÁBADO 13 DE SEPTIEMBRE", "2025-09-13", "Consulta de seguimiento general", "Pendiente", "#FFF3E0", "#E65100"));
    }

    private void seleccionarDia(MaterialCardView tarjetaDia, TextView label, TextView numero, String textoFecha, String fechaISO, String textoMotivo, String textoEstado, String colorFondoBadge, String colorTextoBadge) {
        resetearDias();

        fechaSeleccionada = fechaISO;

        int verde = ContextCompat.getColor(this, R.color.hospital_green);
        int verdeClaro = ContextCompat.getColor(this, R.color.hospital_light_green);

        if (tarjetaDia != null) {
            tarjetaDia.setCardBackgroundColor(verdeClaro);
            tarjetaDia.setStrokeColor(verde);
        }
        if (label != null) label.setTextColor(verde);
        if (numero != null) numero.setTextColor(verde);

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

    public static class ConsultaAdapter extends RecyclerView.Adapter<ConsultaAdapter.ConsultaViewHolder> {

        private final List<Consulta> consultas;

        public ConsultaAdapter(List<Consulta> consultas) {
            this.consultas = consultas;
        }

        @NonNull
        @Override
        public ConsultaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consulta, parent, false);
            return new ConsultaViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ConsultaViewHolder holder, int position) {
            Consulta c = consultas.get(position);
            if (c != null) {
                if (holder.txtPacienteConsulta != null && c.getPaciente() != null) {
                    holder.txtPacienteConsulta.setText(c.getPaciente().getNombreCompleto());
                }
                if (holder.txtInfoConsulta != null && c.getPaciente() != null) {
                    holder.txtInfoConsulta.setText(c.getPaciente().getNumeroHistoria() + " · " + c.getPaciente().getEdad() + " años");
                }
                if (holder.txtMotivoConsulta != null) {
                    holder.txtMotivoConsulta.setText("Motivo: " + (c.getMotivo() != null ? c.getMotivo() : "Atención general"));
                }
            }
        }

        @Override
        public int getItemCount() {
            return consultas != null ? consultas.size() : 0;
        }

        public static class ConsultaViewHolder extends RecyclerView.ViewHolder {
            TextView txtInicialesConsulta, txtPacienteConsulta, txtInfoConsulta, txtEstadoConsulta, txtMotivoConsulta;
            Button btnIniciarConsultaItem;

            public ConsultaViewHolder(@NonNull View itemView) {
                super(itemView);
                txtInicialesConsulta = itemView.findViewById(R.id.txtInicialesConsulta);
                txtPacienteConsulta = itemView.findViewById(R.id.txtPacienteConsulta);
                txtInfoConsulta = itemView.findViewById(R.id.txtInfoConsulta);
                txtEstadoConsulta = itemView.findViewById(R.id.txtEstadoConsulta);
                txtMotivoConsulta = itemView.findViewById(R.id.txtMotivoConsulta);
                btnIniciarConsultaItem = itemView.findViewById(R.id.btnIniciarConsultaItem);
            }
        }
    }
}
