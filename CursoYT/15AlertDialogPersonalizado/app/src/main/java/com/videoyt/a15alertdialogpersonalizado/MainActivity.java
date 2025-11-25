package com.videoyt.a15alertdialogpersonalizado;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    private Button btn;
    private SeekBar sbRojo, sbVerde, sbAzul;
    private TextView tvRojo, tvVerde, tvAzul;
    private View color;


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

        btn = findViewById(R.id.btnColor);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Seleccione un color");

                View view = getLayoutInflater().inflate(R.layout.selectorcolordialog, null);
                builder.setView(view);
                color = view.findViewById(R.id.color);

                sbRojo = view.findViewById(R.id.sbRojo);
                sbVerde = view.findViewById(R.id.sbVerde);
                sbAzul = view.findViewById(R.id.sbAzul);

                tvRojo = view.findViewById(R.id.tvRojo);
                tvVerde = view.findViewById(R.id.tvVerde);
                tvAzul = view.findViewById(R.id.tvAzul);

                sbRojo.setOnSeekBarChangeListener(MainActivity.this);
                sbVerde.setOnSeekBarChangeListener(MainActivity.this);
                sbAzul.setOnSeekBarChangeListener(MainActivity.this);


                color.setBackgroundColor(Color.rgb(sbRojo.getProgress(), sbVerde.getProgress(), sbAzul.getProgress()));
                builder.setPositiveButton("Ok", (dialog, which) -> {
                    btn.setBackgroundColor(Color.rgb(sbRojo.getProgress(), sbVerde.getProgress(), sbAzul.getProgress()));
                });
                builder.create().show();
            }
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int r = sbRojo.getProgress();
        int g = sbVerde.getProgress();
        int b = sbAzul.getProgress();

        tvRojo.setText(String.valueOf(r));
        tvVerde.setText(String.valueOf(g));
        tvAzul.setText(String.valueOf(b));

        color.setBackgroundColor(Color.rgb(r,g,b));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
