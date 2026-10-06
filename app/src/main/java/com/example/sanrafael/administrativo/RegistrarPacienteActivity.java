package com.example.sanrafael.administrativo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

import com.example.sanrafael.R;
import com.example.sanrafael.paciente.Paciente;

public class RegistrarPacienteActivity extends AppCompatActivity {

    private TextInputEditText etNombre, etApellido, etDni, etEdad, etNumeroHistoria;
    private Button btnRegistrar, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_paciente);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etDni = findViewById(R.id.etDni);
        etEdad = findViewById(R.id.etEdad);
        etNumeroHistoria = findViewById(R.id.etNumeroHistoria);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnCancelar.setOnClickListener(v -> finish());

        btnRegistrar.setOnClickListener(v -> {
            String nombre = etNombre.getText() != null ? etNombre.getText().toString().trim() : "";
            String apellido = etApellido.getText() != null ? etApellido.getText().toString().trim() : "";
            String dni = etDni.getText() != null ? etDni.getText().toString().trim() : "";
            String edadStr = etEdad.getText() != null ? etEdad.getText().toString().trim() : "";
            String historia = etNumeroHistoria.getText() != null ? etNumeroHistoria.getText().toString().trim() : "";

            if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || edadStr.isEmpty() || historia.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int edad = Integer.parseInt(edadStr);
                Paciente nuevoPaciente = new Paciente(nombre, apellido, "CC", dni, dni, edad, "Colombiano", 1.70, historia);
                Paciente.pacienteActual = nuevoPaciente;

                Toast.makeText(this, "Paciente " + nuevoPaciente.getNombreCompleto() + " registrado con éxito.", Toast.LENGTH_LONG).show();
                finish();
            } catch (NumberFormatException e) {
                etEdad.setError("Edad inválida");
            }
        });
    }
}
