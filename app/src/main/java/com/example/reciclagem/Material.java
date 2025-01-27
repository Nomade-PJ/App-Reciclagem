package com.example.reciclagem;

public class Material {
    private int id;
    private String nome;
    private String descricao;

    public Material(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
} 