package com.videoyt.a17ficherostextoenmemoriamovil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;
    private Button btnGuardar, btnReestablecer;

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

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnReestablecer = findViewById(R.id.btnReestablecer);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fich = et1.getText().toString();
                String content = et2.getText().toString();

                try {
                    OutputStreamWriter osw = new OutputStreamWriter(openFileOutput(fich, MODE_PRIVATE));
                    osw.write(content);
                    osw.flush();
                    osw.close();
                    et2.setText("");
                    Toast.makeText(getApplicationContext(), "Datos guardados", Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "No se encontro el archivo " + fich, Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Error al escribir en el fichero", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnReestablecer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputStreamReader isr = new InputStreamReader(openFileInput(et1.getText().toString()));
                    BufferedReader br = new BufferedReader(isr);
                    String linea = "";
                    while ((linea = br.readLine()) != null) {
                        et2.append(linea + "\n");
                    }
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "No se encontro el archivo ", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Error al leer el fichero", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}