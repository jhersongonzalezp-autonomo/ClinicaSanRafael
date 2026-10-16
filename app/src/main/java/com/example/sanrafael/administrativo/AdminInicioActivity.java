package com.example.sanrafael.administrativo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import com.example.sanrafael.R;
import com.example.sanrafael.administrador.FormularioActivity;
import com.example.sanrafael.medico.Consulta;
import com.example.sanrafael.paciente.Paciente;

import java.util.List;

public class AdminInicioActivity extends AppCompatActivity {

    private MaterialCardView registrarPaciente, agendarCita, verPacientes, generarFactura, tarjetaProximaCita;
    private TextView totalPacientes, totalConsultas, totalEmpleados;
    private LinearLayout opcionPacientes, opcionCitas, opcionPersonal;
    private ImageView salir;
    private RecyclerView rvProximasCitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_inicio);

        registrarPaciente = findViewById(R.id.registrarPaciente);
        agendarCita = findViewById(R.id.agendarCita);
        verPacientes = findViewById(R.id.verPacientes);
        generarFactura = findViewById(R.id.generarFactura);
        tarjetaProximaCita = findViewById(R.id.tarjetaProximaCita);

        totalPacientes = findViewById(R.id.totalPacientes);
        totalConsultas = findViewById(R.id.totalConsultas);
        totalEmpleados = findViewById(R.id.totalEmpleados);

        opcionPacientes = findViewById(R.id.opcionPacientes);
        opcionCitas = findViewById(R.id.opcionCitas);
        opcionPersonal = findViewById(R.id.opcionPersonal);
        salir = findViewById(R.id.salir);

        rvProximasCitas = findViewById(R.id.rvProximasCitas);
        if (rvProximasCitas != null) {
            rvProximasCitas.setLayoutManager(new LinearLayoutManager(this));
            rvProximasCitas.setAdapter(new CitaAdapter(Consulta.listaConsultas));
        }

        actualizarResumen();

        if (salir != null) {
            salir.setOnClickListener(v -> finish());
        }

        if (registrarPaciente != null) {
            registrarPaciente.setOnClickListener(v -> {
                startActivity(new Intent(AdminInicioActivity.this, RegistrarPacienteActivity.class));
            });
        }

        if (agendarCita != null) {
            agendarCita.setOnClickListener(v -> {
                startActivity(new Intent(AdminInicioActivity.this, AgendarCitaActivity.class));
            });
        }

        if (verPacientes != null) {
            verPacientes.setOnClickListener(v -> {
                startActivity(new Intent(AdminInicioActivity.this, PacientesActivity.class));
            });
        }

        if (generarFactura != null) {
            generarFactura.setOnClickListener(v -> {
                startActivity(new Intent(AdminInicioActivity.this, FacturaActivity.class));
            });
        }

        if (tarjetaProximaCita != null) {
            tarjetaProximaCita.setOnClickListener(v -> {
                startActivity(new Intent(AdminInicioActivity.this, AgendarCitaActivity.class));
            });
        }

        if (opcionPacientes != null) {
            opcionPacientes.setOnClickListener(v -> {
                startActivity(new Intent(AdminInicioActivity.this, PacientesActivity.class));
            });
        }

        if (opcionCitas != null) {
            opcionCitas.setOnClickListener(v -> {
                startActivity(new Intent(AdminInicioActivity.this, AgendarCitaActivity.class));
            });
        }

        if (opcionPersonal != null) {
            opcionPersonal.setOnClickListener(v -> {
                startActivity(new Intent(AdminInicioActivity.this, FormularioActivity.class));
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizarResumen();
        if (rvProximasCitas != null && rvProximasCitas.getAdapter() != null) {
            rvProximasCitas.getAdapter().notifyDataSetChanged();
        }
    }

    private void actualizarResumen() {
        if (totalPacientes != null) {
            totalPacientes.setText(String.valueOf(Paciente.listaPacientes.size()));
        }
        if (totalConsultas != null) {
            totalConsultas.setText(String.valueOf(Consulta.listaConsultas.size()));
        }
        if (totalEmpleados != null) {
            int numEmpleados = FormularioActivity.listaMedicos.size()
                    + FormularioActivity.listaEnfermeros.size()
                    + FormularioActivity.listaAdministrativos.size()
                    + 3;
            totalEmpleados.setText(String.valueOf(numEmpleados));
        }
    }

    public static class CitaAdapter extends RecyclerView.Adapter<CitaAdapter.CitaViewHolder> {

        private final List<Consulta> consultas;

        public CitaAdapter(List<Consulta> consultas) {
            this.consultas = consultas;
        }

        @NonNull
        @Override
        public CitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cita, parent, false);
            return new CitaViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CitaViewHolder holder, int position) {
            Consulta c = consultas.get(position);
            if (c != null) {
                if (holder.txtTituloCita != null && c.getPaciente() != null) {
                    holder.txtTituloCita.setText(c.getPaciente().getNombreCompleto() + " ➔ " + (c.getMedico() != null ? c.getMedico().getNombreCompleto() : "Médico"));
                }
                if (holder.txtDetalleCita != null) {
                    holder.txtDetalleCita.setText(c.getMotivo() != null ? c.getMotivo() : "Consulta general");
                }
            }
        }

        @Override
        public int getItemCount() {
            return consultas != null ? consultas.size() : 0;
        }

        public static class CitaViewHolder extends RecyclerView.ViewHolder {
            TextView txtMesCita, txtDiaCita, txtTituloCita, txtDetalleCita;

            public CitaViewHolder(@NonNull View itemView) {
                super(itemView);
                txtMesCita = itemView.findViewById(R.id.txtMesCita);
                txtDiaCita = itemView.findViewById(R.id.txtDiaCita);
                txtTituloCita = itemView.findViewById(R.id.txtTituloCita);
                txtDetalleCita = itemView.findViewById(R.id.txtDetalleCita);
            }
        }
    }
}
