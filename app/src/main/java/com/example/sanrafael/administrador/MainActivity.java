package com.example.sanrafael.administrador;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sanrafael.R;
import com.example.sanrafael.Persona;
import com.example.sanrafael.medico.Medico;
import com.example.sanrafael.administrativo.Administrativo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Persona persona = new Persona("Laura", "Martínez", "CC", "1094556677",
                29, "Colombiana", 1.65);

        Medico medico = new Medico("Elena", "Sánchez", "DNI", "88877766",
                42, "Colombiana", 1.68, "M-001", 4500.0,
                "Cardióloga", "COL-9921");

        Administrativo admin = new Administrativo("Ricardo", "Gómez", "DNI", "11122233",
                35, "Mexicano", 1.80, "A-505", 2200.0,
                "Recursos Humanos", "Analista");
    }
}
