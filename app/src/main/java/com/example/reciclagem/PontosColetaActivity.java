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
    private Button btnExcluir;
    private PontosColetaAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontos_coleta);

        dbHelper = new DatabaseHelper(this);
        
        recyclerView = findViewById(R.id.recyclerView);
        btnAddPonto = findViewById(R.id.btnAddPonto);
        btnExcluir = findViewById(R.id.btnExcluir);
        
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PontosColetaAdapter(new ArrayList<>(), dbHelper);
        recyclerView.setAdapter(adapter);

        btnAddPonto.setOnClickListener(v -> showAddPontoDialog());
        btnExcluir.setOnClickListener(v -> toggleModoExclusao());

        loadPontosColeta();
    }

    private void showAddPontoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_ponto, null);
        
        EditText editTextNome = dialogView.findViewById(R.id.editTextNome);
        EditText editTextEndereco = dialogView.findViewById(R.id.editTextEndereco);
        EditText editTextMateriais = dialogView.findViewById(R.id.editTextMateriais);

        builder.setView(dialogView)
                .setTitle("Adicionar Ponto de Coleta")
                .setPositiveButton("Adicionar", (dialog, id) -> {
                    String nome = editTextNome.getText().toString();
                    String endereco = editTextEndereco.getText().toString();
                    String materiais = editTextMateriais.getText().toString();
                    
                    if (!nome.isEmpty() && !endereco.isEmpty()) {
                        dbHelper.addPontoColeta(nome, endereco, materiais);
                        loadPontosColeta();
                    }
                })
                .setNegativeButton("Cancelar", null);

        builder.create().show();
    }

    private void toggleModoExclusao() {
        if (!adapter.modoExclusao) {
            // Ativa modo exclusão
            adapter.setModoExclusao(true);
            btnExcluir.setText("Confirmar Exclusão");
            btnAddPonto.setEnabled(false);
        } else {
            // Confirma exclusão
            new AlertDialog.Builder(this)
                .setTitle("Confirmar Exclusão")
                .setMessage("Deseja realmente excluir os itens selecionados?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    adapter.excluirItensSelecionados();
                    loadPontosColeta();
                    btnExcluir.setText("Excluir");
                    btnAddPonto.setEnabled(true);
                })
                .setNegativeButton("Não", (dialog, which) -> {
                    adapter.setModoExclusao(false);
                    btnExcluir.setText("Excluir");
                    btnAddPonto.setEnabled(true);
                })
                .show();
        }
    }

    private void loadPontosColeta() {
        List<PontoColeta> pontosColeta = dbHelper.getAllPontosColeta();
        adapter.atualizarDados(pontosColeta);
    }
} 