[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/3CLMYIcM)
# Implementare VoteSmart
## Clasa Alegeri
#### Functia Creare 
- Cream un array list de tipul Alegeri in care ma bagam detaliile primite de alegeri
#### Functia Pornire / Oprire
- Parcurgem lista creata de alegeri si vedem daca pornim/oprim alegerile in functie de status
#### Functia Listare
- Parcurge lista si o afiseaza

## Clasa Circumscriptie
#### Functia adaugareCircumscriptie
- Parcurgem lista de alegeri si vedem daca se potrivesc alegerile, daca da, cream un array de tipul Circumscriptie care tine minte infomatiile primite pentru mai tarziu
#### Functia eliminare
- Parcurgem lista cu toate detaliile si le verificam pe rand daca corespund ca sa eliminam si conditiile

## Clasa Candidat
#### Functia adauga
- Verifiacm mai intai cnp si varsta, iar apoi cream un array list de tip candidat in care sunt puse toate dtealiile primite de la el cu tot cu circumscriptie
#### Functia eliminare si listare
- Acestea merg pe aceeasi idee unde parcurgem atat alegerile, circumscriptiile sa verificam cazurile limita si mai apoi ori afisam daca gasim, ori  stergem din array

## Clasa Votant
- Seamana cu Candidat doar ca acum este descris profilul celui care voteaza

## Clasa Vot
- Functia de votare parcurge atat array ul de alegeri, circumscriptie, candidat si votant pentru a verifica conditiile de extrema, apoi adauga votantul in votDetaliat, un array care tine minte fiecare ce a votat, unde, iar tot aici exista si array de frauda in caz ca a votat de doua ori sau unde nu trebuie asta fiind verificata din parcurgerea si array ului votDetaliat

## Clasa Analiza
#### Functia raportCircumscriptie/ raportNational
- Parcurgem din nou array ul cu alegeri, circumscriptii, vot si vom crea o alta lista de tip Analiza care contine candidatii, cate voturi au fiecare din ce regiune si asa mai departe
- Sortam ca sa aflam castigatorul cu Collections.sort
#### Functia raportDetaliatCircumscriptie/ National
- Pentru asta facem functii separate pentru nr voturi national, circumscriptie, regiune, pentru castigator pe regiune, national(sortez aici ca sa aflu castigatorul), cate voturi are si functii pentru cnp si nume
- Astfel voi parcurge ca la toate celelate, tratand exceptiile si afisand conform formatului

## Clasa Frauda
- Aici am salvat din clasa Vot oamenii care au incercat sa fraudeze si informatii despre ei, pe care le am transferat aici si am parcurs din nou ca la fiecare functie si am afisat conform formatului

## App.java
- In main doar citesc fiecare comanda si conform ei, citesc corespunzator datele si doar creez instanta a clasei respective, fara parametrii si apelez functia