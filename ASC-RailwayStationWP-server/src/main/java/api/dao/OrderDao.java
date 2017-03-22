package api.dao;

import api.entity.OrderEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nolesuk on 22-Feb-17.
 */
public interface OrderDao {

    void add(OrderEntity order);

    OrderEntity getOrder(Integer id);

    void update(OrderEntity order);

    void remove(OrderEntity order);
}
