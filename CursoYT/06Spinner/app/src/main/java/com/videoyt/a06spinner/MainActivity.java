package com.videoyt.a06spinner;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private TextView tvResultado;
    private EditText et1, et2;
    private Button button;
    private String[] operaciones = {"+", "-", "*", "/"};

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

        spinner = findViewById(R.id.spinner);
        tvResultado = findViewById(R.id.tvResultado);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        button = findViewById(R.id.btn);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, operaciones);
        spinner.setAdapter(arrayAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valor1 = Integer.parseInt(et1.getText().toString());
                int valor2 = Integer.parseInt(et2.getText().toString());
                String operacion = spinner.getSelectedItem().toString();

                if (operacion.equalsIgnoreCase("+")) {
                    tvResultado.setText("Resultado: " + (valor1 + valor2));
                } else if (operacion.equalsIgnoreCase("-")) {
                    tvResultado.setText("Resultado: " + (valor1 - valor2));
                } else if (operacion.equalsIgnoreCase("*")) {
                    tvResultado.setText("Resultado: " + (valor1 * valor2));
                } else if (operacion.equalsIgnoreCase("/")) {
                    if (valor2 == 0) {
                        tvResultado.setText("No se puede dividir entre cero");
                    } else {
                        tvResultado.setText("Resultado: " + (valor1 / valor2));
                    }
                }
            }
        });

    }
}