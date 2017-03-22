package api.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by nolesuk on 21-Feb-17.
 */
@Entity
@Table(name = "cabinets")
public class CabinetEntity extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "cabinet", cascade = {CascadeType.ALL})
    private List<OrderEntity> orders;

    public CabinetEntity() {
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        for (OrderEntity order : orders) {
            order.setCabinet(this);
        }
        this.orders = orders;
    }
}
