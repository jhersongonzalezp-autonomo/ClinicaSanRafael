package com.example.sanrafael.administrativo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

import com.example.sanrafael.R;
import com.example.sanrafael.medico.Consulta;
import com.example.sanrafael.medico.Medico;
import com.example.sanrafael.paciente.Paciente;

public class AgendarCitaActivity extends AppCompatActivity {

    private TextInputEditText etFecha, etMotivo;
    private Button btnConfirmarCita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_cita);

        etFecha = findViewById(R.id.etFecha);
        etMotivo = findViewById(R.id.etMotivo);
        btnConfirmarCita = findViewById(R.id.btnConfirmarCita);

        if (etFecha != null) {
            etFecha.setText("2025-09-10");
        }

        if (findViewById(R.id.toolbar) != null) {
            findViewById(R.id.toolbar).setOnClickListener(v -> finish());
        }

        btnConfirmarCita.setOnClickListener(v -> {
            String fecha = etFecha.getText() != null ? etFecha.getText().toString().trim() : "2025-09-10";
            String motivo = etMotivo.getText() != null ? etMotivo.getText().toString().trim() : "Consulta general";

            Paciente paciente = Paciente.pacienteActual;
            Medico medico = new Medico("Laura", "Gómez", "DNI", "12345678", 40, "Colombiana", 1.65, "M-001", 5000.0, "Cardióloga", "COL-98765");
            Administrativo admin = new Administrativo("María", "Rodríguez", "DNI", "11223344", 32, "Colombiana", 1.60, "A-101", 2000.0, "Recepción", "Recepcionista");

            Consulta consulta = new Consulta(fecha, motivo, medico, paciente);
            admin.agendarCita(paciente, medico, fecha);

            Toast.makeText(this, "Cita agendada exitosamente para " + paciente.getNombreCompleto(), Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
