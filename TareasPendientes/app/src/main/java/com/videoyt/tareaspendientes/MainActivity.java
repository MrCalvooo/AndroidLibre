package com.videoyt.tareaspendientes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText etTarea;
    private Button btnAgregar;

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

        etTarea = findViewById(R.id.etTarea);
        btnAgregar = findViewById(R.id.btnAgregar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tarea = etTarea.getText().toString();

                if (tarea.isEmpty()) {
                    // Si la tarea está vacía, muestra un mensaje de error.
                    Toast.makeText(getApplicationContext(), "Tarea vacía", Toast.LENGTH_LONG).show();
                } else {
                    if (existeTarea(tarea)) {
                        Toast.makeText(getApplicationContext(), "Tarea ya registada", Toast.LENGTH_LONG).show();
                        return;
                    }
                    registrarTarea(tarea);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tareas, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), ListaTareas.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    public boolean existeTarea(String tarea) {
        try {
            InputStreamReader isr = new InputStreamReader(openFileInput("tareas.txt"));
            BufferedReader br = new BufferedReader(isr);
            String linea = "";

            while ((linea = br.readLine()) != null) {
                if (linea.equalsIgnoreCase(tarea)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void registrarTarea(String tarea) {
        // Obtiene el archivo en el almacenamiento interno
        FileOutputStream fos = null;
        try {
            // Abre el archivo en el almacenamiento interno. Si no existe, se creará.
            fos = openFileOutput("tareas.txt", MODE_APPEND); // El modo MODE_APPEND permite agregar tareas sin sobrescribir
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(tarea + "\n");  // Escribe la tarea en el archivo
            osw.flush();  // Asegura que se escriba todo
            osw.close();  // Cierra el flujo de salida
            fos.close();  // Cierra el flujo de archivo

            // Muestra un mensaje de éxito
            Toast.makeText(getApplicationContext(), "Tarea registrada correctamente", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            // Si hay un error al escribir el archivo, muestra un mensaje de error
            Toast.makeText(getApplicationContext(), "Error al registrar la tarea", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}