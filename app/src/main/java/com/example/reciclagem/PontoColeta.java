package com.example.reciclagem;

public class PontoColeta {
    private int id;
    private String nome;
    private String endereco;
    private String materiais;

    public PontoColeta(int id, String nome, String endereco, String materiais) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.materiais = materiais;
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

    public String getMateriais() {
        return materiais;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setMateriais(String materiais) {
        this.materiais = materiais;
    }
} 