package Tema1;

import java.util.ArrayList;

public class Vot {
    public Vot(){
    }
    public static ArrayList<Persoana> vot = new ArrayList<>();
    public static ArrayList<Persoana> fraudaVotMultiplu = new ArrayList<>();
    public static ArrayList<Persoana> fraudaCircum = new ArrayList<>();
    public void votare(String idAlegeri, String numeCircumscriptie, String CNPVotant, String CNPCandidat){
        for(Alegeri alegeri : Alegeri.getListaAlegeri()){
            if(alegeri.getIdAlegeri().equals(idAlegeri)){
                if(alegeri.getStareAlegeri().equals("NEINCEPUT")){
                    System.out.println("EROARE: Nu este perioada de votare");
                    return;
                } else {
                    for(Circumscriptie circumscriptie : Circumscriptie.getListaCircumscriptie()){
                        if(circumscriptie.getNumeCircumscriptie().equals(numeCircumscriptie)){
                            for(Candidat candidat : Candidat.getListaCandidat()) {
                                if (candidat.getCNP().equals(CNPCandidat)) {
                                    for (Votant votant : Votant.getListaVotant()) {
                                        if (votant.getCNP().equals(CNPVotant)) {
                                            if (votant.getNumeCirc().equals(numeCircumscriptie)) {
                                                for (Persoana persoana : vot) {
                                                    if (persoana.getCNP().equals(CNPVotant)) {
                                                        fraudaVotMultiplu.add(votant);
                                                        System.out.println("FRAUDA: Votantul cu CNP-ul " + CNPVotant + " a incercat sa comita o frauda. Votul a fost anulat");
                                                        return;
                                                    }
                                                }
                                                if(votant.getNeindemanatic().equals("nu"))
                                                    vot.add(votant);
                                                System.out.println(votant.getNume() + " a votat pentru " + candidat.getNume());
                                                return;
                                            } else {
                                                fraudaCircum.add(votant);
                                                System.out.println("FRAUDA: Votantul cu CNP-ul " + CNPVotant + " a incercat sa comita o frauda. Votul a fost anulat");
                                                return;
                                            }
                                        }
                                    }
                                    System.out.println("EROARE: Nu exista un votant cu CNP-ul " + CNPVotant);
                                    return;
                                }
                            }
                            System.out.println("EROARE: Nu exista un candidat cu CNP-ul "+CNPCandidat);
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
