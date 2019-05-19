package domaine;

import exceptions.ConflitDeCouleurException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class JoueurIA extends Joueur {

    private byte difficulte;

    public JoueurIA(String nom, Couleur couleur, byte difficulte) {
        super(nom, couleur);
        this.difficulte=difficulte;
    }

    @Override
    public Pion choisirPion(int distance, Plateau plateau) throws ConflitDeCouleurException {
        ArrayList<Pion> chevauxDeplacables = new ArrayList<>();
        ArrayList<CaseDEchelle> echelleJoueur = plateau.getEchelles().get(getCouleur().getId());
        CaseDeChemin caseDevantEchelle = plateau.getChemin().get(plateau.getChemin().indexOf(getCaseDeDepart())-1);

        Pion choix;

        byte i, nbChevauxVerifies = (byte)plateau.getEcuries().get(getCouleur().getId()).getChevaux().size();
        boolean continuer;


        if (distance == 6 && nbChevauxVerifies > 0) chevauxDeplacables.add(plateau.getEcuries().get(getCouleur().getId()).getChevaux().get(0)); //Parcours les chevaux restant a.k.a ceux n'étant pas dans l'écurie
        for(int k=0; k<(4-nbChevauxVerifies); k++){
            Pion pion=getChevaux().get(k);
            if(getChevaux().get(k).getPosition() instanceof CaseDEchelle){
                if (echelleJoueur.get(echelleJoueur.indexOf(pion.getPosition())+1).peutPasser(pion) && echelleJoueur.indexOf(pion.getPosition())+2== distance) { //Condition pour passer à la case d'échelle suivante
                    chevauxDeplacables.add(pion);
                }
            }
            else if (pion.getPosition().equals(caseDevantEchelle) && distance==1) { //Condition pour monter sur l'échelle
                chevauxDeplacables.add(pion);
            }
            else {
                continuer=true;
                i=1;
                while (i<distance && continuer){
                    continuer=plateau.getChemin().get(plateau.getChemin().indexOf(pion.getPosition())+i).peutPasser(pion);
                    i++;
                    if (plateau.getChemin().get(plateau.getChemin().indexOf(pion.getPosition())+i).equals(caseDevantEchelle)) continuer=false;
                }
                if (continuer) chevauxDeplacables.add(pion);
            }
        }

        for (Pion pion:chevauxDeplacables) System.out.println("- "+pion);

        if (chevauxDeplacables.isEmpty()) choix=null;
        else choix = chevauxDeplacables.get(chevauxDeplacables.indexOf(choixStrategie(chevauxDeplacables, distance, plateau)));
        return choix;
    }

    public Pion choixStrategie(ArrayList<Pion> chevauxDeplacables, int de, Plateau plateau){
        Pion choix;
        if (difficulte == 1){
            Random rand = new Random();
            int i=rand.nextInt(chevauxDeplacables.size());
            choix=chevauxDeplacables.get(i);
        }
        else if (difficulte==2) {
            choix=chevauxDeplacables.get(0);
        }
        else {
            ArrayList<Pion> listeChoix = new ArrayList<>(6); // Création d'une ArrayList pour stocker des pions en fonction de leur importance
            Pion test = new Pion("test",getCouleur());
            for (int j=0; j<6; j++) listeChoix.add(test);
            if (!(getCaseDeDepart().getChevaux().isEmpty()) && de==6) if (getCaseDeDepart().getChevaux().get(0).getCouleur()!=chevauxDeplacables.get(0).getCouleur() ) listeChoix.set(0,chevauxDeplacables.get(0));
            else if (de == 6) listeChoix.set(4,chevauxDeplacables.get(0));
            else{
                for (Pion cheval : chevauxDeplacables){
                    Case arrivee=plateau.getChemin().get(plateau.getChemin().indexOf(cheval.getPosition())+de);
                    if (!arrivee.getChevaux().isEmpty() && arrivee.getChevaux().get(0).getCouleur()!=cheval.getCouleur()) listeChoix.set(1,cheval);
                    else if (cheval.getPosition().equals(plateau.getChemin().get(plateau.getChemin().indexOf(getCaseDeDepart())-1)) && de==1) listeChoix.set(2,cheval);
                    else if (cheval.getPosition() instanceof CaseDEchelle) listeChoix.set(3,cheval);
                    else listeChoix.set(5,cheval);
                }
            }
            int parcours = 0;
            while (listeChoix.get(parcours).equals(test)){
                System.out.println(listeChoix.get(parcours));
                parcours++;
            }
            choix = listeChoix.get(parcours);
        }
        return choix;
    }

    public byte getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(byte difficulte) {
        this.difficulte = difficulte;
    }
}
