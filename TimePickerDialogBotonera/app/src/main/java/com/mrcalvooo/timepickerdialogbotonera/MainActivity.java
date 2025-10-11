package com.mrcalvooo.timepickerdialogbotonera;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    // Declaración de variables para los botones y el TextView
    private Button btHora, btMostrarHora, btReestablecerHora;
    private TextView tvHora;

    // Variables para almacenar la hora y el minuto seleccionados
    private int horaSeleccionada, minutoSeleccionado;

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

        // Inicialización de los botones y el TextView
        btHora = findViewById(R.id.btHora);
        btMostrarHora = findViewById(R.id.btMostrarHora);
        btReestablecerHora = findViewById(R.id.btReestablecerHora);
        tvHora = findViewById(R.id.tvHora);

        // Listener para el botón de seleccionar hora
        btHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarHora();
            }
        });

        // Listener para el botón de mostrar hora
        btMostrarHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarHora();
            }
        });

        // Listener para el botón de reestablecer hora
        btReestablecerHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reestablecerHora();
            }
        });
    }

    /**
     * Muestra un TimePickerDialog para que el usuario seleccione una hora.
     * La hora y el minuto actuales se usan como valores predeterminados.
     * La hora y el minuto seleccionados se guardan en las variables
     * horaSeleccionada and minutoSeleccionado.
     */
    private void seleccionarHora() {
        // Obtiene la hora y el minuto actuales
        final Calendar c = Calendar.getInstance();
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int minuto = c.get(Calendar.MINUTE);

        // Crea un nuevo TimePickerDialog
        TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // Guarda la hora y el minuto seleccionados
                horaSeleccionada = hourOfDay;
                minutoSeleccionado = minute;
            }
        }, hora, minuto, true); // El último parámetro indica el formato de 24 horas

        // Muestra el TimePickerDialog
        tpd.show();
    }

    /**
     * Muestra la hora seleccionada en el TextView.
     */
    private void mostrarHora(){
        tvHora.setText(String.format("%02d:%02d", horaSeleccionada, minutoSeleccionado));
    }

    /**
     * Reestablece la hora y el minuto seleccionados a 0 y borra el TextView.
     */
    private void reestablecerHora() {
        horaSeleccionada = 0;
        minutoSeleccionado =0;
        tvHora.setText("");
    }
}
