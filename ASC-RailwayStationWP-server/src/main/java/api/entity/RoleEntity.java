package api.entity;

import api.model.RoleType;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by nolesuk on 11-Apr-17.
 */
@Entity
@Table(name = "role")
public class RoleEntity extends AbstractEntity implements GrantedAuthority {

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleType type;

    public RoleEntity() {
        type = RoleType.USER;
    }

    @Override
    public String getAuthority() {
        return type.getName();
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }
}
