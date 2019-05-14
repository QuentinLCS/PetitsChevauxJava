package exceptions;

public class ConflitDeCouleurException extends Exception {
    private String message = "La case contient une autre couleur que la votre.";

    public ConflitDeCouleurException() {
        super();
    }

    public ConflitDeCouleurException(String message) {
        super(message);
    }

    public ConflitDeCouleurException(Throwable throwable) {
        super(throwable);
    }

    public ConflitDeCouleurException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ConflitDeCouleurException(String message, Throwable throwable, boolean enableSuppression, boolean enableStackTrace) {
        super(message, throwable, enableSuppression, enableStackTrace);
    }
}