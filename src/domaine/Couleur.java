package domaine;

public enum Couleur {
    JAUNE("\033[93;107m","\033[1;97;43m"),
    BLEU("\033[94;107m","\033[1;97;104m"),
    VERT("\033[92;107m","\033[1;97;42m"),
    ROUGE("\033[91;107m","\033[1;97;101m");

    private String codeCouleur, codeCouleurFond;

    Couleur(String codeCouleur, String codeCouleurFond) {
        this.codeCouleur = codeCouleur;
        this.codeCouleurFond = codeCouleurFond;
    }

    public String getCodeCouleur() {
        return this.codeCouleur;
    }

    public String getCodeCouleurFond() {
        return this.codeCouleurFond;
    }
}
