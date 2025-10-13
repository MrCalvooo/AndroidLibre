package com.mrcalvooo.miagenda;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAgregarTarea, btnEliminarTarea;
    private EditText etxtTareaEliminar;
    protected static ArrayList<Tarea> listaTareas = new ArrayList<>();
    private TableLayout tableTareas;

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
        btnAgregarTarea = findViewById(R.id.btnAgregarTarea);
        btnAgregarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Al pulsar el boton se dirige al usuario a otra pestaña de la app
                Intent intent = new Intent(MainActivity.this, AgregarTarea.class);
                startActivity(intent);
            }
        });
        tableTareas = findViewById(R.id.tableTareas);
        btnEliminarTarea = findViewById(R.id.btnEliminarTarea);
        etxtTareaEliminar = findViewById(R.id.etxtTarea);
    }

    // Funcion a ejecutar al volver de la vista de AgregarTarea
    @Override
    protected void onResume() {
        super.onResume();
        cargarTareas();
    }

    public void cargarTareas() {
        // Limpiamos la vista anterior de tareas
        tableTareas.removeAllViews();

        // Definimos el encabezado de la tabla
        TableRow encabezado = new TableRow(this);
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);

        TextView tituloTarea = new TextView(this);
        tituloTarea.setText("Tarea");
        tituloTarea.setPadding(16, 16, 16, 16);
        tituloTarea.setLayoutParams(params);
        encabezado.addView(tituloTarea);

        TextView horaTarea = new TextView(this);
        horaTarea.setText("Hora");
        horaTarea.setPadding(16, 16, 16, 16);
        horaTarea.setLayoutParams(params);
        encabezado.addView(horaTarea);

        TextView prioridadTarea = new TextView(this);
        prioridadTarea.setText("Prioridad");
        prioridadTarea.setPadding(16, 16, 16, 16);
        prioridadTarea.setLayoutParams(params);
        encabezado.addView(prioridadTarea);

        tableTareas.addView(encabezado);

        for (Tarea tarea : listaTareas) {
            TableRow fila = new TableRow(this);

            // Nombre
            TextView tareaNombre = new TextView(this);
            tareaNombre.setText(tarea.getNombre());
            tareaNombre.setPadding(16, 16, 16, 16);
            tareaNombre.setLayoutParams(params);
            fila.addView(tareaNombre);

            // Hora
            TextView tareaHora = new TextView(this);
            tareaHora.setText(String.format("%02d:%02d", tarea.getHora(), tarea.getMinuto()));
            tareaHora.setPadding(16, 16, 16, 16);
            tareaHora.setLayoutParams(params);
            fila.addView(tareaHora);

            // Prioridad
            TextView tareaPrioridad = new TextView(this);

            String prioridad = tarea.getPrioridad();

            if (prioridad.equalsIgnoreCase("Prioridad Alta")){
                tareaPrioridad.setTextColor(getResources().getColor(R.color.rojo));
            }

            if (prioridad.equalsIgnoreCase("Prioridad Media")){
                tareaPrioridad.setTextColor(getResources().getColor(R.color.blue));
            }

            if (prioridad.equalsIgnoreCase("Prioridad Baja")){
                tareaPrioridad.setTextColor(getResources().getColor(R.color.green));
            }

            tareaPrioridad.setText(prioridad);

            tareaPrioridad.setPadding(16, 16, 16, 16);
            tareaPrioridad.setLayoutParams(params);
            fila.addView(tareaPrioridad);

            tableTareas.addView(fila);
        }
    }
}