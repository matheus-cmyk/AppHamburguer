package com.example.apphamburgueria;

public class Produto {
    private String nome;
    private int imagem;

    public Produto(String nome, int imagem) {
        this.nome = nome;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public int getImagem() {
        return imagem;
    }
}

