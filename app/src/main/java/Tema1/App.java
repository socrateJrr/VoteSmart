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
            Alegeri alegeri = new Alegeri(numeAlegeri, idAlegeri);
            alegeri.creareAlegeri(idAlegeri,numeAlegeri);
        }
        else if(comanda == 1) {
            String idAlegeri = scanner.nextLine().trim();
            Alegeri alegeri = new Alegeri("", idAlegeri);
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
        else if(comanda == 18) {
            break;
        }
    }
    }

    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }
}