package Tema1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Votant extends Persoana{
    private String nume;
    private String CNP;
    private String neindemanatic;
    private int varsta;
    private String numeCirc;
    public static ArrayList<Votant> listaVotant = new ArrayList<>();
    public Votant(){
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
    public static ArrayList<Votant> getListaVotant() {
        return listaVotant;
    }
    public String getNumeCirc() {
        return numeCirc;
    }
    public String getNeindemanatic() {
        return neindemanatic;
    }

    public void adaugareVotant(String idAlegeri, String numeCircumscriptie, String CNP, int varsta, String neindemanatic, String nume){
        this.nume = nume;
        this.CNP = CNP;
        this.varsta = varsta;
        this.neindemanatic = neindemanatic;
        this.numeCirc = numeCircumscriptie;
        //verificam conditiile
        if(this.CNP.length() != 13) {
            System.out.println("EROARE: CNP invalid");
            return;
        }
        if(this.varsta < 18) {
            System.out.println("EROARE: Varsta invalida");
            return;
        }
        for(Alegeri alegeri : Alegeri.getListaAlegeri()){
            if(alegeri.getIdAlegeri().equals(idAlegeri)) {
                if(alegeri.getStareAlegeri().equals("NEINCEPUT"))
                {
                    System.out.println("EROARE: Nu este perioada de votare");
                    return;
                }
                else {
                    for(Circumscriptie circumscriptie : Circumscriptie.getListaCircumscriptie()){
                        if(circumscriptie.getNumeCircumscriptie().equals(numeCircumscriptie)) {
                            for(Votant votant : listaVotant){
                                if(votant.CNP.equals(CNP)) {
                                        System.out.println("EROARE: Votantul "+this.nume+" are deja acelasi CNP");
                                        return;
                                    }
                             }
                            // a trecut peste toate conditiile si adaugam votantul
                            listaVotant.add(this);
                            System.out.println("S-a adaugat votantul "+this.nume);
                            return;
                        }
                    }
                    System.out.println("EROARE: Nu exista o circumscriptie cu numele "+numeCircumscriptie);
                    return;
                }
            }
        }
        System.out.println("EROARE: Nu exista alegeri cu acest id");
    }
    public void listareVotanti(String idAlegeri, String numeCircumscriptie) {
        for (Alegeri alegeri : Alegeri.getListaAlegeri()) {
            if (alegeri.getIdAlegeri().equals(idAlegeri)) {
                if (alegeri.getStareAlegeri().equals("NEINCEPUT")) {
                    System.out.println("EROARE: Nu este perioada de votare");
                    return;
                } else {
                    for(Circumscriptie circumscriptie : Circumscriptie.getListaCircumscriptie()) {
                        if(circumscriptie.getNumeCircumscriptie().equals(numeCircumscriptie)) {
                            int nr = 0;
                            for (Votant votant : listaVotant)
                                nr++;
                            if (nr == 0) {
                                System.out.println("GOL: Nu sunt votanti in "+numeCircumscriptie);
                                return;
                            } else {
                                //sortam si votantii
                                Collections.sort(listaVotant, new Comparator<Votant>() {
                                    @Override
                                    public int compare(Votant o1, Votant o2) {
                                        return o1.getCNP().compareTo(o2.getCNP());
                                    }
                                });
                                System.out.println("Votantii din "+numeCircumscriptie+":");
                                for (Votant votant : listaVotant) {
                                    System.out.println(votant.getNume() + " " + votant.getCNP() + " " + votant.getVarsta());
                                }
                                return;
                            }
                        }
                    }
                    System.out.println("EROARE: Nu exista o circumscriptie cu numele "+numeCircumscriptie);
                    return;
                }
            }
        }
        System.out.println("EROARE: Nu exista alegeri cu acest id");
    }
}
