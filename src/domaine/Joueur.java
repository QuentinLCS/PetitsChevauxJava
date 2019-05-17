package domaine;

import exceptions.ConflitDeCouleurException;

import java.util.ArrayList;

/**
 * Classe abstraite des joueurs.
 * Parent de JoueurHumain et JoueurIA
 */
public abstract class Joueur {

    private String nom;
    private Case caseDeDepart;
    private ArrayList<Pion> chevaux;
    private Couleur couleur;

    /**
     * Constructeur Joueur()
     * @param nom Nom du joueur.
     * @param couleur Couleur du joueur.
     */
    public Joueur(String nom, Couleur couleur) {
        this.nom = nom;
        this.couleur=couleur;
    }

    public Case getCaseDeDepart() {
        return caseDeDepart;
    }

    public void setCaseDeDepart(Case caseDeDepart) {
        this.caseDeDepart = caseDeDepart;
    }

    public ArrayList<Pion> getChevaux() {
        return chevaux;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public abstract Pion choisirPion (int num, Plateau plateau) throws ConflitDeCouleurException;
}
