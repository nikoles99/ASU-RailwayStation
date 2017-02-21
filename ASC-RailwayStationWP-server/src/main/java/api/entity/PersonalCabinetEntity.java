package api.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by nolesuk on 21-Feb-17.
 */
@Entity
@Table(name = "cabinets")
public class PersonalCabinetEntity extends AbstractEntity {

    @Column(name = "user_id")
    UserEntity userEntity;

    @OneToMany(mappedBy = "personalCabinetEntity", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    List<OrderEntity> orders;

    public PersonalCabinetEntity() {
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
}
