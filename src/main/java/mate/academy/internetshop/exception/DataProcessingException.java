package mate.academy.internetshop.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(Throwable cause) {
        super(cause);
    }

    public DataProcessingException(String message) {
        super(message);
    }
}
