package com.videoyt.a14alertdialoglistasmultiples;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnDia, btnMes, btnHora;
    private TextView tvDia, tvMes, tvHora;
    private int hora = 0, minuto = 0;

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

        btnDia = findViewById(R.id.btn);
        btnMes = findViewById(R.id.btnMeses);
        btnHora = findViewById(R.id.btnHora);

        tvDia = findViewById(R.id.tvDia);
        tvMes = findViewById(R.id.tvMes);
        tvHora = findViewById(R.id.tvHora);

        btnDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Seleccione dia de la semana");
                String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
                builder.setItems(dias, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvDia.setText(String.format("Dia: %s\n", dias[which]));
                    }
                });
                builder.create().show();
            }
        });

        btnMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                String[] meses = {
                        "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
                };
                boolean[] checks = new boolean[12];

                builder.setMultiChoiceItems(meses, checks, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        StringBuilder todo = new StringBuilder();
                        for (int i = 0; i < checks.length; i++) {
                            if (checks[i]) {
                                todo.append(meses[i]).append(" - ");
                            }
                        }
                        tvMes.setText(todo);
                    }
                });

                builder.setPositiveButton("Confirmar", null);

                builder.create().show();
            }
        });

        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora = hourOfDay;
                        minuto = minute;
                        tvHora.setText(String.format("Hora seleccionada: %d:%dh\n", hora, minuto));
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });
    }
}