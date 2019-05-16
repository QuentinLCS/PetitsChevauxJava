package domaine;

public enum Couleur {
    JAUNE('J',"\033[7;43m","\033[103m"),
    BLEU('B',"\033[7;44m","\033[104m"),
    VERT('V',"\033[7;42m","\033[102m"),
    ROUGE('R',"\033[7;41m","\033[101m");

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
