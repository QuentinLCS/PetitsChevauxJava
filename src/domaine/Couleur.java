package domaine;

public enum Couleur {
    JAUNE('J'),
    BLEU('B'),
    VERT('V'),
    ROUGE('R');

    private char symbol;

    Couleur(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
