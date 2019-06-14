package com.example.ilhacisneclube.model;


public class Time {

    private String quantidadeVitoria;
    private String nomeTime;

    public Time() {

    }

    public Time(String nomeTime, String quantidadeVitoria) {
        this.nomeTime = nomeTime;
        this.quantidadeVitoria = quantidadeVitoria;
    }

    public String getNomeTime() {
        return nomeTime;
    }

    public void setNomeTime(String nomeTime) {
        this.nomeTime = nomeTime;
    }

    public String getQuantidadeVitoria() {
        return quantidadeVitoria;
    }

    public void setQuantidadeVitoria(String quantidadeVitoria) {
        this.quantidadeVitoria = quantidadeVitoria;
    }

}