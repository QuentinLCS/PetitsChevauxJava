package domaine;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Plateau {

    private ArrayList<ArrayList<CaseDEchelle>> echelles;
    private ArrayList<CaseDeChemin> chemin;
    private ArrayList<CaseEcurie> ecuries;

    public Plateau() {

        this.echelles = new ArrayList<ArrayList<CaseDEchelle>>();
        this.chemin = new ArrayList<CaseDeChemin>();
        this.ecuries = new ArrayList<CaseEcurie>();

        for (byte i = 0; i < 8; i++)
            this.chemin.add(new CaseDeChemin());

        for (byte i = 0; i < 4; i++) {
            this.echelles.add(new ArrayList<CaseDEchelle>());
            this.ecuries.add(new CaseEcurie(Couleur.values()[i]));
            for (byte j = 0; j < 6; j++) {
                this.echelles.get(i).add(new CaseDEchelle(Couleur.values()[i]));
                this.chemin.add(new CaseDeChemin());
            }
        }
    }

    public ArrayList<ArrayList<CaseDEchelle>> getEchelles() {
        return this.echelles;
    }

    public ArrayList<CaseDeChemin> getChemin() {
        return this.chemin;
    }

    public ArrayList<CaseEcurie> getEcuries() {
        return this.ecuries;
    }

    public void afficher(){
        byte indexCouleur;
        byte valEchelle;

        for (byte i = 0; i < 15; i++) {

            for (byte k = 0; k < 3; k++) {
                System.out.println();
                for (byte j = 0; j < 15; j++) {
                    indexCouleur = (byte)((i <= 7 ? 0 : 1) + i / 8 + j / 8 );
                    if ((i < 6 || i > 8) && (j < 6 || j > 8))
                        System.out.print(Couleur.values()[indexCouleur].getCodeCouleurFond()+ "            \033[0m");

                    else if ((i == 7 && j != 0 && j != 14)|| (j == 7 && i != 0 && i != 14)) {
                        if (i == 7)
                            if (j < 8) valEchelle = j;
                            else valEchelle = (byte)(14 - j);
                        else
                            if (i < 8) valEchelle = i;
                            else valEchelle = (byte)(14 - i);

                        System.out.print(Couleur.values()[indexCouleur].getCodeCouleurFond() + "      " + (k == 1 ? valEchelle : " ") + "     \033[0m");

                    } else
                        System.out.print(Couleur.values()[indexCouleur].getCodeCouleur()+ "            \033[0m");

                }
            }
        }
    }

    public void deplacerPionA(Pion p, Case c){
        int pos=chemin.indexOf(p);
        chemin.get(pos).getChevaux().remove(p);
        c.ajouteCheval(p);
    }
}
