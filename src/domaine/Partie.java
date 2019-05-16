package domaine;

import exceptions.*;

import java.lang.reflect.Array;
import java.security.DrbgParameters;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Partie {

    private Random de = new Random() ;
    private Joueur joueurCourant;
    private ArrayList<Joueur> joueurs;
    private Plateau plateau;

    public Partie()  {
        plateau = new Plateau();
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.println("Veuillez entrer le nombre de joueurs de la partie (entre 1 et 4) : ");
                int nb = sc.nextByte();
                initialiserJoueurs(nb);
            }
            catch (PasDeJoueurException e) {
            }
        } while (joueurs == null);

    }

    /**
     * Permet d'initialiser les instances de joueurs humains, leur assigne une couleur et choisir qui commence
     * @param nbJoueur
     * @throws PasDeJoueurException dans le cas où un petit malin voudrait jouer sans joueur
     */
    public void initialiserJoueurs(int nbJoueur) throws PasDeJoueurException{
        if (nbJoueur<=0) throw new PasDeJoueurException();
        if (nbJoueur<=4) {
            Scanner sc = new Scanner(System.in);
            String nom;
            joueurs = new ArrayList<>(nbJoueur);
            for (int i = 0; i<nbJoueur; i++){
                System.out.println("Entrer le nom du joueur "+(i+1)+" :");
                nom = sc.nextLine();
                joueurs.add(new JoueurHumain(nom,Couleur.values()[i]));
                System.out.print("Très bien "+joueurs.get(i).getNom()+", vous serez de couleur "+joueurs.get(i).getCouleur().getCodeCouleurFond()+joueurs.get(i).getCouleur()+"\033[0m !");
            }
            int res=-1;
            int de;
            System.out.println("Déterminons maintenant l'ordre de jeu !\nLancez votre dé en appuyant sur le touche \"Entrée\" \n");
            for (Joueur player : joueurs) {
                if (player instanceof JoueurHumain){
                    String input;
                    System.out.println("Joueur "+player.getNom()+" ("+player.getCouleur().getCodeCouleurFond()+"     \033[0m), c'est à vous de lancer le dé !");
                    do {
                        input = sc.nextLine();
                        if (input != "\0") System.out.println("\033[91;107mIl faut appuyer sur la touche Entrée !\033[0m");
                    } while (input != "\0");
                    de = lancerDe();
                    if (de>res){
                        joueurCourant=player;
                        res = de;
                    }
                }
                /*else {
                    de = lancerDe();
                    if (de>res){
                        joueurCourant=player;
                        res = de;
                }*/
            }
        }
    }

    public void initialiserPlateau(){
        plateau = new Plateau();
    }

    private int lancerDe(){
        return de.nextInt(6)+1;
    }

    public void jouerUnTour(){
        //methode à completer
    }

    public boolean estPartieTermine(){
        //methode à completer ----> vérifier les règles
        return true;
    }

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    public void setJoueurCourant(Joueur joueurCourant) {
        this.joueurCourant = joueurCourant;
    }

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    private void mangerLesPions(Case cs){
        ArrayList<Pion> chevaux = cs.getChevaux();
        ArrayList<CaseEcurie> ecuries = getPlateau().getEcuries();
        for (Pion pion:chevaux) {
            for (CaseEcurie ecu:ecuries){
                if (ecu.getCouleur()==pion.getCouleur()) {
                    getPlateau().deplacerPionA(pion, ecu);
                }
            }
        }
    }
}
