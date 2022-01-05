package model;

import java.io.Serializable;

public class Mitglied implements Serializable {
    private static final long serialVersionUID = -8675754595661794167L;

    private int id;
    private String vorname;
    private String nachname;
    private String adresse;
    private String geburtsdatum;
    private Verein verein;

    public Mitglied() {
        this.vorname = "";
        this.nachname = "";
        this.adresse = "";
        this.geburtsdatum = "";
        this.verein = new Verein();
    }

    public Mitglied(int id, String vorname, String nachname, String adresse, String geburtsdatum, Verein verein) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
        this.geburtsdatum = geburtsdatum;
        this.verein = verein;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public Verein getVerein() {
        return verein;
    }

    public void setVerein(Verein verein) {
        this.verein = verein;
    }
}

