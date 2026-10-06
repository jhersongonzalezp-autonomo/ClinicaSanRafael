package com.example.sanrafael.administrador;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;

import com.example.sanrafael.R;
import com.example.sanrafael.medico.Medico;
import com.example.sanrafael.enfermero.Enfermero;
import com.example.sanrafael.administrativo.Administrativo;

public class FormularioActivity extends AppCompatActivity {

    public static ArrayList<Medico> listaMedicos = new ArrayList<>();
    public static ArrayList<Enfermero> listaEnfermeros = new ArrayList<>();
    public static ArrayList<Administrativo> listaAdministrativos = new ArrayList<>();

    private ImageView btnBack;
    private TextView tabMedico, tabEnfermero, tabAdministrativo;
    private TextInputEditText etNombre, etApellido, etIdentidad, etEdad;
    private TextInputEditText etIdEmpleado, etFechaIngreso, etSueldo;
    private TextInputLayout tilCampoDinamico1, tilCampoDinamico2;
    private TextInputEditText etCampoDinamico1, etCampoDinamico2;
    private Button btnContratar, btnMostrarLista;

    private String tipoSeleccionado = "Medico";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        btnBack = findViewById(R.id.btnBack);
        tabMedico = findViewById(R.id.tabMedico);
        tabEnfermero = findViewById(R.id.tabEnfermero);
        tabAdministrativo = findViewById(R.id.tabAdministrativo);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etIdentidad = findViewById(R.id.etIdentidad);
        etEdad = findViewById(R.id.etEdad);
        etIdEmpleado = findViewById(R.id.etIdEmpleado);
        etFechaIngreso = findViewById(R.id.etFechaIngreso);
        etSueldo = findViewById(R.id.etSueldo);

        tilCampoDinamico1 = findViewById(R.id.tilCampoDinamico1);
        tilCampoDinamico2 = findViewById(R.id.tilCampoDinamico2);
        etCampoDinamico1 = findViewById(R.id.etCampoDinamico1);
        etCampoDinamico2 = findViewById(R.id.etCampoDinamico2);

        btnContratar = findViewById(R.id.btnContratar);
        btnMostrarLista = findViewById(R.id.btnMostrarLista);

        btnBack.setOnClickListener(v -> finish());

        tabMedico.setOnClickListener(v -> seleccionarTipo("Medico"));
        tabEnfermero.setOnClickListener(v -> seleccionarTipo("Enfermero"));
        tabAdministrativo.setOnClickListener(v -> seleccionarTipo("Administrativo"));

        seleccionarTipo("Medico");

