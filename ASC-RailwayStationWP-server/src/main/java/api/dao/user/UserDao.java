package api.dao.user;

import api.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

public interface UserDao {

    void addUser(UserEntity userEntity);

    UserEntity getUser(Integer id);

    UserEntity getUserByLogin(String login);

    void updateUser(UserEntity userEntity);

    void removeUser(UserEntity userEntity);

}
