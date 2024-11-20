package Tema1;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.text.*;

public class App {
    private Scanner scanner;

    public App(InputStream input) {
        this.scanner = new Scanner(input);
    }

    public void run() {
    while(true) {
        int comanda = scanner.nextInt();
        scanner.nextLine();
        if(comanda == 0) {
            String idAlegeri = scanner.next();
            String numeAlegeri = scanner.nextLine().trim();
            Alegeri alegeri = new Alegeri();
            alegeri.creareAlegeri(idAlegeri,numeAlegeri);
        }
        else if(comanda == 1) {
            String idAlegeri = scanner.next();
            Alegeri alegeri = new Alegeri();
            alegeri.pornireAlegeri(idAlegeri);
        }
        else if(comanda == 2) {
            String idAlegeri = scanner.next();
            String numeCircumscrptie = scanner.next();
            String regiune = scanner.next();
            Circumscriptie circumscriptie = new Circumscriptie();
            circumscriptie.adaugareCircumscriptie(idAlegeri, numeCircumscrptie, regiune);
        }
        else if(comanda == 3) {
            String idAlegeri = scanner.next();
            String numeCircumscrptie = scanner.next();
            Circumscriptie circumscriptie = new Circumscriptie();
            circumscriptie.eliminareCircumscriptie(idAlegeri, numeCircumscrptie);
        }
        else if(comanda == 4) {
            String idAlegeri = scanner.next();
            String CNP = scanner.next();
            int varsta = scanner.nextInt();
            String nume = scanner.nextLine().trim();
            Candidat candidat = new Candidat();
            candidat.adaugareCandidat(idAlegeri,CNP,varsta,nume);
        }
        else if(comanda == 5) {
            String idAlegeri = scanner.next();
            String CNP = scanner.next();
            Candidat candidat = new Candidat();
            candidat.eliminareCandidat(idAlegeri,CNP);
        }
        else if(comanda == 6) {
            String idAlegeri = scanner.next();
            String numeCircumscrptie = scanner.next();
            String CNP = scanner.next();
            int varsta = scanner.nextInt();
            String neindemanatic = scanner.next();
            String nume = scanner.nextLine().trim();
            Votant votant = new Votant();
            votant.adaugareVotant(idAlegeri, numeCircumscrptie, CNP, varsta, neindemanatic,nume);
        }
        else if(comanda == 7) {
            String idAlegeri = scanner.next();
            Candidat candidat = new Candidat();
            candidat.listareCandidati(idAlegeri);
        }
        else if(comanda == 8) {
            String idAlegeri = scanner.next();
            String numeCircumscrptie = scanner.next();
            Votant votant = new Votant();
            votant.listareVotanti(idAlegeri, numeCircumscrptie);
        }
        else if(comanda == 9){
            String idAlegeri = scanner.next();
            String numeCircumscrptie = scanner.next();
            String CNPVotant = scanner.next();
            String CNPCandidat = scanner.next();
            Vot vot = new Vot();
            vot.votare(idAlegeri,numeCircumscrptie,CNPVotant,CNPCandidat);
        }
        else if(comanda == 10) {
            String idAlegeri = scanner.next();
            Alegeri alegeri = new Alegeri();
            alegeri.oprireAlegeri(idAlegeri);
        }
        else if(comanda == 11) {
            String idAlegeri = scanner.next();
            String numeCircumscrptie = scanner.next();
            Circumscriptie circumscriptie = new Circumscriptie();
            circumscriptie.raportCircumscriptie(idAlegeri, numeCircumscrptie);
        }
        else if(comanda == 16){
            String idAlegeri = scanner.next();
            Alegeri alegeri = new Alegeri();
            alegeri.stergeAlegeri(idAlegeri);
        }
        else if(comanda == 17){
            Alegeri alegeri = new Alegeri();
            alegeri.listareAlegeri();
        }
        else if(comanda == 18) {
            return;
        }
    }
    }

    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }
}