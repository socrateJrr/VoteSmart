package Tema1;

import java.util.ArrayList;

public class Votant extends Persoana{
    private String nume;
    private String CNP;
    private String neindemanatic;
    private int varsta;
    public static ArrayList<Votant> listaVotant = new ArrayList<>();
    public Votant(){
    }
    public void adaugareVotant(String idAlegeri, String numeCircumscriptie, String CNP, int varsta, String neindemanatic, String nume){
        this.nume = nume;
        this.CNP = CNP;
        this.varsta = varsta;
        this.neindemanatic = neindemanatic;

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
}
