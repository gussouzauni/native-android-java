package com.example.ilhacisneclube.model;

public class Feed {
    private String nomeTitulo;
    private String nomeAutor;
    private String descricao;
    private String data;
    private String imagemFeed;
    private int  nota;

    public Feed() {
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getNomeTitulo() {
        return nomeTitulo;
    }

    public void setNomeTitulo(String nomeTitulo) {
        this.nomeTitulo = nomeTitulo;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getImagemFeed() {
        return imagemFeed;
    }

    public void setImagemFeed(String imagemFeed) {
        this.imagemFeed = imagemFeed;
    }

}
