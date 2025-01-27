package com.example.reciclagem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PontosColetaAdapter extends RecyclerView.Adapter<PontosColetaAdapter.ViewHolder> {
    private List<PontoColeta> pontosColeta;

    public PontosColetaAdapter(List<PontoColeta> pontosColeta) {
        this.pontosColeta = pontosColeta;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PontoColeta ponto = pontosColeta.get(position);
        holder.text1.setText(ponto.getNome());
        holder.text2.setText(ponto.getEndereco());
    }

    @Override
    public int getItemCount() {
        return pontosColeta.size();
    }

    public void atualizarDados(List<PontoColeta> novosPontos) {
        this.pontosColeta = novosPontos;
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