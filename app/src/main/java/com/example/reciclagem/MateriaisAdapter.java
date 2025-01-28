package com.example.reciclagem;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MateriaisAdapter extends RecyclerView.Adapter<MateriaisAdapter.ViewHolder> {
    private List<Material> materiais;
    private DatabaseHelper dbHelper;
    public boolean modoExclusao = false;
    private Set<Integer> itensSelecionados = new HashSet<>();

    public MateriaisAdapter(List<Material> materiais, DatabaseHelper dbHelper) {
        this.materiais = materiais;
        this.dbHelper = dbHelper;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_material, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Material material = materiais.get(position);
        holder.textNome.setText(material.getNome());
        holder.textDescricao.setText(material.getDescricao());

        // Controla visibilidade do checkbox e botão editar
        holder.checkBox.setVisibility(modoExclusao ? View.VISIBLE : View.GONE);
        holder.btnEditar.setVisibility(modoExclusao ? View.GONE : View.VISIBLE);

        // Configura estado do checkbox
        holder.checkBox.setChecked(itensSelecionados.contains(position));
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                itensSelecionados.add(position);
            } else {
                itensSelecionados.remove(position);
            }
        });

        holder.btnEditar.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            View dialogView = LayoutInflater.from(v.getContext())
                    .inflate(R.layout.dialog_add_material, null);

            EditText editTextNome = dialogView.findViewById(R.id.editTextNome);
            EditText editTextDescricao = dialogView.findViewById(R.id.editTextDescricao);

            editTextNome.setText(material.getNome());
            editTextDescricao.setText(material.getDescricao());

            builder.setView(dialogView)
                    .setTitle("Editar Material")
                    .setPositiveButton("Salvar", (dialog, id) -> {
                        String novoNome = editTextNome.getText().toString();
                        String novaDescricao = editTextDescricao.getText().toString();
                        
                        if (!novoNome.isEmpty()) {
                            dbHelper.atualizarMaterial(material.getId(), novoNome, novaDescricao);
                            material.setNome(novoNome);
                            material.setDescricao(novaDescricao);
                            notifyItemChanged(position);
                        }
                    })
                    .setNegativeButton("Cancelar", null);

            builder.create().show();
        });
    }

    @Override
    public int getItemCount() {
        return materiais.size();
    }

    public void setModoExclusao(boolean ativo) {
        this.modoExclusao = ativo;
        if (!ativo) {
            itensSelecionados.clear();
        }
        notifyDataSetChanged();
    }

    public void excluirItensSelecionados() {
        List<Material> itensParaExcluir = new ArrayList<>();
        for (Integer position : itensSelecionados) {
            if (position < materiais.size()) {
                itensParaExcluir.add(materiais.get(position));
            }
        }
        
        for (Material material : itensParaExcluir) {
            dbHelper.deletarMaterial(material.getId());
        }
        
        itensSelecionados.clear();
        modoExclusao = false;
    }

    public void atualizarDados(List<Material> novosMateriais) {
        this.materiais = novosMateriais;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textNome;
        public TextView textDescricao;
        public Button btnEditar;
        public CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);
            textNome = view.findViewById(R.id.textNome);
            textDescricao = view.findViewById(R.id.textDescricao);
            btnEditar = view.findViewById(R.id.btnEditar);
            checkBox = view.findViewById(R.id.checkBox);
        }
    }
} 