package com.example.sanrafael.administrativo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

import com.example.sanrafael.R;
import com.example.sanrafael.administrador.FormularioActivity;
import com.example.sanrafael.medico.Consulta;
import com.example.sanrafael.paciente.Paciente;

public class AdminInicioActivity extends AppCompatActivity {

    private MaterialCardView registrarPaciente, agendarCita, verPacientes, generarFactura, tarjetaProximaCita;
    private TextView totalPacientes, totalConsultas, totalEmpleados;
    private LinearLayout opcionPacientes, opcionCitas, opcionPersonal;
    private ImageView salir;

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

        actualizarResumen();

        if (salir != null) {
            salir.setOnClickListener(v -> finish());
        }

        registrarPaciente.setOnClickListener(v -> {
            startActivity(new Intent(AdminInicioActivity.this, RegistrarPacienteActivity.class));
        });

        agendarCita.setOnClickListener(v -> {
            startActivity(new Intent(AdminInicioActivity.this, AgendarCitaActivity.class));
        });

        verPacientes.setOnClickListener(v -> {
            startActivity(new Intent(AdminInicioActivity.this, PacientesActivity.class));
        });

        generarFactura.setOnClickListener(v -> {
            startActivity(new Intent(AdminInicioActivity.this, FacturaActivity.class));
        });

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
}
