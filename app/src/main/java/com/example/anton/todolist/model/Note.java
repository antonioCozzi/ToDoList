package com.example.anton.todolist.model;

/**
 * Created by anton on 20/02/2017.
 */

public class Note {
    private String titolo;
    private String dataCreazione;
    private String ultimaModifica;
    private String corpo;
    private String dataScadenza;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    /*public Note(String titolo, String dataCreazione, String ultimaModifica, String corpo, String dataScadenza) {
        this.titolo = titolo;
        this.dataCreazione = dataCreazione;
        this.ultimaModifica = ultimaModifica;
        this.corpo = corpo;
        this.dataScadenza = dataScadenza;
    }*/

    public String getTitolo() {
        return titolo;
    }

    public String getDataCreazione() {
        return dataCreazione;
    }

    public String getUltimaModifica() {
        return ultimaModifica;
    }

    public String getCorpo() {
        return corpo;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setDataCreazione(String dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public void setUltimaModifica(String ultimaModifica) {
        this.ultimaModifica = ultimaModifica;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }
}
