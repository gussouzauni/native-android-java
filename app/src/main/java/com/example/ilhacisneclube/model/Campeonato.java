package com.example.ilhacisneclube.model;

public class Campeonato {
    private String nomeCampeonato;
    private String descricaoCampeonato;
    private String modalidadeCampeonato;
    private String quantidadeTimes;
    private String liderCampeonato;


    public Campeonato () {

    }

    public Campeonato(String nomeCampeonato, String descricaoCampeonato, String modalidadeCampeonato, String quantidadeTimes, String liderCampeonato) {
        this.nomeCampeonato = nomeCampeonato;
        this.descricaoCampeonato = descricaoCampeonato;
        this.modalidadeCampeonato = modalidadeCampeonato;
        this.quantidadeTimes = quantidadeTimes;
        this.liderCampeonato = liderCampeonato;
    }


    public String getNomeCampeonato() {
        return nomeCampeonato;
    }

    public void setNomeCampeonato(String nomeCampeonato) {
        this.nomeCampeonato = nomeCampeonato;
    }

    public String getDescricaoCampeonato() {
        return descricaoCampeonato;
    }

    public void setDescricaoCampeonato(String descricaoCampeonato) {
        this.descricaoCampeonato = descricaoCampeonato;
    }

    public String getModalidadeCampeonato() {
        return modalidadeCampeonato;
    }

    public void setModalidadeCampeonato(String modalidadeCampeonato) {
        this.modalidadeCampeonato = modalidadeCampeonato;
    }

    public String getQuantidadeTimes() {
        return quantidadeTimes;
    }

    public void setQuantidadeTimes(String quantidadeTimes) {
        this.quantidadeTimes = quantidadeTimes;
    }

    public String getLiderCampeonato() {
        return liderCampeonato;
    }

    public void setLiderCampeonato(String liderCampeonato) {
        this.liderCampeonato = liderCampeonato;
    }
}
