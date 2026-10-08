package com.example.sanrafael.administrativo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

import com.example.sanrafael.R;
import com.example.sanrafael.paciente.Paciente;

public class RegistrarPacienteActivity extends AppCompatActivity {

    private TextInputEditText nombre, apellido, dni, edad, historia;
    private Button registrar, cancelar;
    private MaterialToolbar encabezado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_paciente);

        encabezado = findViewById(R.id.encabezado);
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        dni = findViewById(R.id.dni);
        edad = findViewById(R.id.edad);
        historia = findViewById(R.id.historia);

        registrar = findViewById(R.id.registrar);
        cancelar = findViewById(R.id.cancelar);

        if (encabezado != null) {
            encabezado.setNavigationOnClickListener(v -> finish());
        }

        cancelar.setOnClickListener(v -> finish());

        registrar.setOnClickListener(v -> {
            String textoNombre = nombre.getText() != null ? nombre.getText().toString().trim() : "";
            String textoApellido = apellido.getText() != null ? apellido.getText().toString().trim() : "";
            String textoDni = dni.getText() != null ? dni.getText().toString().trim() : "";
            String textoEdad = edad.getText() != null ? edad.getText().toString().trim() : "";
            String textoHistoria = historia.getText() != null ? historia.getText().toString().trim() : "";

            if (textoNombre.isEmpty() || textoApellido.isEmpty() || textoDni.isEmpty() || textoEdad.isEmpty() || textoHistoria.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int valorEdad = Integer.parseInt(textoEdad);
                Paciente nuevoPaciente = new Paciente(textoNombre, textoApellido, "CC", textoDni, textoDni, valorEdad, "Colombiano", 1.70, textoHistoria);
                
                Paciente.listaPacientes.add(nuevoPaciente);
                Paciente.pacienteActual = nuevoPaciente;

                Toast.makeText(this, "Paciente " + nuevoPaciente.getNombreCompleto() + " registrado con éxito.", Toast.LENGTH_LONG).show();
                finish();
            } catch (NumberFormatException e) {
                edad.setError("Edad inválida");
            }
        });
    }
}
