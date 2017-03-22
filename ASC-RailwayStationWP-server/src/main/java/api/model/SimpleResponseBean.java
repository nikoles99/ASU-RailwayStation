package api.model;

/**
 * Created by nikita on 28.02.17.
 */
public class SimpleResponseBean {

    private String message;

    public SimpleResponseBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
