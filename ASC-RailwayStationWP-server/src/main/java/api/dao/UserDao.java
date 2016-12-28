package api.dao;

import api.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserDao {

    void addUser(UserEntity userEntity);

    UserEntity getUser(Integer id);
}
