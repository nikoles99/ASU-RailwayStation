package api.exception;

/**
 * Created by nolesuk on 17-Mar-17.
 */
public class TrainException extends Exception {
    public TrainException() {
    }

    public TrainException(String message) {
        super(message);
    }

    public TrainException(String message, Throwable cause) {
        super(message, cause);
    }

    public TrainException(Throwable cause) {
        super(cause);
    }

    public TrainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
