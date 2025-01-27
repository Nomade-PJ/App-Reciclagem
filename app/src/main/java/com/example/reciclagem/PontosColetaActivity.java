package com.example.reciclagem;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class PontosColetaActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btnAddPonto;
    private PontosColetaAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontos_coleta);

        dbHelper = new DatabaseHelper(this);
        
        recyclerView = findViewById(R.id.recyclerView);
        btnAddPonto = findViewById(R.id.btnAddPonto);
        
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PontosColetaAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        btnAddPonto.setOnClickListener(v -> showAddPontoDialog());

        loadPontosColeta();
    }

    private void showAddPontoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_ponto, null);
        
        EditText editTextNome = dialogView.findViewById(R.id.editTextNome);
        EditText editTextEndereco = dialogView.findViewById(R.id.editTextEndereco);

        builder.setView(dialogView)
                .setTitle("Adicionar Ponto de Coleta")
                .setPositiveButton("Adicionar", (dialog, id) -> {
                    String nome = editTextNome.getText().toString();
                    String endereco = editTextEndereco.getText().toString();
                    
                    if (!nome.isEmpty() && !endereco.isEmpty()) {
                        dbHelper.addPontoColeta(nome, endereco);
                        loadPontosColeta();
                    }
                })
                .setNegativeButton("Cancelar", null);

        builder.create().show();
    }

    private void loadPontosColeta() {
        List<PontoColeta> pontosColeta = dbHelper.getAllPontosColeta();
        adapter.atualizarDados(pontosColeta);
    }
} 