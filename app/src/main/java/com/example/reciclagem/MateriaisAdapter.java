package com.example.reciclagem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MateriaisAdapter extends RecyclerView.Adapter<MateriaisAdapter.ViewHolder> {
    private List<Material> materiais;

    public MateriaisAdapter(List<Material> materiais) {
        this.materiais = materiais;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Material material = materiais.get(position);
        holder.text1.setText(material.getNome());
        holder.text2.setText(material.getDescricao());
    }

    @Override
    public int getItemCount() {
        return materiais.size();
    }

    public void atualizarDados(List<Material> novosMateriais) {
        this.materiais = novosMateriais;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text1;
        public TextView text2;

        public ViewHolder(View view) {
            super(view);
            text1 = view.findViewById(android.R.id.text1);
            text2 = view.findViewById(android.R.id.text2);
        }
    }
} 