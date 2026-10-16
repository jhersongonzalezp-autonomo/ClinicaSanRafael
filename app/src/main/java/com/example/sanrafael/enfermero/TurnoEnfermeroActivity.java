package com.example.sanrafael.enfermero;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sanrafael.R;
import com.example.sanrafael.paciente.Paciente;

import java.util.List;

public class TurnoEnfermeroActivity extends AppCompatActivity {

    private Button btnIrSignos, btnIrMedicar;
    private LinearLayout navSignos, navMedicacion;
    private RecyclerView rvPacientesTurno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turno_enfermero);

        btnIrSignos = findViewById(R.id.btnIrSignos);
        btnIrMedicar = findViewById(R.id.btnIrMedicar);
        navSignos = findViewById(R.id.navSignos);
        navMedicacion = findViewById(R.id.navMedicacion);

        rvPacientesTurno = findViewById(R.id.rvPacientesTurno);
        if (rvPacientesTurno != null) {
            rvPacientesTurno.setLayoutManager(new LinearLayoutManager(this));
            rvPacientesTurno.setAdapter(new PacienteTurnoAdapter(Paciente.listaPacientes));
        }

        if (btnIrSignos != null) {
            btnIrSignos.setOnClickListener(v -> {
                Intent intent = new Intent(TurnoEnfermeroActivity.this, SignosVitalesActivity.class);
                startActivity(intent);
            });
        }

        if (btnIrMedicar != null) {
            btnIrMedicar.setOnClickListener(v -> {
                Intent intent = new Intent(TurnoEnfermeroActivity.this, AdministrarMedicamentoActivity.class);
                startActivity(intent);
            });
        }

        if (navSignos != null) {
            navSignos.setOnClickListener(v -> {
                Intent intent = new Intent(TurnoEnfermeroActivity.this, SignosVitalesActivity.class);
                startActivity(intent);
            });
        }

        if (navMedicacion != null) {
            navMedicacion.setOnClickListener(v -> {
                Intent intent = new Intent(TurnoEnfermeroActivity.this, AdministrarMedicamentoActivity.class);
                startActivity(intent);
            });
        }
    }

    public static class PacienteTurnoAdapter extends RecyclerView.Adapter<PacienteTurnoAdapter.PacienteViewHolder> {

        private final List<Paciente> pacientes;

        public PacienteTurnoAdapter(List<Paciente> pacientes) {
            this.pacientes = pacientes;
        }

        @NonNull
        @Override
        public PacienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paciente_turno, parent, false);
            return new PacienteViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PacienteViewHolder holder, int position) {
            Paciente p = pacientes.get(position);
            if (p != null) {
                if (holder.txtNombrePacienteTurno != null) {
                    holder.txtNombrePacienteTurno.setText(p.getNombreCompleto());
                }
                if (holder.txtInfoHistoriaTurno != null) {
                    holder.txtInfoHistoriaTurno.setText(p.getNumeroHistoria() + " · " + p.getEdad() + " años");
                }
                if (holder.txtInicialesTurno != null && p.getNombre() != null && !p.getNombre().isEmpty()) {
                    holder.txtInicialesTurno.setText(p.getNombre().substring(0, 1).toUpperCase());
                }
            }
        }

        @Override
        public int getItemCount() {
            return pacientes != null ? pacientes.size() : 0;
        }

        public static class PacienteViewHolder extends RecyclerView.ViewHolder {
            TextView txtInicialesTurno, txtNombrePacienteTurno, txtInfoHistoriaTurno, txtEstadoTurno, txtDetalleConsultaTurno;
            Button btnSignosItem, btnMedicarItem;

            public PacienteViewHolder(@NonNull View itemView) {
                super(itemView);
                txtInicialesTurno = itemView.findViewById(R.id.txtInicialesTurno);
                txtNombrePacienteTurno = itemView.findViewById(R.id.txtNombrePacienteTurno);
                txtInfoHistoriaTurno = itemView.findViewById(R.id.txtInfoHistoriaTurno);
                txtEstadoTurno = itemView.findViewById(R.id.txtEstadoTurno);
                txtDetalleConsultaTurno = itemView.findViewById(R.id.txtDetalleConsultaTurno);
                btnSignosItem = itemView.findViewById(R.id.btnSignosItem);
                btnMedicarItem = itemView.findViewById(R.id.btnMedicarItem);
            }
        }
    }
}
