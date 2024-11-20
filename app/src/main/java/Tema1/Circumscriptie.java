package Tema1;

import java.util.ArrayList;

public class Circumscriptie{
    private String numeCircumscriptie;
    private String regiune;
    public static ArrayList<Circumscriptie> listaCircumscriptie = new ArrayList<>();
    public static ArrayList<Votant> newVot = new ArrayList<>();
    public Circumscriptie() {
    }

    public String getNumeCircumscriptie() {
        return numeCircumscriptie;
    }

    public static ArrayList<Circumscriptie> getListaCircumscriptie() {
        return listaCircumscriptie;
    }

    public void adaugareCircumscriptie(String idAlegeri, String numeCircumscriptie, String regiune) {
        this.numeCircumscriptie = numeCircumscriptie;
        this.regiune = regiune;
        for (Alegeri alegeri : Alegeri.getListaAlegeri()){
            if(alegeri.getIdAlegeri().equals(idAlegeri)){
                if(alegeri.getStareAlegeri().equals("NEINCEPUT")){
                    System.out.println("EROARE: Nu este perioada de votare");
                    return;
                } else {
                    for(Circumscriptie circumscriptie : listaCircumscriptie){
                        if(circumscriptie.numeCircumscriptie.equals(numeCircumscriptie)){
                            System.out.println("EROARE: Deja exista o circumscriptie cu numele "+this.numeCircumscriptie);
                            return;
                        }
                    }
                    listaCircumscriptie.add(this);
                    System.out.println("S-a adaugat circumscriptia "+this.numeCircumscriptie);
                    return;
                }
            }
        }
        System.out.println("EROARE: Nu exista alegeri cu acest id");
    }

    public void eliminareCircumscriptie(String idAlegeri, String numeCircumscriptie) {
        this.numeCircumscriptie = numeCircumscriptie;
        for(Alegeri alegeri : Alegeri.getListaAlegeri()){
            if(alegeri.getIdAlegeri().equals(idAlegeri)){
                if(alegeri.getStareAlegeri().equals("NEINCEPUT")) {
                    System.out.println("EROARE: Nu este perioada de votare");
                    return;
                } else {
                    for(Circumscriptie circumscriptie : listaCircumscriptie){
                        if(circumscriptie.numeCircumscriptie.equals(this.numeCircumscriptie)){
                            listaCircumscriptie.remove(this);
                            System.out.println("S-a sters circumscriptia "+this.numeCircumscriptie);
                            return;
                        }
                    }
                    System.out.println("EROARE: Nu exista o circumscriptie cu numele "+this.numeCircumscriptie);
                    return;
                }
            }
        }
        System.out.println("EROARE: Nu exista alegeri cu acest id");
    }
}
