package com.example.reciclagem;

public class PontoColeta {
    private int id;
    private String nome;
    private String endereco;

    public PontoColeta(int id, String nome, String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }
} 