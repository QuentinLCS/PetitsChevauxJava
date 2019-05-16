package domaine;

import exceptions.ConflitDeCouleurException;

public class CaseDeChemin extends Case {
    public CaseDeChemin() {
        super();
    }

    public boolean peutPasser(Pion cheval) throws ConflitDeCouleurException {
        if (!(cheval.getCouleur().equals(this.getChevaux().get(0).getCouleur()))) throw new ConflitDeCouleurException();
        return this.getChevaux().isEmpty() || cheval.getCouleur().equals(this.getChevaux().get(0).getCouleur());
    }

}
