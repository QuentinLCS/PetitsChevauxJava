package exceptions;

public class PasDeJoueurException extends Exception {
    private String message = "Pas de joueur.";

    public PasDeJoueurException() {
        super();
    }

    public PasDeJoueurException(String message) {
        super(message);
    }

    public PasDeJoueurException(Throwable throwable) {
        super(throwable);
    }

    public PasDeJoueurException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public PasDeJoueurException(String message, Throwable throwable, boolean enableSuppression, boolean enableStackTrace) {
        super(message, throwable, enableSuppression, enableStackTrace);
    }
}