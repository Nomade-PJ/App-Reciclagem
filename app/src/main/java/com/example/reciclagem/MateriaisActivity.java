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

public class MateriaisActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btnAddMaterial;
    private MateriaisAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materiais);

        dbHelper = new DatabaseHelper(this);
        
        recyclerView = findViewById(R.id.recyclerView);
        btnAddMaterial = findViewById(R.id.btnAddMaterial);
        
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MateriaisAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        btnAddMaterial.setOnClickListener(v -> showAddMaterialDialog());

        loadMateriais();
    }

    private void showAddMaterialDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_material, null);
        
        EditText editTextNome = dialogView.findViewById(R.id.editTextNome);
        EditText editTextDescricao = dialogView.findViewById(R.id.editTextDescricao);

        builder.setView(dialogView)
                .setTitle("Adicionar Material")
                .setPositiveButton("Adicionar", (dialog, id) -> {
                    String nome = editTextNome.getText().toString();
                    String descricao = editTextDescricao.getText().toString();
                    
                    if (!nome.isEmpty()) {
                        dbHelper.addMaterial(nome, descricao);
                        loadMateriais();
                    }
                })
                .setNegativeButton("Cancelar", null);

        builder.create().show();
    }

    private void loadMateriais() {
        List<Material> materiais = dbHelper.getAllMateriais();
        adapter.atualizarDados(materiais);
    }
} 