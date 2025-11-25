package com.videoyt.a05switch;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Switch sw1, sw2;
    private Button btn;

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

        sw1 = findViewById(R.id.sw1);
        sw2 = findViewById(R.id.sw2);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        sw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sw2.isChecked()) {
                    sw2.setChecked(false);
                }

                if (sw1.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Datos activados", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Datos desactivados", Toast.LENGTH_LONG).show();
                }
            }
        });

        sw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sw1.isChecked()) {
                    sw1.setChecked(false);
                }

                if (sw2.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Wifi activados", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Wifi desactivados", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}