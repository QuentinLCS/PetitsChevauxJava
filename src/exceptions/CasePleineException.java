package exceptions;

public class CasePleineException extends Exception {

    public CasePleineException() {
        super();
    }

    public CasePleineException(String message) {
        super(message);
    }

    public CasePleineException(Throwable throwable) {
        super(throwable);
    }

    public CasePleineException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CasePleineException(String message, Throwable throwable, boolean enableSuppression, boolean enableStackTrace) {
        super(message, throwable, enableSuppression, enableStackTrace);
    }
}
