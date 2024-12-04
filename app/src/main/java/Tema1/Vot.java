package Tema1;

import java.util.ArrayList;

public class Vot {
    private String CNPCandidat;
    private String CNPVotant;
    private String numeCircumscriptie;
    private String numeCandidat;
    private String regiune;
    public Vot(){
    }
    public static ArrayList<Votant> vot = new ArrayList<>();
    public static ArrayList<Vot> votDetaliat  = new ArrayList<>();
    public static ArrayList<Votant> frauda = new ArrayList<>();
    public static ArrayList<Votant> getVot() {
        return vot;
    }
    public static ArrayList<Vot> getVotDetaliat() {
        return votDetaliat;
    }
    public static ArrayList<Votant> getFrauda() {
        return frauda;
    }
    public String getCNPCandidat() {
        return CNPCandidat;
    }
    public String getCNPVotant() {
        return CNPVotant;
    }
    public String getNumeCircumscriptie() {
        return numeCircumscriptie;
    }
    public String getNumeCandidat(){
        return numeCandidat;
    }
    public String getRegiune() {
        return regiune;
    }
    public void votare(String idAlegeri, String numeCircumscriptie, String CNPVotant, String CNPCandidat){
        this.numeCircumscriptie = numeCircumscriptie;
        this.CNPVotant = CNPVotant;
        this.CNPCandidat = CNPCandidat;
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
                                                for (Votant persoana : vot) {
                                                    if (persoana.getCNP().equals(CNPVotant)) {
                                                        frauda.add(votant);
                                                        System.out.println("FRAUDA: Votantul cu CNP-ul " + CNPVotant + " a incercat sa comita o frauda. Votul a fost anulat");
                                                        return;
                                                    }
                                                }
                                                if(votant.getNeindemanatic().equals("da")){
                                                    vot.add(votant);
                                                    this.numeCandidat=candidat.getNume();
                                                    this.regiune=circumscriptie.getRegiune();
                                                    votDetaliat.add(this);
                                                }
                                                System.out.println(votant.getNume() + " a votat pentru " + candidat.getNume());
                                                return;
                                            } else {
                                                frauda.add(votant);
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
