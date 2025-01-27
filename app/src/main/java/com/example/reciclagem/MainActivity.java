package com.example.reciclagem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnPontosColeta;
    private Button btnMateriais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPontosColeta = findViewById(R.id.btnPontosColeta);
        btnMateriais = findViewById(R.id.btnMateriais);

        btnPontosColeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PontosColetaActivity.class);
                startActivity(intent);
            }
        });

        btnMateriais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MateriaisActivity.class);
                startActivity(intent);
            }
        });
    }
} 