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

public class PontosColetaAdapter extends RecyclerView.Adapter<PontosColetaAdapter.ViewHolder> {
    private List<PontoColeta> pontosColeta;
    private DatabaseHelper dbHelper;
    public boolean modoExclusao = false;
    private Set<Integer> itensSelecionados = new HashSet<>();

    public PontosColetaAdapter(List<PontoColeta> pontosColeta, DatabaseHelper dbHelper) {
        this.pontosColeta = pontosColeta;
        this.dbHelper = dbHelper;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ponto_coleta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PontoColeta ponto = pontosColeta.get(position);
        holder.textNome.setText(ponto.getNome());
        holder.textEndereco.setText(ponto.getEndereco());
        holder.textMateriais.setText("Materiais aceitos: " + ponto.getMateriais());

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
                    .inflate(R.layout.dialog_add_ponto, null);

            EditText editTextNome = dialogView.findViewById(R.id.editTextNome);
            EditText editTextEndereco = dialogView.findViewById(R.id.editTextEndereco);
            EditText editTextMateriais = dialogView.findViewById(R.id.editTextMateriais);

            editTextNome.setText(ponto.getNome());
            editTextEndereco.setText(ponto.getEndereco());
            editTextMateriais.setText(ponto.getMateriais());

            builder.setView(dialogView)
                    .setTitle("Editar Ponto de Coleta")
                    .setPositiveButton("Salvar", (dialog, id) -> {
                        String novoNome = editTextNome.getText().toString();
                        String novoEndereco = editTextEndereco.getText().toString();
                        String novosMateriais = editTextMateriais.getText().toString();
                        
                        if (!novoNome.isEmpty() && !novoEndereco.isEmpty()) {
                            dbHelper.atualizarPontoColeta(ponto.getId(), novoNome, novoEndereco, novosMateriais);
                            ponto.setNome(novoNome);
                            ponto.setEndereco(novoEndereco);
                            ponto.setMateriais(novosMateriais);
                            notifyItemChanged(position);
                        }
                    })
                    .setNegativeButton("Cancelar", null);

            builder.create().show();
        });
    }

    @Override
    public int getItemCount() {
        return pontosColeta.size();
    }

    public void setModoExclusao(boolean ativo) {
        this.modoExclusao = ativo;
        if (!ativo) {
            itensSelecionados.clear();
        }
        notifyDataSetChanged();
    }

    public void excluirItensSelecionados() {
        List<PontoColeta> itensParaExcluir = new ArrayList<>();
        for (Integer position : itensSelecionados) {
            if (position < pontosColeta.size()) {
                itensParaExcluir.add(pontosColeta.get(position));
            }
        }
        
        for (PontoColeta ponto : itensParaExcluir) {
            dbHelper.deletarPontoColeta(ponto.getId());
        }
        
        itensSelecionados.clear();
        modoExclusao = false;
    }

    public void atualizarDados(List<PontoColeta> novosPontos) {
        this.pontosColeta = novosPontos;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textNome;
        public TextView textEndereco;
        public TextView textMateriais;
        public Button btnEditar;
        public CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);
            textNome = view.findViewById(R.id.textNome);
            textEndereco = view.findViewById(R.id.textEndereco);
            textMateriais = view.findViewById(R.id.textMateriais);
            btnEditar = view.findViewById(R.id.btnEditar);
            checkBox = view.findViewById(R.id.checkBox);
        }
    }
} 