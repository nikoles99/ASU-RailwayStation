package api.model;

import java.io.Serializable;

/**
 * Created by nolesuk on 12-Apr-17.
 */
public enum RoleType implements Serializable {
    USER("user"), SUPER_USER("superuser"), ADMIN("admin");

    RoleType(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
