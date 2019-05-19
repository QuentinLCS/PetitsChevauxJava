package domaine;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class Plateau, contenant tous les nécessaires du plateau et de ses interactions.
 * @author Quentin LECHASLES
 */
public class Plateau {

    private ArrayList<ArrayList<CaseDEchelle>> echelles;
    private LinkedList<CaseDeChemin> chemin;
    private ArrayList<CaseEcurie> ecuries;

    /**
     * Le constructeur de Plateau initialise :
     * - chaque echelle de chaque joueur.
     * - chaque caseDeChemin
     * - ainsi que chaque caseEcurie.
     */
    public Plateau() {

        this.echelles = new ArrayList<>();
        this.chemin = new LinkedList<>();
        this.ecuries = new ArrayList<>();

        for (byte i = 0; i < 8; i++)
            this.chemin.add(new CaseDeChemin());

        for (byte i = 0; i < 4; i++) {
            this.echelles.add(new ArrayList<>());
            this.ecuries.add(new CaseEcurie(Couleur.values()[i]));
            for (byte j = 0; j < 6; j++) {
                this.echelles.get(i).add(new CaseDEchelle(Couleur.values()[i]));
                for (byte k = 0; k < 2; k++)
                    this.chemin.add(new CaseDeChemin());
            }
        }
    }

    public ArrayList<ArrayList<CaseDEchelle>> getEchelles() {
        return this.echelles;
    }

    public LinkedList<CaseDeChemin> getChemin() {
        return this.chemin;
    }

    public ArrayList<CaseEcurie> getEcuries() {
        return this.ecuries;
    }

    /**
     * Affichage en grande dimension, couleur du plateau et de ses interactions.
     */
    public void afficher(){
        String couleur;
        ArrayList<Pion> chevaux;
        byte valEchelle, numCase = 0, numEquipe;
        byte[] indexCase = {0,1,2,55,3,54,4,53,5,52,6,51,7,44,45,46,47,48,49,50,8,9,10,11,12,13,14,43,15,42,41,40,39,38,37,36,22,21,20,19,18,17,16,35,23,34,24,33,25,32,26,31,27,30,29,28};

        for (byte i = 0; i < 15; i++) {

            for (byte k = 0; k < 3; k++) {

                for (byte j = 0; j < 15; j++) {

                    if (i <= 7 && !(i == 7 && j > 6))
                        if (j <= 6) numEquipe = 0;
                        else numEquipe = 1;
                    else
                        if (j <= 7) numEquipe = 2;
                        else numEquipe = 3;

                    // CENTRE -----------------------------------------
                    if (i == 7 && j == 7)
                        System.out.print("\033[1;30m    " +(k == 1 ? "WIN !" : "     ")+ "   \033[0m");

                    // ECURIES ----------------------------------------
                    else if ((i < 6 || i > 8) && (j < 6 || j > 8)) {
                        chevaux = ecuries.get(numEquipe).getChevaux();
                        couleur = ecuries.get(numEquipe).getCouleur().getCodeCouleurFond();
                        if ((i == 2 && j == 2) || (i == 2 && j == 12) || (i == 12 && j ==2) || (i == 12 && j == 12))
                            System.out.print(couleur+ "    " +(chevaux.isEmpty() || k != 1 ? "   " : chevaux.size()+"\u265e")+ "     \033[0m");
                        else
                            System.out.print(couleur+ "            \033[0m");

                    // ECHELLES ---------------------------------------
                    } else if ((i == 7 && j != 0 && j != 14)|| (j == 7 && i != 0 && i != 14)) {
                        if (i == 7)
                            if (j < 8) valEchelle = j;
                            else valEchelle = (byte)(14 - j);
                        else
                            if (i < 8) valEchelle = i;
                            else valEchelle = (byte)(14 - i);

                        chevaux = echelles.get(numEquipe).get(valEchelle-1).getChevaux();
                        couleur = echelles.get(numEquipe).get(0).getCouleur().getCodeCouleurFond();
                        System.out.print(couleur+ "      " + (k == 1 ? (chevaux.isEmpty() ? valEchelle : "\u265e") : " ") + "     \033[0m");

                    // CHEMIN ------------------------------------------
                    } else {
                        chevaux = chemin.get(indexCase[numCase]).getChevaux();
                        couleur = chevaux.isEmpty() ? Couleur.values()[0].getCodeCouleur() : chevaux.get(0).getCouleur().getCodeCouleur();
                        System.out.print(couleur + "     " + (chevaux.isEmpty() || k != 1 ? "  " : chevaux.size()+"\u265e") + "     \033[0m");
                        numCase++;
                    }
                }
                System.out.println();
            }
        }
    }

    /**
     * La fonction deplacerPionA permet de déplacer un pion sur une case choisie.
     * @param pion Pion a déplacer.
     * @param caseCible Case destination.
     */
    public void deplacerPionA(Pion pion, Case caseCible){
        Case caseDepart = pion.getPosition();
        caseDepart.getChevaux().remove(pion);
        caseCible.ajouteCheval(pion);
        pion.setPosition(caseCible);
        System.out.println("pion = " +pion);
        for (Case c : ecuries) System.out.println("ecurie : "+c.getChevaux());
    }
}
