package domaine;

import exceptions.ConflitDeCouleurException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PetitsChevaux {
    private static Partie partie;
    private static boolean jouer, fermerProgramme;

    public PetitsChevaux() {}

    public static void main(String[] args) throws InterruptedException, ConflitDeCouleurException {
        PetitsChevaux petitschevaux = new PetitsChevaux();

        while (!fermerProgramme) {
            if (!jouer)
                petitschevaux.afficherMenu();
            else
                for (Joueur joueur : partie.getJoueurs()) {
                    partie.getPlateau().afficher();
                    partie.setJoueurCourant(joueur);
                    partie.jouerUnTour();
                }
        }
    }

    public void afficherMenu() throws InterruptedException {
        byte saisie;
        PetitsChevaux.clear();
        System.out.println(
                "\n[Projet 1A] PETITS CHEVAUX \n" +
                "Par : CHAVAS Nathan & LECHASLES Quentin\n\n" +
                "Choisissez votre mode de jeu :\n  " +
                "[1] Nouvelle partie\n  " +
                "[2] Continuer\n  " +
                "[3] Options\n  " +
                "[4] Quitter \n\n" +
                "Mode [entrez une valeur]: ");

        saisie = this.choixMenu((byte)1, (byte)4);

        switch (saisie)
        {
            case 1 :
                partie = new Partie();
                jouer = true;
                break;
            case 2 :
                System.out.println("INDISPONIBLE");
                break;
            case 3 :
                this.afficherOption();
                break;
            default:
                fermerProgramme = true;
        }
    }

    public void afficherOption() throws InterruptedException {
        byte saisie;
        PetitsChevaux.clear();
        System.out.print(
                "\n[OPTIONS] A quelle option souhaitez-vous accéder ?\n  " +
                        "[1] Scénarios\n  " +
                        "[2] Plus d'infos\n  " +
                        "[3] RETOUR\n\n" +
                        "Option [entrez une valeur]: ");

        saisie = this.choixMenu((byte)1, (byte)3);

        switch (saisie)
        {
            case 1 :
                this.afficherScenarios();
                break;
            case 2 :
                PetitsChevaux.clear();
                System.out.println("\n[Projet 1A] PETITS CHEVAUX \n--------------------------\n\nPar : CHAVAS Nathan & LECHASLES Quentin\n\nEtablissement : IUT Caen\n\nDate : 2018-2019\n\nClasse : TD2.1");
                Thread.sleep(3000);
                break;
            default:
                this.afficherMenu();
                break;
        }
    }

    public void afficherScenarios() throws InterruptedException {
        byte saisie;

        PetitsChevaux.clear();
        System.out.print(
                "\n[SCENARIOS] A quel scenario souhaitez-vous acceder ?\n  " +
                        "[1] ..\n  " +
                        "[2] ..\n  " +
                        "[3] ..\n  " +
                        "[4] Retour\n\n" +
                        "Scenario [entrez une valeur]: ");

        saisie = this.choixMenu((byte)1, (byte)4);

        switch (saisie)
        {
            case 1 :
                partie.chargerPartie("scenario1.txt");
                break;
            case 2 :
                partie.chargerPartie("scenario2.txt");
                break;
            case 3 :
                partie.chargerPartie("scenario3.txt");
                break;
            default :
                this.afficherOption();
        }
    }

    public byte choixMenu(byte min, byte max) {
        boolean continuer;
        byte saisie = -1;
        Scanner scan = new Scanner(System.in);

        do {
            try {
                continuer = false;
                saisie = scan.nextByte();
            } catch (InputMismatchException e) {
                continuer = true;
                scan.nextLine();
            }
        } while (saisie < min || saisie > max || continuer);

        return saisie;
    }

    public static void clear() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
