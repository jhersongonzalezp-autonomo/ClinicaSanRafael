package com.example.sanrafael;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

import com.example.sanrafael.administrador.MainActivity;
import com.example.sanrafael.administrativo.AdminInicioActivity;
import com.example.sanrafael.enfermero.TurnoEnfermeroActivity;
import com.example.sanrafael.medico.AgendaMedicoActivity;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText nombreUsuario;
    private TextInputEditText Contraseña;
    private MaterialCardView cardMedico;
    private MaterialCardView cardEnfermero;
    private MaterialCardView cardAdministrativo;
    private MaterialCardView cardAdministrador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nombreUsuario = findViewById(R.id.nombreUsuario);
        Contraseña = findViewById(R.id.Contraseña);
        cardMedico = findViewById(R.id.cardMedico);
        cardEnfermero = findViewById(R.id.cardEnfermero);
        cardAdministrativo = findViewById(R.id.cardAdministrativo);
        cardAdministrador = findViewById(R.id.cardAdministrador);

        cardMedico.setOnClickListener(v -> intentarLogin("medico"));
        cardEnfermero.setOnClickListener(v -> intentarLogin("enfermero"));
        cardAdministrativo.setOnClickListener(v -> intentarLogin("administrativo"));
        cardAdministrador.setOnClickListener(v -> intentarLogin("administrador"));
    }

    private void intentarLogin(String rolSeleccionado) {
        String usuario = nombreUsuario.getText() != null ? nombreUsuario.getText().toString().trim() : "";
        String contraseña = Contraseña.getText() != null ? Contraseña.getText().toString().trim() : "";

        if (TextUtils.isEmpty(usuario)) {
            nombreUsuario.setError("Escriba su ID de empleado para ingresar");
            nombreUsuario.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(contraseña)) {
            Contraseña.setError("Escriba su contraseña para ingresar");
            Contraseña.requestFocus();
            return;
        }

        boolean ingresoValido = false;

        switch (rolSeleccionado) {
            case "medico":
                ingresoValido = (usuario.equalsIgnoreCase("medico") && contraseña.equals("medico123"))
                        || (usuario.equalsIgnoreCase("admin") && contraseña.equals("admin123"))
                        || usuario.length() >= 2;
                break;
            case "enfermero":
                ingresoValido = (usuario.equalsIgnoreCase("enfermero") && contraseña.equals("enfermero123"))
                        || (usuario.equalsIgnoreCase("admin") && contraseña.equals("admin123"))
                        || usuario.length() >= 2;
                break;
            case "administrativo":
                ingresoValido = (usuario.equalsIgnoreCase("administrativo") && contraseña.equals("admin123"))
                        || (usuario.equalsIgnoreCase("admin") && contraseña.equals("admin123"))
                        || usuario.length() >= 2;
                break;
            case "administrador":
                ingresoValido = (usuario.equalsIgnoreCase("administrador") && contraseña.equals("administrador123"))
                        || (usuario.equalsIgnoreCase("admin") && contraseña.equals("admin123"))
                        || usuario.length() >= 2;
                break;
        }

        if (ingresoValido) {
            Toast.makeText(LoginActivity.this, "¡Ingreso exitoso como " + rolSeleccionado + "!", Toast.LENGTH_SHORT).show();
            Intent intent;
            switch (rolSeleccionado) {
                case "medico":
                    intent = new Intent(LoginActivity.this, AgendaMedicoActivity.class);
                    break;
                case "enfermero":
                    intent = new Intent(LoginActivity.this, TurnoEnfermeroActivity.class);
                    break;
                case "administrativo":
                    intent = new Intent(LoginActivity.this, AdminInicioActivity.class);
                    break;
                case "administrador":
                default:
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    break;
            }
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "Credenciales incorrectas para el rol seleccionado", Toast.LENGTH_SHORT).show();
        }
    }
}
