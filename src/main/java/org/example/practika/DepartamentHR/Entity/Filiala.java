package org.example.practika.DepartamentHR.Entity;

public class Filiala {
    private String nume;
    private String adresa;
    private String telefon;
    private String tara;

    public Filiala(String nume, String adresa, String telefon, String tara) {
        this.nume = nume;
        this.adresa = adresa;
        this.telefon = telefon;
        this.tara = tara;
    }

    public Filiala() {
    }

    public Filiala(String nume, String adresa, String telefon) {
        this.nume = nume;
        this.adresa = adresa;
        this.telefon = telefon;
    }

    public String getTara() {
        return tara;
    }


    public void setTara(String tara) {
        this.tara = tara;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }


    public String getAdresa() {
        return adresa;
    }


    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }


    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
