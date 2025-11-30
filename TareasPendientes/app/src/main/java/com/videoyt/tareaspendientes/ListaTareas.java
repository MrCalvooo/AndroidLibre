package com.videoyt.tareaspendientes;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ListaTareas extends AppCompatActivity {

    private ListView lvTareas;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> tareas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_tareas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvTareas = findViewById(R.id.lvTareas);
        cargarTareas();
        arrayAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, tareas);
        lvTareas.setAdapter(arrayAdapter);

        lvTareas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtiene la tarea seleccionada
                String tarea = tareas.get(position);

                // Elimina la tarea de la lista
                tareas.remove(position);

                // Notifica al Adapter que se ha actualizado la lista
                arrayAdapter.notifyDataSetChanged();

                // Ahora vamos a eliminar la tarea del archivo
                try {
                    // Abrimos el archivo para leerlo
                    FileInputStream fis = openFileInput("tareas.txt");
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);

                    // Lista para almacenar las tareas restantes
                    List<String> tareasRestantes = new ArrayList<>();

                    String linea;
                    // Lee el archivo línea por línea
                    while ((linea = br.readLine()) != null) {
                        // Si la tarea no es la que vamos a eliminar, la agregamos a la lista
                        if (!linea.equalsIgnoreCase(tarea)) {
                            tareasRestantes.add(linea);
                        }
                    }

                    // Cierra los flujos de lectura
                    br.close();
                    isr.close();
                    fis.close();

                    // Ahora vamos a sobrescribir el archivo con las tareas restantes
                    FileOutputStream fos = openFileOutput("tareas.txt", MODE_PRIVATE); // Usamos MODE_PRIVATE para sobrescribir el archivo
                    OutputStreamWriter osw = new OutputStreamWriter(fos);

                    // Escribimos las tareas restantes de vuelta en el archivo
                    for (String tareaRestante : tareasRestantes) {
                        osw.write(tareaRestante + "\n");
                    }

                    // Cerramos los flujos de escritura
                    osw.flush();
                    osw.close();
                    fos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error al eliminar la tarea", Toast.LENGTH_LONG).show();
                }

                return true;
            }
        });

    }

    private void cargarTareas() {
        try {
            InputStreamReader isr = new InputStreamReader(openFileInput("tareas.txt"));
            BufferedReader br = new BufferedReader(isr);
            String linea = "";

            while ((linea = br.readLine()) != null) {
                tareas.add(linea);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}