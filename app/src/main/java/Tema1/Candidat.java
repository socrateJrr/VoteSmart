package Tema1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    public static ArrayList<Candidat> getListaCandidat() {
        return listaCandidat;
    }

    public void adaugareCandidat(String idAlegeri, String CNP, int varsta, String nume) {
        this.nume = nume;
        this.varsta = varsta;
        this.CNP = CNP;
        if(this.CNP.length() != 13) {
            System.out.println("EROARE: CNP invalid");
            return;
        }
        if(this.varsta < 35) {
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
                        if(candidat.CNP.equals(CNP)){
                            System.out.println("EROARE: Candidatul "+this.nume+" are deja acelasi CNP");
                            return;
                        }
                    }
                    listaCandidat.add(this);
                    System.out.println("S-a adaugat candidatul "+this.nume);
                    return;
                }
            }
        }
       System.out.println("EROARE: Nu exista alegeri cu acest id");
    }
    public void eliminareCandidat(String idAlegeri, String CNP) {
        this.CNP = CNP;
        for(Alegeri alegeri : Alegeri.getListaAlegeri()){
            if(alegeri.getIdAlegeri().equals(idAlegeri)){
                if(alegeri.getStareAlegeri().equals("NEINCEPUT")){
                    System.out.println("EROARE: Nu este perioada de votare");
                    return;
                }
                else{
                    for(Candidat candidat : listaCandidat){
                        if(candidat.CNP.equals(CNP)){
                            listaCandidat.remove(this);
                            System.out.println("S-a sters candidatul "+ candidat.getNume());
                            return;
                        }
                    }
                    System.out.println("EROARE: Nu exista un candidat cu CNP-ul "+ this.CNP);
                    return;
                }
            }
        }
        System.out.println("EROARE: Nu exista alegeri cu acest id");
    }
    public void listareCandidati(String idAlegeri) {
        for (Alegeri alegeri : Alegeri.getListaAlegeri()) {
            if (alegeri.getIdAlegeri().equals(idAlegeri)) {
                if (alegeri.getStareAlegeri().equals("NEINCEPUT")) {
                    System.out.println("EROARE: Nu este perioada de votare");
                    return;
                } else {
                    int nr = 0;
                    for (Candidat candidat : listaCandidat)
                        nr++;
                    if (nr == 0) {
                        System.out.println("GOL: Nu sunt candidati");
                        return;
                    }
                    else
                    {
                        Collections.sort(listaCandidat, new Comparator<Candidat>() {
                            @Override
                            public int compare(Candidat o1, Candidat o2) {
                                return o1.getCNP().compareTo(o2.getCNP());
                            }
                        });
                        System.out.println("Candidatii:");
                        for (Candidat candidat : listaCandidat) {
                            System.out.println(candidat.getNume()+" "+candidat.getCNP()+" "+candidat.getVarsta());
                        }
                        return;
                    }
                }
            }
        }
        System.out.println("EROARE: Nu exista alegeri cu acest id");
    }
}