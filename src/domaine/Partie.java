package domaine;

import exceptions.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
        boolean continuer;
        do {
            continuer = false;
            try {
                System.out.println("Veuillez entrer le nombre de joueurs de la partie (entre 1 et 4) : ");
                int nb = sc.nextByte();
                initialiserJoueurs(nb);
            }
            catch (InputMismatchException|PasDeJoueurException e) { continuer = true; sc.nextLine(); }
        } while (continuer || joueurs.isEmpty());

    }

    /**
     * Permet d'initialiser les instances de joueurs humains, leur assigne une couleur et choisir qui commence
     * @param nbJoueur Nombre de joueurs renseignés par le joueur dans le constructeur.
     * @throws PasDeJoueurException dans le cas où un petit malin voudrait jouer sans joueur
     */
    public void initialiserJoueurs(int nbJoueur) throws PasDeJoueurException{
        joueurs = new ArrayList<>(nbJoueur);
        if (nbJoueur<=0) throw new PasDeJoueurException();
        if (nbJoueur<=4) {
            Scanner sc = new Scanner(System.in);
            String nom;
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
                    sc.nextLine();
                    de = lancerDe();
                    if (de>res){
                        joueurCourant=player;
                        res = de;
                    }
                }
                /*TODO : else {
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
        //TODO : methode à completer, réflexive ?
    }

    public boolean estPartieTermine(){
        ArrayList<ArrayList<CaseDEchelle>> listeEchelles = getPlateau().getEchelles();
        boolean victoire = false;
        int i;
        for (ArrayList<CaseDEchelle> echelle : listeEchelles ){
            i=5;
            while (i>1 && echelle.get(i).getChevaux()!=null){
                i--;
            }
            if (i==1){
                victoire = true;
            }
        }
        return victoire;
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
        CaseEcurie ecu = ecuries.get(ecuries.indexOf(new CaseEcurie(chevaux.get(0).getCouleur())));
        for (Pion pion:chevaux) getPlateau().deplacerPionA(pion, ecu);
    }
}

