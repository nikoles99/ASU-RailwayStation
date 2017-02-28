package api.exception;

/**
 * Created by nikita on 28.02.17.
 */
public class StationException extends Exception {
    public StationException() {
        super();
    }

    public StationException(String message) {
        super(message);
    }

    public StationException(String message, Throwable cause) {
        super(message, cause);
    }
}
