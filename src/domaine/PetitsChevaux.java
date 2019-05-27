package domaine;

import exceptions.ConflitDeCouleurException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe par défaut du programme. Contient le main.
 */
public class PetitsChevaux {
    private static Partie partie;
    private static boolean jouer, fermerProgramme;

    public PetitsChevaux() {}

    public static void main(String[] args) throws InterruptedException, ConflitDeCouleurException {
        PetitsChevaux petitschevaux = new PetitsChevaux();

        while (!fermerProgramme) {
            if (!jouer)
                petitschevaux.afficherMenu();
            else {
                for (Joueur joueur : partie.getJoueurs()) {
                    PetitsChevaux.clear();
                    partie.getPlateau().afficher();
                    partie.setJoueurCourant(joueur);
                    partie.jouerUnTour(true);
                    PetitsChevaux.sauvegarderPartie("last.txt");
                    Thread.sleep(3000);
                }
                if (partie.estPartieTermine())
                    jouer = false;
                else {
                    System.out.println(
                            "Voulez-vous continuer la partie ?\n  " +
                                    "[1] Oui \n  " +
                                    "[2] Non\n  " +
                                    "Continuer ? [entrez une valeur]: ");
                    if (PetitsChevaux.choixMenu((byte) 1, (byte) 2) == 2) jouer = false;
                }
            }
        }
    }

    /**
     * Méthode permettant l'affichage du menu et le choix du sous menu.
     * @throws InterruptedException Exception pour le sleep.
     */
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

        saisie = PetitsChevaux.choixMenu((byte)1, (byte)4);

        switch (saisie)
        {
            case 1 :
                partie = new Partie(false, true);
                jouer = true;
                break;
            case 2 :
                PetitsChevaux.chargerPartie("last.txt");
                jouer = true;
                break;
            case 3 :
                this.afficherOption();
                break;
            default:
                fermerProgramme = true;
        }
    }

    /**
     * Méthode permettant d'afficher le menu des options et le choix des sous-menus.
     * @throws InterruptedException Exception pour le sleep.
     */
    public void afficherOption() throws InterruptedException {
        byte saisie;
        PetitsChevaux.clear();
        System.out.print(
                "\n[OPTIONS] A quelle option souhaitez-vous accéder ?\n  " +
                        "[1] Scénarios\n  " +
                        "[2] Plus d'infos\n  " +
                        "[3] RETOUR\n\n" +
                        "Option [entrez une valeur]: ");

        saisie = PetitsChevaux.choixMenu((byte)1, (byte)3);

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

    /**
     * Méthode permettant l'affichage des différents scénarios disponibles et la sélection.
     * @throws InterruptedException Exception pour le sleep.
     */
    public void afficherScenarios() throws InterruptedException {
        byte saisie;

        PetitsChevaux.clear();
        System.out.print(
                "\n[SCENARIOS] A quel scenario souhaitez-vous acceder ?\n  " +
                        "[1] Manger un pion\n  " +
                        "[2] Monter Echelle\n  " +
                        "[3] Victoire\n  " +
                        "[4] BOT VS BOT\n  " +
                        "[5] Retour\n\n" +
                        "Scenario [entrez une valeur]: ");

        saisie = PetitsChevaux.choixMenu((byte)1, (byte)5);

        switch (saisie)
        {
            case 1 :
                partie = new Partie(true, true);
                jouer = true;
                for (byte i = 0; i < 4; i++) {
                    ArrayList<Pion> chevaux = partie.getJoueurs().get(i).getChevaux();
                    for (byte j = 0; j < 4; j++) {
                        Case caseCible = partie.getPlateau().getChemin().get(i+2);
                        partie.getPlateau().deplacerPionA(chevaux.get(j), caseCible);
                    }
                }
                break;
            case 2 :
                partie = new Partie(true, false);
                jouer = true;
                for (byte i = 0; i < 4; i++) {
                    ArrayList<Pion> chevaux = partie.getJoueurs().get(i).getChevaux();
                    for (byte j = 0; j < 4; j++) {
                        int caseDepartIndex = partie.getPlateau().getChemin().indexOf(partie.getJoueurs().get(i).getCaseDeDepart());
                        Case caseCible = partie.getPlateau().getChemin().get(caseDepartIndex-1);
                        partie.getPlateau().deplacerPionA(chevaux.get(j), caseCible);
                    }
                }
                break;
            case 3 :
                partie = new Partie(true, true);
                jouer = true;
                int indexCase;
                for (byte i = 0; i < 4; i++) {
                    indexCase = 3;
                    ArrayList<Pion> chevaux = partie.getJoueurs().get(i).getChevaux();
                    for (byte j = 0; j < 4; j++) {
                        Case caseCible = partie.getPlateau().getEchelles().get(i).get(j == 0 ? 1 : indexCase++);
                        partie.getPlateau().deplacerPionA(chevaux.get(j), caseCible);
                    }
                }
                break;
            case 4 :
                partie = new Partie(true, false);
                jouer = true;
                break;
            default :
                this.afficherOption();
        }
    }

    /**
     * Méthode permettant une saisie décurisée d'un nombre (utile pour les menus)
     * @param min Numéro du choix minimum.
     * @param max Numéro du choix max.
     * @return Retourne le numéro choisi entre min et max par l'utilisateur.
     */
    public static byte choixMenu(byte min, byte max) {
        boolean continuer;
        byte saisie = -1;
        Scanner scan = new Scanner(System.in);

        do {
            try {
                continuer = false;
                saisie = scan.nextByte();
                if (saisie < min || saisie > max) throw new Exception();
            } catch (Exception e) {
                System.err.println("Saisie incorrecte. Veuillez suivre les instructions.");
                continuer = true;
                scan.nextLine();
            }
        } while (continuer);

        return saisie;
    }

    /**
     * Méthode permattant de "clear" la console.
     */
    public static void clear() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    /**
     * Sauvegarder dans un fichier donné en paramètre.
     * @param fichier Nom du fichier en format string.
     */
    public static void sauvegarderPartie(String fichier) {
        ObjectOutputStream oos;

        try {
            oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(
                                    new File("saves/"+fichier))));

            oos.writeObject(partie);

            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Charger un fichier donné en paramètre.
     * @param fichier Nom du fichier en format string.
     */
    public static void chargerPartie(String fichier) {
        ObjectInputStream ois;

        try {
            ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(
                                    new File("saves/"+fichier))));

            try {
                partie = (Partie)ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            ois.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
