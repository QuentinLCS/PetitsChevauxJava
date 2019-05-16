package domaine;

import exceptions.ConflitDeCouleurException;

import java.util.ArrayList;

public class JoueurHumain extends Joueur {

    public JoueurHumain(String nom, Couleur couleur) {
        super(nom, couleur);
    }

    @Override
    public Pion choisirPion(int distance, Plateau plateau) throws ConflitDeCouleurException {
        ArrayList<Pion> chevauxDeplacables = new ArrayList<Pion>();
        byte i, nbChevauxVerifies = (byte)plateau.getEcuries().get(plateau.getEcuries().indexOf(new CaseEcurie(this.getCouleur()))).getChevaux().size();
        boolean continuer = true;
        CaseDeChemin caseChemin = new CaseDeChemin();
        caseChemin.ajouteCheval(this.getChevaux().get(nbChevauxVerifies));

        if (distance == 6)
            if (nbChevauxVerifies > 0)
                chevauxDeplacables.add(this.getChevaux().get(0));

        while (nbChevauxVerifies != 4 && continuer) {
            i = 0;

            if (plateau.getChemin().get(plateau.getChemin().indexOf(caseChemin)).peutPasser(this.getChevaux().get(nbChevauxVerifies))) {
                i++;
                if (i == distance) {
                    nbChevauxVerifies++;
                    caseChemin.ajouteCheval(this.getChevaux().get(nbChevauxVerifies));
                    chevauxDeplacables.add(this.getChevaux().get(nbChevauxVerifies));
                }
            }
        }

        //TODO : Proposer les choix présents dans chevauxDeplacables.

        return getChevaux().get(distance);
    }
}
