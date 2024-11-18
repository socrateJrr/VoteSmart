package Tema1;

public class Persoana {
    private String nume;
    private String CNP;
    private int varsta;
    public Persoana() {
    }
    public Persoana(String nume, String CNP, int varsta) {
        this.nume = nume;
        this.CNP = CNP;
        this.varsta = varsta;
    }
    public String getNume() {
        return nume;
    }
    public String getCNP() {
        return CNP;
    }
    public int getVarsta() {
        return varsta;
    }
}
