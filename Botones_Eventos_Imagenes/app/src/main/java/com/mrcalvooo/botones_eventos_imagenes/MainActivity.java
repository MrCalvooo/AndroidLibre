package com.mrcalvooo.botones_eventos_imagenes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Establecemos las variables de los elementos que usaremos en metodos y funciones
    private RadioButton rojo, verde, azul;
    private TextView texto;

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

        // Buscamos los elementos en el XML
        rojo = findViewById(R.id.rojo);
        azul = findViewById(R.id.azul);
        verde = findViewById(R.id.verde);
        texto = findViewById(R.id.colorSeleccionado);
    }

    @SuppressLint("SetTextI18n")
    public void colorSeleccionado(View view){
        // Comprobamos que boton esta pulsado y se muestra al usuario el boton pulsado
        if (rojo.isChecked()){
            texto.setText("El color " + rojo.getText() + " ha sido elegido");
            texto.setTextColor(getResources().getColor(R.color.rojo));
        } else if (azul.isChecked()){
            texto.setText("El color " + azul.getText() + " ha sido elegido");
            texto.setTextColor(getResources().getColor(R.color.azul));
        } else if (verde.isChecked()){
            texto.setText("El color " + verde.getText() + " ha sido elegido");
            texto.setTextColor(getResources().getColor(R.color.verde));
        } else {
            texto.setText("SELECCIONE UN COLOR");
        }
    }
}