package domaine;

import java.util.ArrayList;

public class JoueurHumain extends Joueur {

    public JoueurHumain(String nom, Couleur couleur) {
        super(nom, couleur);
    }

    @Override
    public Pion choisirPion(int num, Plateau plateau) {
        return getChevaux().get(num);
    }
}
