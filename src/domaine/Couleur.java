package domaine;

public enum Couleur {
    JAUNE('J',"\033[93;107m","\033[1;97;43m"),
    BLEU('B',"\033[94;107m","\033[1;97;104m"),
    VERT('V',"\033[92;107m","\033[1;97;42m"),
    ROUGE('R',"\033[91;107m","\033[1;97;101m");

    private char symbol;
    private String codeCouleur;
    private String CodeCouleurFond;

    Couleur(char symbol, String codeCouleur, String codeCouleurFond) {
        this.symbol = symbol;
        this.codeCouleur = codeCouleur;
        this.CodeCouleurFond = codeCouleurFond;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getCodeCouleur() {
        return codeCouleur;
    }

    public String getCodeCouleurFond() {
        return CodeCouleurFond;
    }
}
