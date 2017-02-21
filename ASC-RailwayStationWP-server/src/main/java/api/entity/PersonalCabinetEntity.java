package api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by nolesuk on 21-Feb-17.
 */
@Entity
@Table(name = "cabinets")
public class PersonalCabinetEntity extends AbstractEntity {

    @Column(name = "user_id")
    UserEntity userEntity;

    List<OrderEntity> orders;
}
