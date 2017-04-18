package api.dao;

import api.entity.UserEntity;

public interface UserDao {

    void add(UserEntity user);

    UserEntity get(Integer id);

    UserEntity getByLogin(String login);

    void update(UserEntity user);

    void delete(UserEntity user);

}
