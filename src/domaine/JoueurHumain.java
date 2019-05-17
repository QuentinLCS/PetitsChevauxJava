package domaine;

import exceptions.ConflitDeCouleurException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class JoueurHumain extends Joueur {

    public JoueurHumain(String nom, Couleur couleur) {
        super(nom, couleur);
    }

    @Override
    public Pion choisirPion(int distance, Plateau plateau) throws ConflitDeCouleurException {
        ArrayList<Pion> chevauxDeplacables = new ArrayList<Pion>();
        byte saisie = 0, i, nbChevauxVerifies = (byte)plateau.getEcuries().get(plateau.getEcuries().indexOf(new CaseEcurie(this.getCouleur()))).getChevaux().size();
        boolean continuer;
        CaseDeChemin caseChemin = new CaseDeChemin();
        Scanner scan = new Scanner(System.in);
        caseChemin.ajouteCheval(this.getChevaux().get(nbChevauxVerifies));

        if (distance == 6)
            if (nbChevauxVerifies > 0)
                chevauxDeplacables.add(this.getChevaux().get(0));

        while (nbChevauxVerifies != 4) {
            i = 0;
            continuer = true;

            while (continuer) {
                if (plateau.getChemin().get(plateau.getChemin().indexOf(caseChemin)).peutPasser(this.getChevaux().get(nbChevauxVerifies))) {
                    i++;
                    if (i == distance) {
                        caseChemin.ajouteCheval(this.getChevaux().get(nbChevauxVerifies));
                        chevauxDeplacables.add(this.getChevaux().get(nbChevauxVerifies));
                    }
                } else
                    continuer = false;
            }
            nbChevauxVerifies++;
        }

        for (byte j = 0; j < this.getChevaux().size(); j++ )
            System.out.println(j+ " : Déplacer le cheval numero " +j);

        do {
            continuer = false;
            try {
                System.out.print("Numéro de pion à déplacer : ");
                saisie = scan.nextByte();
            } catch (InputMismatchException e) {
                continuer = true;
                scan.nextLine();
            }
        } while((saisie < 1 || saisie > this.getChevaux().size()) || continuer);

        return chevauxDeplacables.get(saisie);
    }
}
