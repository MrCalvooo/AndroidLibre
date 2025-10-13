package com.mrcalvooo.miagenda;

import static android.widget.Toast.LENGTH_LONG;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AgregarTarea extends AppCompatActivity {

    private EditText editTextNombreTarea;
    private Button btnEstablecerHora, btnGuardarTarea;
    private RadioGroup rgbPrioridades;
    private TextView txtVerHoraTarea;
    private int hora = 0, minuto = 0;
    private boolean horaSeleccionada = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Establecemos la vista que tendra esta clase (agregar_tarea.xml)
        setContentView(R.layout.agregar_tarea);

        editTextNombreTarea = findViewById(R.id.editTextNombreTarea);
        btnEstablecerHora = findViewById(R.id.btnEstablecerHora);
        txtVerHoraTarea = findViewById(R.id.txtVerHoraTarea);
        btnGuardarTarea = findViewById(R.id.btnGuardarTarea);
        rgbPrioridades = findViewById(R.id.rgbPrioridades);

        txtVerHoraTarea.setText("00:00");
        btnEstablecerHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tpd = new TimePickerDialog(AgregarTarea.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora = hourOfDay;
                        minuto = minute;
                        txtVerHoraTarea.setText(String.format("%d:%dh", hora, minuto));
                        horaSeleccionada = true;
                    }
                }, hora, minuto, true);
                tpd.show();
            }
        });

        btnGuardarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener ID de la prioridad seleccionada
                int id = rgbPrioridades.getCheckedRadioButtonId();

                // Comprobamos que el usuario haya seleccionado una prioridad
                if (id == -1) {
                    Toast.makeText(AgregarTarea.this, "Seleccione una prioridad porfavor",
                            LENGTH_LONG).show();
                }

                if (!horaSeleccionada) {
                    Toast.makeText(AgregarTarea.this, "Seleccione una hora porfavor",
                            LENGTH_LONG).show();
                }

                // Encontrar la priroridad seleccionada
                RadioButton rb = findViewById(id);
            }
        });
    }
}
