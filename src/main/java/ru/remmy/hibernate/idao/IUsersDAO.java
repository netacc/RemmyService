package ru.remmy.hibernate.idao;

import ru.remmy.hibernate.dao.UsersList;
import ru.remmy.hibernate.entities.UsersEntity;

public interface IUsersDAO {
        void createUsers(UsersEntity user);
        UsersEntity getUsers(String id);
        void updateUsers(UsersEntity user);
        void deleteUsers(UsersEntity user);
        UsersList getUsersList();
}
