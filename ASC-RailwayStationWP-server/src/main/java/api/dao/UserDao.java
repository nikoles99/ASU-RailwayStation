package api.dao;

import api.entity.UserEntity;

public interface UserDao {

    void addUser(UserEntity userEntity);

    UserEntity getUser(Integer id);
}
