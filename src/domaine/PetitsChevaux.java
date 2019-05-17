package domaine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PetitsChevaux {
    private boolean continuerProgramme;
    public PetitsChevaux() {
        this.continuerProgramme = true;
    }

    public static void main(String[] args) {
        PetitsChevaux petitschevaux = new PetitsChevaux();
        while (petitschevaux.continuerProgramme) {
            petitschevaux.afficherMenu();
        }
        Plateau plateau = new Plateau();
        plateau.afficher();

    }

    public void afficherMenu() {
        boolean continuer;
        byte saisie = -1;
        Scanner scan = new Scanner(System.in);

        System.out.println(
                "\n[Projet 1A] PETITS CHEVAUX \n" +
                "Par : CHAVAS Nathan & LECHASLES Quentin\n\n" +
                "Choisissez votre mode de jeu :\n  " +
                "[1] Nouvelle partie\n  " +
                "[2] Continuer\n  " +
                "[3] Options\n  " +
                "[4] Quitter \n\n" +
                "Mode [entrez une valeur]: ");

        do {
            try {
                continuer = false;
                saisie = scan.nextByte();
            } catch (InputMismatchException e) {
                continuer = true;
                scan.nextLine();
            }
        } while (saisie < 1 || saisie > 4 || continuer);

        switch (saisie)
        {
            case 1 :
                Partie partie = new Partie();
                break;
            case 2 :
                System.out.println("INDISPONIBLE");
                break;
            case 3 :
                this.afficherOption();
                break;
            default:
                this.continuerProgramme = false;
                break;
        }
    }

    public void afficherOption() {
        byte saisie = -1;
        boolean continuer;
        Scanner scan = new Scanner(System.in);
        System.out.print(
                "\n[OPTIONS] A quelle option souhaitez-vous accéder ?\n  " +
                        "[1] Scénarios\n  " +
                        "[2] Plus d'infos\n  " +
                        "[3] RETOUR\n\n" +
                        "Option [entrez une valeur]: ");
        do {
            try {
                continuer = false;
                saisie = scan.nextByte();
            } catch (InputMismatchException e) {
                continuer = true;
                scan.nextLine();
            }
        } while (saisie < 1 || saisie > 3 || continuer);

        switch (saisie)
        {
            case 1 :
                System.out.println("INDISPONIBLE");
                break;
            case 2 :
                System.out.println("\n[Projet 1A] PETITS CHEVAUX \n--------------------------\n\nPar : CHAVAS Nathan & LECHASLES Quentin\n\nEtablissement : IUT Caen\n\nDate : 2018-2019\n\nClasse : TD2.1");
                break;
        }
    }
}
