package org.example.practika.DepartamentHR.Entity;


public class    Angajat {
    private String nume;
    private String prenume;
    private String idnp;
    private String adresa;
    private String telefon;
    private String functie;
    private String companie;
    private String subdiviziunea;


    public Angajat(String nume, String prenume, String idnp, String adresa, String telefon,
                   String functie, String companie, String subdiviziunea) {
        this.nume = nume;
        this.prenume = prenume;
        this.idnp = idnp;
        this.adresa = adresa;
        this.telefon = telefon;
        this.functie = functie;
        this.companie = companie;
        this.subdiviziunea = subdiviziunea;
    }

    public String getNume() {
        return nume;
    }


    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }



    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }



    public String getIdnp() {
        return idnp;
    }


    public void setIdnp(String idnp) {
        this.idnp = idnp;
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


    public String getFunctie() {
        return functie;
    }


    public void setFunctie(String functie) {
        this.functie = functie;
    }



    public String getCompanie() {
        return companie;
    }


    public void setCompanie(String companie) {
        this.companie = companie;
    }


    public String getSubdiviziunea() {
        return subdiviziunea;
    }



    public void setSubdiviziunea(String subdiviziunea) {
        this.subdiviziunea = subdiviziunea;
    }


    @Override
    public String toString() {
        return String.format("Nume: %s, Prenume: %s, IDNP: %s, Adresa: %s, Telefon: %s, Functie: %s, Companie: %s, Subdiviziune: %s",
                nume, prenume, idnp, adresa, telefon, functie, companie, subdiviziunea);
    }
}

