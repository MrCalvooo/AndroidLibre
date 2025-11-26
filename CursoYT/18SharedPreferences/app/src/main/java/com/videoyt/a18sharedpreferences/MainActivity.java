package com.videoyt.a18sharedpreferences;

import android.content.SharedPreferences;
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

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;
    private Button btnGuardar, btnRecuperar;

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
        btnRecuperar = findViewById(R.id.btnRecuperar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("agenda", MODE_PRIVATE).edit();
                String fecha = et1.getText().toString();
                String texto = et2.getText().toString();
                editor.putString(fecha, texto);
                editor.apply();
                et1.setText("");
                et2.setText("");
            }
        });

        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("agenda", MODE_PRIVATE);
                String fecha = sharedPreferences.getString(et1.getText().toString(), "");
                if (fecha.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "No hay nada para esa fecha", Toast.LENGTH_LONG).show();
                } else {
                    et2.setText(fecha);
                }
            }
        });
    }
}