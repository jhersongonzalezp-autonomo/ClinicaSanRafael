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
import com.google.android.material.appbar.MaterialToolbar;
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

    static {
        if (listaMedicos.isEmpty()) {
            Medico med = new Medico("Laura", "Gómez", "DNI", "12345678", 40, "Colombiana", 1.65, "M-001", 5000.0, "Cardióloga", "COL-98765");
            med.setFechaIngreso("2024-01-15");
            listaMedicos.add(med);
        }
        if (listaEnfermeros.isEmpty()) {
            Enfermero enf = new Enfermero("Carlos", "Ruiz", "CC", "11223344", 30, "Colombiano", 1.75, "EMP-003", 3000.0, "Turno Mañana");
            enf.setFechaIngreso("2024-02-01");
            listaEnfermeros.add(enf);
        }
        if (listaAdministrativos.isEmpty()) {
            Administrativo adm = new Administrativo("María", "Rodríguez", "DNI", "11223344", 32, "Colombiana", 1.60, "A-101", 2000.0, "Recepción", "Recepcionista");
            adm.setFechaIngreso("2023-11-10");
            listaAdministrativos.add(adm);
        }
    }

    private ImageView btnBack;
    private MaterialToolbar toolbarFormulario;
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
        toolbarFormulario = findViewById(R.id.toolbarFormulario);

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

        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        if (toolbarFormulario != null) {
            toolbarFormulario.setNavigationOnClickListener(v -> finish());
        }

        if (tabMedico != null) tabMedico.setOnClickListener(v -> seleccionarTipo("Medico"));
        if (tabEnfermero != null) tabEnfermero.setOnClickListener(v -> seleccionarTipo("Enfermero"));
        if (tabAdministrativo != null) tabAdministrativo.setOnClickListener(v -> seleccionarTipo("Administrativo"));

        seleccionarTipo("Medico");

        if (btnContratar != null) {
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
                        String val2 = etCampoDinamico2 != null && etCampoDinamico2.getText() != null ? etCampoDinamico2.getText().toString().trim() : "";

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
        }

        if (btnMostrarLista != null) {
            btnMostrarLista.setOnClickListener(v -> mostrarResumenEmpleados());
        }
    }

    private void seleccionarTipo(String tipo) {
        tipoSeleccionado = tipo;

        int transparent = ContextCompat.getColor(this, android.R.color.transparent);
        int selectedBgColor = ContextCompat.getColor(this, R.color.white);
        int textColor = ContextCompat.getColor(this, R.color.black);

        if (tabMedico != null) { tabMedico.setBackgroundColor(transparent); tabMedico.setTextColor(textColor); }
        if (tabEnfermero != null) { tabEnfermero.setBackgroundColor(transparent); tabEnfermero.setTextColor(textColor); }
        if (tabAdministrativo != null) { tabAdministrativo.setBackgroundColor(transparent); tabAdministrativo.setTextColor(textColor); }

        switch (tipo) {
            case "Medico":
                if (tabMedico != null) tabMedico.setBackgroundColor(selectedBgColor);
                if (tilCampoDinamico1 != null) tilCampoDinamico1.setHint("Especialidad");
                if (tilCampoDinamico2 != null) { tilCampoDinamico2.setHint("N.º colegiado"); tilCampoDinamico2.setVisibility(View.VISIBLE); }
                if (toolbarFormulario != null) toolbarFormulario.setTitle("Registrar médico");
                break;
            case "Enfermero":
                if (tabEnfermero != null) tabEnfermero.setBackgroundColor(selectedBgColor);
                if (tilCampoDinamico1 != null) tilCampoDinamico1.setHint("Turno (Mañana/Tarde/Noche)");
                if (tilCampoDinamico2 != null) { tilCampoDinamico2.setHint("N.º colegiado / Obs."); tilCampoDinamico2.setVisibility(View.VISIBLE); }
                if (toolbarFormulario != null) toolbarFormulario.setTitle("Registrar enfermero");
                break;
            case "Administrativo":
                if (tabAdministrativo != null) tabAdministrativo.setBackgroundColor(selectedBgColor);
                if (tilCampoDinamico1 != null) tilCampoDinamico1.setHint("Departamento");
                if (tilCampoDinamico2 != null) { tilCampoDinamico2.setHint("Cargo"); tilCampoDinamico2.setVisibility(View.VISIBLE); }
                if (toolbarFormulario != null) toolbarFormulario.setTitle("Registrar administrativo");
                break;
        }
    }

    private boolean validarCampos() {
        if (etNombre != null && TextUtils.isEmpty(etNombre.getText())) { etNombre.setError("Requerido"); return false; }
        if (etApellido != null && TextUtils.isEmpty(etApellido.getText())) { etApellido.setError("Requerido"); return false; }
        if (etIdentidad != null && TextUtils.isEmpty(etIdentidad.getText())) { etIdentidad.setError("Requerido"); return false; }
        if (etEdad != null && TextUtils.isEmpty(etEdad.getText())) { etEdad.setError("Requerido"); return false; }
        if (etIdEmpleado != null && TextUtils.isEmpty(etIdEmpleado.getText())) { etIdEmpleado.setError("Requerido"); return false; }
        if (etFechaIngreso != null && TextUtils.isEmpty(etFechaIngreso.getText())) { etFechaIngreso.setError("Requerido"); return false; }
        if (etSueldo != null && TextUtils.isEmpty(etSueldo.getText())) { etSueldo.setError("Requerido"); return false; }
        if (etCampoDinamico1 != null && TextUtils.isEmpty(etCampoDinamico1.getText())) { etCampoDinamico1.setError("Requerido"); return false; }
        if (tipoSeleccionado.equals("Medico") || tipoSeleccionado.equals("Administrativo")) {
            if (etCampoDinamico2 != null && TextUtils.isEmpty(etCampoDinamico2.getText())) { etCampoDinamico2.setError("Requerido"); return false; }
        }
        return true;
    }

    private void limpiarCampos() {
        if (etNombre != null) etNombre.setText("");
        if (etApellido != null) etApellido.setText("");
        if (etIdentidad != null) etIdentidad.setText("");
        if (etEdad != null) etEdad.setText("");
        if (etIdEmpleado != null) etIdEmpleado.setText("");
        if (etFechaIngreso != null) etFechaIngreso.setText("");
        if (etSueldo != null) etSueldo.setText("");
        if (etCampoDinamico1 != null) etCampoDinamico1.setText("");
        if (etCampoDinamico2 != null) etCampoDinamico2.setText("");
        if (etNombre != null) etNombre.requestFocus();
    }

    private void mostrarResumenEmpleados() {
        int total = listaMedicos.size() + listaEnfermeros.size() + listaAdministrativos.size();
        if (total == 0) {
            Toast.makeText(this, "No hay empleados registrados aún.", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== PERSONAL DEL CENTRO MÉRICO (").append(total).append(") ===\n\n");

        if (!listaMedicos.isEmpty()) {
            sb.append("--- MÉDICOS (").append(listaMedicos.size()).append(") ---\n");
            for (int i = 0; i < listaMedicos.size(); i++) {
                Medico m = listaMedicos.get(i);
                sb.append(i + 1).append(". Dr. ").append(m.getNombreCompleto())
                  .append("\n   • Ingreso: ").append(m.getFechaIngreso() != null ? m.getFechaIngreso() : "N/A")
                  .append("\n   • Esp: ").append(m.getEspecialidad() != null ? m.getEspecialidad() : "Cardiología")
                  .append("\n   • Colegiado: ").append(m.getNumeroColegiado() != null ? m.getNumeroColegiado() : "M-100")
                  .append("\n   • Sueldo: $").append(m.getSueldo()).append(" COP\n\n");
            }
        }

        if (!listaEnfermeros.isEmpty()) {
            sb.append("--- ENFERMEROS (").append(listaEnfermeros.size()).append(") ---\n");
            for (int i = 0; i < listaEnfermeros.size(); i++) {
                Enfermero e = listaEnfermeros.get(i);
                sb.append(i + 1).append(". ").append(e.getNombreCompleto())
                  .append("\n   • Ingreso: ").append(e.getFechaIngreso() != null ? e.getFechaIngreso() : "N/A")
                  .append("\n   • Turno: ").append(e.getTurno() != null ? e.getTurno() : "Mañana")
                  .append("\n   • Sueldo: $").append(e.getSueldo()).append(" COP\n\n");
            }
        }

        if (!listaAdministrativos.isEmpty()) {
            sb.append("--- ADMINISTRATIVOS (").append(listaAdministrativos.size()).append(") ---\n");
            for (int i = 0; i < listaAdministrativos.size(); i++) {
                Administrativo a = listaAdministrativos.get(i);
                sb.append(i + 1).append(". ").append(a.getNombreCompleto())
                  .append("\n   • Ingreso: ").append(a.getFechaIngreso() != null ? a.getFechaIngreso() : "N/A")
                  .append("\n   • Dept: ").append(a.getDepartamento() != null ? a.getDepartamento() : "Recepción")
                  .append("\n   • Cargo: ").append(a.getCargo() != null ? a.getCargo() : "Administrativo")
                  .append("\n   • Sueldo: $").append(a.getSueldo()).append(" COP\n");
            }
        }

        new AlertDialog.Builder(this)
                .setTitle("Personal del Centro Médico")
                .setMessage(sb.toString())
                .setPositiveButton("Cerrar", null)
                .show();
    }
}
