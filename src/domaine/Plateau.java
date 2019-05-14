package domaine;

import java.util.ArrayList;

public class Plateau {

    private ArrayList<ArrayList<CaseDEchelle>> echelles;
    private ArrayList<CaseDeChemin> chemin;
    private ArrayList<CaseEcurie> ecuries;

    public Plateau() {
    }

    public ArrayList<ArrayList<CaseDEchelle>> getEchelles() {
        return echelles;
    }

    public ArrayList<CaseDeChemin> getChemin() {
        return chemin;
    }

    public ArrayList<CaseEcurie> getEcuries() {
        return ecuries;
    }

    public void afficher(){
        //A faire ou non selon si on JavaFX or not lol
    }

    public void deplacerPionA(Pion p, Case c){
        int pos=chemin.indexOf(p);
        chemin.get(pos).getChevaux().remove(p);
        c.ajouteCheval(p);
    }
}
