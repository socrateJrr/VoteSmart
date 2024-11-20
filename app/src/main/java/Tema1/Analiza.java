package Tema1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Analiza {
    private String numeCircumscriptie;
    private String numeCandidat;
    private int numarVoturi;
    private String CNPCandidat;
    public static ArrayList<Analiza> analiza = new ArrayList<Analiza>();
    public Analiza() {
    }
    public String getNumeCircumscriptie() {
        return numeCircumscriptie;
    }
    public String getNumeCandidat() {
        return numeCandidat;
    }
    public int getNumarVoturi() {
        return numarVoturi;
    }
    public String getCNPCandidat() {
        return CNPCandidat;
    }

    public void raportCircumscriptie(String idAlegeri, String numeCircumscriptie) {
        int ok=0;
        this.numeCircumscriptie = numeCircumscriptie;
        for(Alegeri alegeri : Alegeri.getListaAlegeri()){
            if(alegeri.getIdAlegeri().equals(idAlegeri)){
                if(!alegeri.getStareAlegeri().equals("TERMINAT")) {
                    System.out.println("EROARE: Inca nu s-a terminat votarea");
                    return;
                }
                else {
                    for(Circumscriptie circumscriptie : Circumscriptie.getListaCircumscriptie()){
                        if(circumscriptie.getNumeCircumscriptie().equals(this.numeCircumscriptie)){
                            for(Vot vot : Vot.getVotDetaliat())
                                if(vot.getNumeCircumscriptie().equals(numeCircumscriptie))
                                    ok=1;
                            if(ok==0){
                                System.out.println("GOL: Lumea nu isi exercita dreptul de vot in "+this.numeCircumscriptie);
                                return;
                            }
                            else {
                                for(Candidat candidat : Candidat.getListaCandidat()){
                                    int numar=0;
                                    for(Vot vot : Vot.getVotDetaliat()){
                                        if(vot.getNumeCircumscriptie().equals(this.numeCircumscriptie)){
                                            if(candidat.getCNP().equals(vot.getCNPCandidat()))
                                                numar++;
                                        }
                                    }
                                    this.numarVoturi=numar;
                                    this.CNPCandidat=candidat.getCNP();
                                    this.numeCandidat=candidat.getNume();
                                    analiza.add(this);
                                }
                                Collections.sort(analiza, new Comparator<Analiza>() {
                                    @Override
                                    public int compare(Analiza a1, Analiza a2) {
                                        if (a1.numarVoturi != a2.numarVoturi) {
                                            return Integer.compare(a2.numarVoturi, a1.numarVoturi);
                                        }
                                        return a2.CNPCandidat.compareTo(a1.CNPCandidat);
                                    }
                                });
                                System.out.println("Raport voturi "+this.numeCircumscriptie+":");
                                for(Analiza analiza1 : analiza)
                                    System.out.println(analiza1.getNumeCandidat()+" "+analiza1.getCNPCandidat()+" - "+analiza1.getNumarVoturi());
                                return;
                            }
                        }
                    }
                    System.out.println("Nu exista o circumscriptie cu numele "+this.numeCircumscriptie);
                }
            }
        }
        System.out.println("EROARE: Nu exista alegeri cu acest id");
    }
}
