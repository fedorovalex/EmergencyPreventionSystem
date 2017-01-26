package cmdLine.exception;

public class ParametersException extends Exception {
    public ParametersException() {
    }

    public ParametersException(String message) {
        super(message);
    }

    public ParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParametersException(Throwable cause) {
        super(cause);
    }

    public ParametersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
