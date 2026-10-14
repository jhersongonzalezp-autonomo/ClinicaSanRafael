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

    private TextInputEditText etIdEmpleado;
    private TextInputEditText etContrasena;
    private MaterialCardView cardRolMedico;
    private MaterialCardView cardRolEnfermero;
    private MaterialCardView cardRolAdministrativo;
    private MaterialCardView cardRolAdministrador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etIdEmpleado = findViewById(R.id.etIdEmpleado);
        etContrasena = findViewById(R.id.etContrasena);
        cardRolMedico = findViewById(R.id.cardRolMedico);
        cardRolEnfermero = findViewById(R.id.cardRolEnfermero);
        cardRolAdministrativo = findViewById(R.id.cardRolAdministrativo);
        cardRolAdministrador = findViewById(R.id.cardRolAdministrador);

        if (cardRolMedico != null) cardRolMedico.setOnClickListener(v -> intentarLogin("medico"));
        if (cardRolEnfermero != null) cardRolEnfermero.setOnClickListener(v -> intentarLogin("enfermero"));
        if (cardRolAdministrativo != null) cardRolAdministrativo.setOnClickListener(v -> intentarLogin("administrativo"));
        if (cardRolAdministrador != null) cardRolAdministrador.setOnClickListener(v -> intentarLogin("administrador"));
    }

    private void intentarLogin(String rolSeleccionado) {
        String usuario = etIdEmpleado != null && etIdEmpleado.getText() != null ? etIdEmpleado.getText().toString().trim() : "";
        String contraseña = etContrasena != null && etContrasena.getText() != null ? etContrasena.getText().toString().trim() : "";

        if (TextUtils.isEmpty(usuario)) {
            if (etIdEmpleado != null) {
                etIdEmpleado.setError("Escriba su ID de empleado para ingresar");
                etIdEmpleado.requestFocus();
            }
            return;
        }
        if (TextUtils.isEmpty(contraseña)) {
            if (etContrasena != null) {
                etContrasena.setError("Escriba su contraseña para ingresar");
                etContrasena.requestFocus();
            }
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
