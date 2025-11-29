package com.mrcalvooo.gestortareas;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class VerTareas extends AppCompatActivity {

    private ListView lvTareas;
    private ArrayAdapter<Tarea> arrayAdapterTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver_tareas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvTareas = findViewById(R.id.lvTareas);

        arrayAdapterTareas = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MainActivity.tareas);
        lvTareas.setAdapter(arrayAdapterTareas);

        lvTareas.setOnItemClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(VerTareas.this);
            builder.setTitle("Editar tarea");
            builder.setMessage("¿Tarea completada?");
            builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.tareas.get(position).setCompletada(true);
                    arrayAdapterTareas.notifyDataSetChanged();
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("No", null);
            builder.show();
        });

        lvTareas.setOnItemLongClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(VerTareas.this);
            builder.setTitle("Eliminar tarea");
            builder.setMessage("¿Desea eliminar la tarea seleccionada?");
            builder.setPositiveButton("Si", (dialog, which) -> {
                MainActivity.tareas.remove(position);
                arrayAdapterTareas.notifyDataSetChanged();
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Tarea eliminada correctamente", Toast.LENGTH_LONG).show();
            });
            builder.setNegativeButton("No", null);
            builder.show();
            return true;
        });
    }
}
