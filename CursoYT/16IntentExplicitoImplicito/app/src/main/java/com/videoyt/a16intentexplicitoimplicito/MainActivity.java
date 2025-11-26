package com.videoyt.a16intentexplicitoimplicito;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnNavegador, btnMaps, btnContactos, btnCamara;

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

        btnNavegador = findViewById(R.id.btnNavegador);
        btnMaps = findViewById(R.id.btnMaps);
        btnContactos = findViewById(R.id.btnContactos);
        btnCamara = findViewById(R.id.btnCamara);

        btnNavegador.setOnClickListener(this);
        btnMaps.setOnClickListener(this);
        btnContactos.setOnClickListener(this);
        btnCamara.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnNavegador) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://imontalvo.dev/"));
            startActivity(intent);
        }

        if (v.getId() == R.id.btnMaps) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-31, -64"));
            startActivity(intent);
        }

        if (v.getId() == R.id.btnMaps) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-31, -64"));
            startActivity(intent);
        }

        if (v.getId() == R.id.btnContactos) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
            startActivity(intent);
        }

        if (v.getId() == R.id.btnCamara) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivity(intent);
        }
    }
}