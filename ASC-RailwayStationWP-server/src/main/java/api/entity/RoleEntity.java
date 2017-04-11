package api.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by nolesuk on 11-Apr-17.
 */
@Entity
@Table(name = "role")
public class RoleEntity extends AbstractEntity {

    @OneToMany(mappedBy = "role", cascade = {CascadeType.ALL})
    private List<UserEntity> users;

    @Column(name = "name")
    private String name;

    public RoleEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
