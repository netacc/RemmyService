package ru.remmy.dao;

import ru.remmy.entities.User;
import ru.remmy.entities.UserList;

import javax.sql.DataSource;
import java.util.ArrayList;

public interface IUsersDAO {
        void setDataSource(DataSource dataSource);
        User createUser(User user);
        User getUser(String id);
        User updateUser(User user);
        boolean removeUser(String id);
        UserList getUserList();
}
