package domaine;

import exceptions.*;
import java.util.ArrayList;
import java.util.Random;

public class Partie {

    private Random de = new Random() ;
    private Joueur joueurCourant;
    private ArrayList<Joueur> joueurs;
    private Plateau plateau;

    public Partie() {
    }

    public void initialiserJoueurs(int nbJoueur){
        if (!(nbJoueur<0 || nbJoueur>4)) {
            joueurs = new ArrayList<>(nbJoueur);
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
