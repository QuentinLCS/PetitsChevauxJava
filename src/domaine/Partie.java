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
        initialiserPlateau();
        Scanner sc = new Scanner(System.in);
        boolean continuer;
        do {
            continuer = false;
            try {
                System.out.print("Veuillez entrer le nombre de joueurs de la partie (entre 1 et 4) : ");
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
                System.out.print("Entrer le nom du joueur "+(i+1)+" : ");
                nom = sc.nextLine();
                joueurs.add(new JoueurHumain(nom,Couleur.values()[i]));
                joueurs.get(i).setCaseDeDepart(plateau.getEcuries().get(joueurs.get(i).getCouleur().getId()));
                for (Pion chevaux : joueurs.get(i).getChevaux()) chevaux.setPosition(joueurs.get(i).getCaseDeDepart());
                System.out.println("Très bien "+joueurs.get(i).getNom()+", vous serez de couleur "+joueurs.get(i).getCouleur().getCodeCouleurFond()+joueurs.get(i).getCouleur()+"\033[0m !");
            }
            int res=-1;
            int de;
            System.out.println("Déterminons maintenant l'ordre de jeu !");
            for (Joueur player : joueurs) {
                if (player instanceof JoueurHumain){
                    String input;
                    System.out.print("\nJoueur "+player.getNom()+" ("+player.getCouleur().getCodeCouleurFond()+"     \033[0m), c'est à vous de lancer le dé ! [Appuyez sur entrée]");
                    sc.nextLine();
                    de = lancerDe();
                    System.out.println("Résultat : " +de);
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

    public void jouerUnTour() throws ConflitDeCouleurException{
        int de = lancerDe();
        Case arrivee;

        Pion choix=joueurCourant.choisirPion(de, plateau);
        int idCouleurJoueur = joueurCourant.getCouleur().getId();
        CaseEcurie ecurieDuJoueur = plateau.getEcuries().get(idCouleurJoueur);
        ArrayList<CaseDEchelle> echelleDuJoueur = plateau.getEchelles().get(idCouleurJoueur);

        if (ecurieDuJoueur.equals(choix.getPosition())){
            arrivee = joueurCourant.getCaseDeDepart();
            if(!(arrivee.getChevaux().isEmpty()) && arrivee.getChevaux().get(0).getCouleur()!=joueurCourant.getCouleur() ) mangerLesPions(arrivee);
        }
        else if (echelleDuJoueur.contains(choix.getPosition())) {
            arrivee = echelleDuJoueur.get(echelleDuJoueur.indexOf(choix.getPosition()) + 1);
        }
        else{
            arrivee = plateau.getChemin().get(plateau.getChemin().indexOf(choix.getPosition())+de);
            if(!(arrivee.getChevaux().isEmpty()) && arrivee.getChevaux().get(0).getCouleur()!=joueurCourant.getCouleur()) mangerLesPions(arrivee);
        }
        plateau.deplacerPionA(choix, arrivee);
        if (de == 6){
            jouerUnTour();
        }
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
        CaseEcurie ecu = ecuries.get(chevaux.get(0).getCouleur().getId());
        for (Pion pion:chevaux) getPlateau().deplacerPionA(pion, ecu);
    }
}

