package api.dao.order;

import api.entity.OrderEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nolesuk on 22-Feb-17.
 */
@Transactional
public interface OrderDao {

    void addOrder(OrderEntity orderEntity);

    OrderEntity getOrder(Integer id);

    void updateOrder(OrderEntity orderEntity);

    void removeOrder(OrderEntity orderEntity);
}
