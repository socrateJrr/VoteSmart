package Tema1;
import java.util.ArrayList;

public class Alegeri {
    private String numeAlegeri;
    private String idAlegeri;
    private String stareAlegeri;
    public static ArrayList<Alegeri> listaAlegeri = new ArrayList<>();

    public Alegeri() {
        this.stareAlegeri = "NEINCEPUT";
    }

    public String getNumeAlegeri() {
        return numeAlegeri;
    }
    public String getIdAlegeri() {
        return idAlegeri;
    }
    public String getStareAlegeri() {
        return stareAlegeri;
    }
    public static ArrayList<Alegeri> getListaAlegeri() {
        return listaAlegeri;
    }

    public void creareAlegeri(String idAlegeri, String numeAlegeri){
        this.numeAlegeri = numeAlegeri;
        this.idAlegeri = idAlegeri;
        for(Alegeri alegeri : listaAlegeri){
            if(alegeri.idAlegeri.equals(this.idAlegeri)){
                System.out.println("EROARE: Deja exista alegeri cu id "+ this.idAlegeri);
                return;
            }
        }
        listaAlegeri.add(this);
        System.out.println("S-au creat alegerile "+ this.numeAlegeri);
    }

    public void pornireAlegeri(String idAlegeri){
        this.idAlegeri = idAlegeri;
        for(Alegeri alegeri : listaAlegeri){
            if(alegeri.idAlegeri.equals(this.idAlegeri)){
                if(alegeri.stareAlegeri.equals("NEINCEPUT")){
                    alegeri.stareAlegeri = "IN_CURS";
                    System.out.println("Au pornit alegerile "+ alegeri.numeAlegeri);
                }
                else
                    System.out.println("EROARE: Alegerile deja au inceput");
                return;
            }
        }
        System.out.println("EROARE: Nu exista alegeri cu acest id");
    }
    public void oprireAlegeri(String idAlegeri){
        this.idAlegeri = idAlegeri;
        for(Alegeri alegeri : listaAlegeri){
            if(alegeri.idAlegeri.equals(this.idAlegeri)){
                if(alegeri.stareAlegeri.equals("NEINCEPUT")){
                    System.out.println("EROARE: Nu este perioada de votare");
                    return;
                }
                else {
                    System.out.println("S-au terminat alegerile "+ alegeri.numeAlegeri);
                    return;
                }
            }
        }
        System.out.println("EROARE: Nu exista alegeri cu acest id");
    }
    public void stergeAlegeri(String idAlegeri){
        this.idAlegeri = idAlegeri;
        for(Alegeri alegeri : listaAlegeri){
            if(alegeri.idAlegeri.equals(this.idAlegeri)){
                listaAlegeri.remove(alegeri);
                System.out.println("S-au sters alegerile "+ alegeri.numeAlegeri);
                return;
            }
        }
        System.out.println("EROARE: Nu exista alegeri cu acest id");
    }
    public void listareAlegeri(){
        int ok=0;
        for(Alegeri alegeri : listaAlegeri)
            ok=1;
        if(ok==0){
            System.out.println("GOL: Nu sunt alegeri");
            return;
        }
        else
            System.out.println("Alegeri:");
        for(Alegeri alegeri : listaAlegeri)
            System.out.println(alegeri.idAlegeri+" "+alegeri.numeAlegeri);
    }
}

