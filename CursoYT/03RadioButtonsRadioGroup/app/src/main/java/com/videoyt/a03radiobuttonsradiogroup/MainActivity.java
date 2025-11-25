package com.videoyt.a03radiobuttonsradiogroup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;
    private Button button;
    private TextView textView;
    private RadioButton rb1, rb2;

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

        et1 = findViewById(R.id.editTextNumberSigned);
        et2 = findViewById(R.id.editTextNumberSigned2);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(et1.getText().toString());
                int num2 = Integer.parseInt(et2.getText().toString());
                int resultado = 0;
                if (rb1.isChecked()) {
                    resultado = num1 + num2;
                } else if (rb2.isChecked()) {
                    resultado = num1 - num2;
                }

                String og = textView.getText().toString();
                textView.setText(String.format("%s %d", og, resultado));
            }
        });

    }
}