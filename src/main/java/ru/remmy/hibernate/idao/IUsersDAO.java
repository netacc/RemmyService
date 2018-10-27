package ru.remmy.hibernate.idao;

import ru.remmy.hibernate.dao.UsersList;
import ru.remmy.hibernate.entities.*;

public interface IUsersDAO {
        long saveUser(User user);
        boolean isExist(String login);
        User getUser(String login);
        void createUsers(User user);
        User getUsers(String id);
        User getUsersByName(String name);
        void updateUsers(User user);
        void deleteUsersById(String id);
        UsersList getUsersList();
}
