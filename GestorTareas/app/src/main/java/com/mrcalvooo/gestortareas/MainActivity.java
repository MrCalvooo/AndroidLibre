package com.mrcalvooo.gestortareas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected static List<Tarea> tareas = new ArrayList<>();
    protected File file;
    private EditText etTareaNueva;
    private Button btnTareaNueva;

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

        file = new File(getFilesDir(), "tareas.dat"); // Ruta del archivo de tareas

        // Cargar tareas desde el archivo al inicio
        cargarTareas();

        // Si no hay tareas guardadas, precargar tareas predeterminadas
        if (tareas.isEmpty()) {
            precargarTareas();
            guardarTareas(); // Guardar las tareas precargadas en el archivo
        }

        etTareaNueva = findViewById(R.id.etTareaNueva);
        btnTareaNueva = findViewById(R.id.btnTareaNueva);

        btnTareaNueva.setOnClickListener(v -> {
            String tareaNombre = etTareaNueva.getText().toString().trim();
            if (tareaNombre.isEmpty()) {
                Toast.makeText(getApplicationContext(), "No se puede agregar una tarea vacía", Toast.LENGTH_LONG).show();
                return;
            }

            // Verificar si la tarea ya existe
            if (!existeTarea(tareaNombre)) {
                Tarea nuevaTarea = new Tarea(tareaNombre);
                tareas.add(nuevaTarea); // Añadir la tarea a la lista

                // Guardar la nueva tarea en el archivo
                guardarTareas();

                etTareaNueva.setText(""); // Limpiar el campo de texto
                Toast.makeText(MainActivity.this, "Tarea agregada", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Tarea ya existente", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para cargar tareas desde el archivo
    private void cargarTareas() {
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    try {
                        Tarea tarea = (Tarea) ois.readObject();
                        tareas.add(tarea);
                    } catch (IOException e) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para guardar tareas en el archivo
    private void guardarTareas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Tarea t: tareas) {
                oos.writeObject(t);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para precargar tareas predeterminadas
    private void precargarTareas() {
        tareas.add(new Tarea("Estudiar Android"));
        tareas.add(new Tarea("Ir al gimnasio"));
        tareas.add(new Tarea("Hacer la colada"));
        tareas.add(new Tarea("Ir al supermercado"));
        tareas.add(new Tarea("Recoger la habitación"));
    }

    private boolean existeTarea(String tareaNombre) {
        for (Tarea t : tareas) {
            if (t.getNombre().equalsIgnoreCase(tareaNombre)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ver_tareas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, VerTareas.class);
        startActivity(intent);
        return true;
    }
}
