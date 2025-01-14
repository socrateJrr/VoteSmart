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
    ArrayList<String> regiuniUnice = new ArrayList<>();
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
        int okk=0;
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
                                    // adaugam intr un array de tip Analiza toate detaliile legate de analiza, castigatori, voturi
                                    Analiza anal = new Analiza();
                                    anal.numarVoturi = numar;
                                    anal.numeCircumscriptie=this.numeCircumscriptie;
                                    anal.CNPCandidat = candidat.getCNP();
                                    anal.numeCandidat = candidat.getNume();
                                    analiza.add(anal);
                                }
                                //si sortam sa vedem clasamentul
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
                    System.out.println("EROARE: Nu exista o circumscriptie cu numele "+this.numeCircumscriptie);
                    return;
                }
            }
        }
        System.out.println("EROARE: Nu exista alegeri cu acest id");
    }
    public void raportNational(String idAlegeri){
        int ok=0;
        for(Alegeri alegeri : Alegeri.getListaAlegeri()){
            if(alegeri.getIdAlegeri().equals(idAlegeri)){
                if(!alegeri.getStareAlegeri().equals("TERMINAT")) {
                    System.out.println("EROARE: Inca nu s-a terminat votarea");
                    return;
                }
                else {
                    for(Vot vot : Vot.getVotDetaliat())
                        ok=1;
                    if(ok==0){
                        System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
                        return;
                    }
                    else {
                        //facem acelasi lucru ca la circumscriptie
                        for(Candidat candidat : Candidat.getListaCandidat()){
                            int numar=0;
                            for(Vot vot : Vot.getVotDetaliat()){
                                if(candidat.getCNP().equals(vot.getCNPCandidat()))
                                    numar++;
                            }
                            Analiza anal = new Analiza();
                            anal.numarVoturi = numar;
                            anal.CNPCandidat = candidat.getCNP();
                            anal.numeCandidat = candidat.getNume();
                            analiza.add(anal);
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
                        System.out.println("Raport voturi Romania:");
                        for(Analiza analiza1 : analiza)
                        {
                            System.out.println(analiza1.getNumeCandidat()+" "+analiza1.getCNPCandidat()+" - "+analiza1.getNumarVoturi());
                        }
                        return;
                    }
                }
            }
        }
        System.out.println("EROARE: Nu exista alegeri cu acest id");
    }
    //pentru raport detaliat facem functie pentru fiecare detaliu de afisat, castigator, voturi castigator, nume, nr voturi total
    public int numarVoturiCircumscriptie(String numeCircumscriptie){
        int numar=0;
        for(Vot vot : Vot.getVotDetaliat()){
            if(vot.getNumeCircumscriptie().equals(numeCircumscriptie))
                numar++;
        }
        return numar;
    }
    public int numarVoturiRegiune(String regiune){
        int numar=0;
        for(Vot vot : Vot.getVotDetaliat()){
            if(vot.getRegiune().equals(regiune))
                numar++;
        }
        return numar;
    }
    public int numarVoturiNational(){
        int numar=0;
        for(Vot vot : Vot.getVotDetaliat()){
            numar++;
        }
        return numar;
    }
    public String numeCastigatorCircumscriptie(String numeCircumscriptie){
        analiza.clear();
        for(Candidat candidat : Candidat.getListaCandidat()){
            int numar=0;
            for(Vot vot : Vot.getVotDetaliat()){
                if(vot.getNumeCircumscriptie().equals(numeCircumscriptie))
                    if(candidat.getCNP().equals(vot.getCNPCandidat()))
                        numar++;
            }
            Analiza anal = new Analiza();
            anal.numarVoturi = numar;
            anal.CNPCandidat = candidat.getCNP();
            anal.numeCandidat = candidat.getNume();
            analiza.add(anal);
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
        return analiza.get(0).getNumeCandidat();
    }
    public String CNPCastigatorCircumscriptie(String numeCircumscriptie){
        analiza.clear();
        for(Candidat candidat : Candidat.getListaCandidat()){
            int numar=0;
            for(Vot vot : Vot.getVotDetaliat()){
                if(vot.getNumeCircumscriptie().equals(numeCircumscriptie))
                    if(candidat.getCNP().equals(vot.getCNPCandidat()))
                        numar++;
            }
            Analiza anal = new Analiza();
            anal.numarVoturi = numar;
            anal.CNPCandidat = candidat.getCNP();
            anal.numeCandidat = candidat.getNume();
            analiza.add(anal);
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
        return analiza.get(0).getCNPCandidat();
    }
    public String numeCastigatorRegiune(String regiune){
        analiza.clear();
        for(Candidat candidat : Candidat.getListaCandidat()){
            int numar=0;
            for(Vot vot : Vot.getVotDetaliat()){
                if(vot.getRegiune().equals(regiune))
                    if(candidat.getCNP().equals(vot.getCNPCandidat()))
                        numar++;
            }
            Analiza anal = new Analiza();
            anal.numarVoturi = numar;
            anal.CNPCandidat = candidat.getCNP();
            anal.numeCandidat = candidat.getNume();
            analiza.add(anal);
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
        return analiza.get(0).getNumeCandidat();
    }
    public String CNPCastigatorRegiune(String regiune){
        analiza.clear();
        for(Candidat candidat : Candidat.getListaCandidat()){
            int numar=0;
            for(Vot vot : Vot.getVotDetaliat()){
                if(vot.getRegiune().equals(regiune))
                    if(candidat.getCNP().equals(vot.getCNPCandidat()))
                        numar++;
            }
            Analiza anal = new Analiza();
            anal.numarVoturi = numar;
            anal.CNPCandidat = candidat.getCNP();
            anal.numeCandidat = candidat.getNume();
            analiza.add(anal);
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
        return analiza.get(0).getCNPCandidat();
    }
    public int nrVoturiCastigatorCircumscriptie(String numeCircumscriptie){
        for(Candidat candidat : Candidat.getListaCandidat()){
            int numar=0;
            for(Vot vot : Vot.getVotDetaliat()){
                if(vot.getNumeCircumscriptie().equals(this.numeCircumscriptie)){
                    if(candidat.getCNP().equals(vot.getCNPCandidat()))
                        numar++;
                }
            }
            Analiza anal = new Analiza();
            anal.numarVoturi = numar;
            anal.CNPCandidat = candidat.getCNP();
            anal.numeCandidat = candidat.getNume();
            analiza.add(anal);
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
        return analiza.get(0).getNumarVoturi();
    }
    public int nrVoturiCastigatorRegiune(String regiune){
        analiza.clear();
        for(Candidat candidat : Candidat.getListaCandidat()){
            int numar=0;
            for(Vot vot : Vot.getVotDetaliat()){
                if(vot.getRegiune().equals(regiune)){
                    if(candidat.getCNP().equals(vot.getCNPCandidat()))
                        numar++;
                }
            }
            Analiza anal = new Analiza();
            anal.numarVoturi = numar;
            anal.CNPCandidat = candidat.getCNP();
            anal.numeCandidat = candidat.getNume();
            analiza.add(anal);
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
        return analiza.get(0).getNumarVoturi();
    }
    public void raportDetaliatCircumscriptie(String idAlegeri, String numeCircumscriptie){
        int ok=0;
        for(Alegeri alegeri : Alegeri.getListaAlegeri()){
            if(alegeri.getIdAlegeri().equals(idAlegeri)){
                ok=1;
            }
        }
        if(ok==0){
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        ok=0;
        for(Circumscriptie circumscriptie : Circumscriptie.getListaCircumscriptie()){
            if(circumscriptie.getNumeCircumscriptie().equals(numeCircumscriptie)){
                ok=1;
            }
        }
        if(ok==0){
            System.out.println("EROARE: Nu exista o circumscriptie cu numele "+numeCircumscriptie);
            return;
        }
        for(Alegeri alegeri : Alegeri.getListaAlegeri()) {
            if (alegeri.getIdAlegeri().equals(idAlegeri)) {
                if (!alegeri.getStareAlegeri().equals("TERMINAT")) {
                    System.out.println("EROARE: Inca nu s-a terminat votarea");
                    return;
                }
            }
        }
        ok=0;
        for(Vot vot : Vot.getVotDetaliat()){
            if(vot.getNumeCircumscriptie().equals(numeCircumscriptie)){
                ok=1;
            }
        }
        if(ok==0){
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in "+ numeCircumscriptie);
            return;
        }
        //daca ajungem aici, sunt verificate toate conditiile si afisam rezultatul functiilor
        System.out.println("In "+numeCircumscriptie+" au fost "+numarVoturiCircumscriptie(numeCircumscriptie)+" voturi din "+numarVoturiNational()+". Adica "+numarVoturiCircumscriptie(numeCircumscriptie)*100/numarVoturiNational()+"%. Cele mai multe voturi au fost stranse de "+CNPCastigatorCircumscriptie(numeCircumscriptie)+" "+numeCastigatorCircumscriptie(numeCircumscriptie)+". Acestea constituie "+nrVoturiCastigatorCircumscriptie(numeCircumscriptie)*100/numarVoturiCircumscriptie(numeCircumscriptie)+"% din voturile circumscriptiei.");
    }
    public void raportDetaliatNational(String idAlegeri){
        int ok=0;
        for(Alegeri alegeri : Alegeri.getListaAlegeri()){
            if(alegeri.getIdAlegeri().equals(idAlegeri)){
                ok=1;
            }
        }
        if(ok==0){
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        for(Alegeri alegeri : Alegeri.getListaAlegeri()) {
            if (alegeri.getIdAlegeri().equals(idAlegeri)) {
                if (!alegeri.getStareAlegeri().equals("TERMINAT")) {
                    System.out.println("EROARE: Inca nu s-a terminat votarea");
                    return;
                }
            }
        }
        ok=0;
        for(Vot vot : Vot.getVotDetaliat()){
            ok=1;
        }
        if(ok==0){
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
            return;
        }
        Collections.sort(Vot.getVotDetaliat(), new Comparator<Vot>() {
            @Override
            public int compare(Vot v1, Vot v2) {
                return v1.getRegiune().compareTo(v2.getRegiune());
            }
        });
        System.out.println("In Romania au fost "+numarVoturiNational()+" voturi.");
        //verificam sa se afiseze o singura data regiunea
        for (Vot vot : Vot.getVotDetaliat()) {
            if (!regiuniUnice.contains(vot.getRegiune())) {
                regiuniUnice.add(vot.getRegiune());
            }
        }
        //daca ajungem aici, sunt verificate toate conditiile si afisam rezultatul functiilor
        for(String reg : regiuniUnice) {
            System.out.println("In "+reg+" au fost "+numarVoturiRegiune(reg)+" voturi din "+numarVoturiNational()+". Adica "+numarVoturiRegiune(reg)*100/numarVoturiNational()+"%. Cele mai multe voturi au fost stranse de "+CNPCastigatorRegiune(reg)+" "+numeCastigatorRegiune(reg)+". Acestea constituie "+nrVoturiCastigatorRegiune(reg)*100/numarVoturiRegiune(reg)+"% din voturile regiunii.");
        }
    }
}