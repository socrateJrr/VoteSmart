package Tema1;

import java.util.ArrayList;

public class Frauda {
    public Frauda(){
    }
    public void raportFrauda(String idAlegeri){
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
        for(Votant fr : Vot.getFrauda()){
            ok=1;
        }
        if(ok==0){
            System.out.println("GOL: Romanii sunt cinstiti");
            return;
        }
        System.out.println("Fraude comise:");
        ArrayList<Votant> frauda = Vot.getFrauda();
        for(int i=frauda.size()-1;i>=0;i--){
            Votant fr = frauda.get(i);
            System.out.println("In "+fr.getNumeCirc()+": "+fr.getCNP()+" "+fr.getNume());
        }
    }
}
