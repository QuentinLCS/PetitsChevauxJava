package domaine;

import exceptions.ConflitDeCouleurException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe instaciable du joueur humain.
 * Fils de la classe Joueur.
 */
public class JoueurHumain extends Joueur {

    /**
     * Constructeur JoueurHumain()
     * @param nom Nom du joueur pour le constructeur parent.
     * @param couleur Couleur du joueur.
     */
    public JoueurHumain(String nom, Couleur couleur) {
        super(nom, couleur);
    }

    /**
     * Propose les pions déplaçables en fonction de leurs contraintes et de la valeur du dé.
     * @param distance Distance donnée par le dé.
     * @param plateau Plateau de la partie.
     * @return Retourne le cheval choisi par le joueur parmi ceux proposés.
     * @throws ConflitDeCouleurException Si un joueur adverse est sur la route. (pion non choisissable)
     */
    @Override
    public Pion choisirPion(int distance, Plateau plateau) throws ConflitDeCouleurException {
        ArrayList<Pion> chevauxDeplacables = new ArrayList<>();
        ArrayList<CaseDEchelle> echelleJoueur = plateau.getEchelles().get(getCouleur().getId());
        CaseDeChemin caseDevantEchelle = plateau.getChemin().get(plateau.getChemin().indexOf(getCaseDeDepart())-1);

        Pion choix;

        byte saisie = 0,i, proposition=1, nbChevauxVerifies = (byte)plateau.getEcuries().get(getCouleur().getId()).getChevaux().size();
        boolean continuer;


        Scanner scan = new Scanner(System.in);

        if (distance == 6 && nbChevauxVerifies > 0) {
            chevauxDeplacables.add(plateau.getEcuries().get(getCouleur().getId()).getChevaux().get(0)); //Parcours les chevaux restant a.k.a ceux n'étant pas dans l'écurie
            System.out.println(proposition+ " : Sortir un pion de l'écurie");
        }

        for(int k=0; k<(4-nbChevauxVerifies); k++){
            Pion pion=getChevaux().get(k);
            if(getChevaux().get(k).getPosition() instanceof CaseDEchelle){
                if (echelleJoueur.get(echelleJoueur.indexOf(pion.getPosition())+1).peutPasser(pion) && echelleJoueur.indexOf(pion.getPosition())+2== distance) { //Condition pour passer à la case d'échelle suivante
                    chevauxDeplacables.add(proposition,pion);
                    System.out.println(proposition+ " : Bouger le pion n°"+(getChevaux().indexOf(pion)+1));
                    proposition++;
                }
            }
            else if (pion.getPosition().equals(caseDevantEchelle) && distance==1) { //Condition pour monter sur l'échelle
                chevauxDeplacables.add(proposition,pion);
                System.out.println(proposition + " : Bouger le pion n°" + (getChevaux().indexOf(pion)+1));
                proposition++;
            }
            else {
                continuer=true;
                i=1;
                while (i<distance && continuer){
                    i++;
                    continuer=plateau.getChemin().get((plateau.getChemin().indexOf(pion.getPosition())+i)%56).peutPasser(pion);
                    if (plateau.getChemin().get(plateau.getChemin().indexOf(pion.getPosition())+i).equals(caseDevantEchelle)) continuer=false;
                }
                if (continuer) {
                    chevauxDeplacables.add(pion);
                    System.out.println(proposition + " : Bouger le pion n°" + (getChevaux().indexOf(pion)+1));
                }
            }
        }



        if (chevauxDeplacables.isEmpty()) choix = null;
        else {
            do {
                continuer = false;
                try {
                    System.out.print("Choisir une proposition : ");
                    saisie = scan.nextByte();
                } catch (InputMismatchException e) {
                    System.out.println("\033[93;107mErreur : Il faut entrer un chiffre entre 1 et "+chevauxDeplacables.size()+"\033[0m");
                    continuer = true;
                    scan.nextLine();
                }
            } while((saisie < 1 || saisie > chevauxDeplacables.size()) || continuer);
            choix = chevauxDeplacables.get(saisie-1);
        }

        return choix;
    }
}
