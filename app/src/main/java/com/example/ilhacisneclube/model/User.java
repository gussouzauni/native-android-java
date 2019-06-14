package com.example.ilhacisneclube.model;

public class User {
    private String nome, email, sobrenome;

    public User() {

    }

    public User(String nome, String email, String sobrenome) {
        this.nome = nome;
        this.email = email;
        this.sobrenome = sobrenome;


    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
}