        btnContratar.setOnClickListener(v -> {
            if (validarCampos()) {
                try {
                    String nombre = etNombre.getText().toString().trim();
                    String apellido = etApellido.getText().toString().trim();
                    String dni = etIdentidad.getText().toString().trim();
                    int edad = Integer.parseInt(etEdad.getText().toString().trim());
                    String idEmpleado = etIdEmpleado.getText().toString().trim();
                    String fechaIngreso = etFechaIngreso.getText().toString().trim();
                    double sueldo = Double.parseDouble(etSueldo.getText().toString().trim());
                    String val1 = etCampoDinamico1.getText().toString().trim();
                    String val2 = etCampoDinamico2.getText().toString().trim();

                    String nacionalidad = "Colombiana";
                    double estatura = 1.70;

                    if (tipoSeleccionado.equals("Medico")) {
                        Medico medico = new Medico(nombre, apellido, "CC", dni, edad, nacionalidad, estatura, idEmpleado, sueldo, val1, val2);
                        medico.setFechaIngreso(fechaIngreso);
                        listaMedicos.add(medico);
                        Toast.makeText(this, "Médico contratado con éxito. Total: " + listaMedicos.size(), Toast.LENGTH_SHORT).show();
                    } else if (tipoSeleccionado.equals("Enfermero")) {
                        Enfermero enfermero = new Enfermero(nombre, apellido, "CC", dni, edad, nacionalidad, estatura, idEmpleado, sueldo, val1);
                        enfermero.setFechaIngreso(fechaIngreso);
                        listaEnfermeros.add(enfermero);
                        Toast.makeText(this, "Enfermero contratado con éxito. Total: " + listaEnfermeros.size(), Toast.LENGTH_SHORT).show();
                    } else if (tipoSeleccionado.equals("Administrativo")) {
                        Administrativo admin = new Administrativo(nombre, apellido, "CC", dni, edad, nacionalidad, estatura, idEmpleado, sueldo, val1, val2);
                        admin.setFechaIngreso(fechaIngreso);
                        listaAdministrativos.add(admin);
                        Toast.makeText(this, "Administrativo contratado con éxito. Total: " + listaAdministrativos.size(), Toast.LENGTH_SHORT).show();
                    }

                    limpiarCampos();

                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Por favor verifique los valores numéricos (Edad o Sueldo).", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnMostrarLista.setOnClickListener(v -> mostrarResumenEmpleados());
    }

    private void seleccionarTipo(String tipo) {
        tipoSeleccionado = tipo;

        tabMedico.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
        tabEnfermero.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
        tabAdministrativo.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));

        tabMedico.setTextColor(ContextCompat.getColor(this, R.color.text_secondary));
        tabEnfermero.setTextColor(ContextCompat.getColor(this, R.color.text_secondary));
        tabAdministrativo.setTextColor(ContextCompat.getColor(this, R.color.text_secondary));

        int selectedBgColor = ContextCompat.getColor(this, R.color.white);
        int textColor = ContextCompat.getColor(this, R.color.black);

        switch (tipo) {
            case "Medico":
                tabMedico.setBackgroundColor(selectedBgColor);
                tabMedico.setTextColor(textColor);
                tilCampoDinamico1.setHint("Especialidad");
                tilCampoDinamico2.setHint("N.º colegiado");
                tilCampoDinamico2.setVisibility(View.VISIBLE);
                break;
            case "Enfermero":
                tabEnfermero.setBackgroundColor(selectedBgColor);
                tabEnfermero.setTextColor(textColor);
                tilCampoDinamico1.setHint("Turno (Mañana/Tarde/Noche)");
                tilCampoDinamico2.setHint("N.º colegiado / Obs.");
                tilCampoDinamico2.setVisibility(View.VISIBLE);
                break;
            case "Administrativo":
                tabAdministrativo.setBackgroundColor(selectedBgColor);
                tabAdministrativo.setTextColor(textColor);
                tilCampoDinamico1.setHint("Departamento");
                tilCampoDinamico2.setHint("Cargo");
                tilCampoDinamico2.setVisibility(View.VISIBLE);
                break;
        }
    }

    private boolean validarCampos() {
        if (TextUtils.isEmpty(etNombre.getText())) { etNombre.setError("Requerido"); return false; }
        if (TextUtils.isEmpty(etApellido.getText())) { etApellido.setError("Requerido"); return false; }
        if (TextUtils.isEmpty(etIdentidad.getText())) { etIdentidad.setError("Requerido"); return false; }
        if (TextUtils.isEmpty(etEdad.getText())) { etEdad.setError("Requerido"); return false; }
        if (TextUtils.isEmpty(etIdEmpleado.getText())) { etIdEmpleado.setError("Requerido"); return false; }
        if (TextUtils.isEmpty(etFechaIngreso.getText())) { etFechaIngreso.setError("Requerido"); return false; }
        if (TextUtils.isEmpty(etSueldo.getText())) { etSueldo.setError("Requerido"); return false; }
        if (TextUtils.isEmpty(etCampoDinamico1.getText())) { etCampoDinamico1.setError("Requerido"); return false; }
        if (tipoSeleccionado.equals("Medico") || tipoSeleccionado.equals("Administrativo")) {
            if (TextUtils.isEmpty(etCampoDinamico2.getText())) { etCampoDinamico2.setError("Requerido"); return false; }
        }
        return true;
    }

    private void limpiarCampos() {
        etNombre.setText("");
        etApellido.setText("");
        etIdentidad.setText("");
        etEdad.setText("");
        etIdEmpleado.setText("");
        etFechaIngreso.setText("");
        etSueldo.setText("");
        etCampoDinamico1.setText("");
        etCampoDinamico2.setText("");
        etNombre.requestFocus();
    }

    private void mostrarResumenEmpleados() {
        int total = listaMedicos.size() + listaEnfermeros.size() + listaAdministrativos.size();
        if (total == 0) {
            Toast.makeText(this, "No hay empleados registrados aún.", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== EMPLEADOS CONTRATADOS (").append(total).append(") ===\n\n");

        if (!listaMedicos.isEmpty()) {
            sb.append("--- MÉDICOS (").append(listaMedicos.size()).append(") ---\n");
            for (int i = 0; i < listaMedicos.size(); i++) {
                Medico m = listaMedicos.get(i);
                sb.append(i + 1).append(". Dr. ").append(m.getNombreCompleto())
                  .append("\n   • Ingreso: ").append(m.getFechaIngreso())
                  .append("\n   • Esp: ").append(m.getEspecialidad())
                  .append("\n   • Colegiado: ").append(m.getNumeroColegiado())
                  .append("\n   • Sueldo: $").append(m.getSueldo()).append(" COP\n");
            }
            sb.append("\n");
        }

        if (!listaEnfermeros.isEmpty()) {
            sb.append("--- ENFERMEROS (").append(listaEnfermeros.size()).append(") ---\n");
            for (int i = 0; i < listaEnfermeros.size(); i++) {
                Enfermero e = listaEnfermeros.get(i);
                sb.append(i + 1).append(". ").append(e.getNombreCompleto())
                  .append("\n   • Ingreso: ").append(e.getFechaIngreso())
                  .append("\n   • Turno: ").append(e.getTurno())
                  .append("\n   • Sueldo: $").append(e.getSueldo()).append(" COP\n");
            }
            sb.append("\n");
        }

        if (!listaAdministrativos.isEmpty()) {
            sb.append("--- ADMINISTRATIVOS (").append(listaAdministrativos.size()).append(") ---\n");
            for (int i = 0; i < listaAdministrativos.size(); i++) {
                Administrativo a = listaAdministrativos.get(i);
                sb.append(i + 1).append(". ").append(a.getNombreCompleto())
                  .append("\n   • Ingreso: ").append(a.getFechaIngreso())
                  .append("\n   • Dept: ").append(a.getDepartamento())
                  .append("\n   • Cargo: ").append(a.getCargo())
                  .append("\n   • Sueldo: $").append(a.getSueldo()).append(" COP\n");
            }
        }

        new AlertDialog.Builder(this)
                .setTitle("Lista de Empleados")
                .setMessage(sb.toString())
                .setPositiveButton("Cerrar", null)
                .show();
    }
}
