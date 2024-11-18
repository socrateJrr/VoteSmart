package Tema1;

import java.util.ArrayList;

public class Candidat extends Persoana{
    private String nume;
    private int varsta;
    private String CNP;
    public static ArrayList<Candidat> listaCandidat = new ArrayList<>();

    public Candidat(){
    }
    public Candidat(String nume, int varsta, String CNP) {
        super(nume, CNP, varsta);
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
    public void adaugareCandidat(String idAlegeri, String CNP, int varsta, String nume) {
        this.nume = nume;
        this.varsta = varsta;
        this.CNP = CNP;
        if(this.CNP.length() != 13)
        {
            System.out.println("EROARE: CNP invalid");
            return;
        }
        if(this.varsta < 35){
            System.out.println("EROARE: Varsta invalida");
            return;
        }
        for(Alegeri alegeri : Alegeri.getListaAlegeri()){
            if(alegeri.getIdAlegeri().equals(idAlegeri)){
                if(alegeri.getStareAlegeri().equals("NEINCEPUT"))
                {
                    System.out.println("EROARE: Nu este perioada de votare");
                    return;
                }
                else
                {
                    for(Candidat candidat : listaCandidat){
                        if(candidat.getCNP().equals(CNP)){
                            System.out.println("Candidatul "+candidat.getNume()+" are deja acelasi CNP");
                            return;
                        }
                    }
                    listaCandidat.add(this);
                    System.out.println("S-a adaugat candidatul "+nume);
                }
            }
        }
       System.out.println("Nu exista alegeri cu acest id");
    }
    public void eliminareCandidat(String idAlegeri, String CNP) {
        this.CNP = CNP;
        for(Alegeri alegeri : Alegeri.getListaAlegeri()){
            if(alegeri.getIdAlegeri().equals(idAlegeri)){
                if(alegeri.getStareAlegeri().equals("NEINCEPUT")){
                    System.out.println("EROARE: Nu este perioada de votare");
                }
                else{
                    for(Candidat candidat : listaCandidat){
                        if(candidat.getCNP().equals(CNP)){
                            listaCandidat.remove(this);
                            System.out.println("S-a sters candidatul "+nume);
                            return;
                        }
                    }
                    System.out.println("EROARE: Nu exista un candidat cu CNP-ul "+CNP);
                    return;
                }
            }
        }
        System.out.println("Nu exista alegeri cu acest id");
    }
}