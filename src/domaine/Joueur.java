package domaine;

import java.util.ArrayList;

public abstract class Joueur {

    private String nom;
    private Case caseDep;
    private ArrayList<Pion> chevaux;
    private Couleur couleur;


    public Joueur(String nom, Couleur couleur) {
        this.nom = nom;
        this.couleur=couleur;
    }

    public Case getCaseDep() {
        return caseDep;
    }

    public void setCaseDep(Case caseDep) {
        this.caseDep = caseDep;
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

    public abstract Pion choisirPion (int num, Plateau plateau);
}
