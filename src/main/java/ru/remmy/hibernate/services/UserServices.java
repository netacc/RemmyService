package ru.remmy.hibernate.services;

import ru.remmy.hibernate.dao.DAOUser;
import ru.remmy.hibernate.entities.*;
import ru.remmy.hibernate.idao.IUsersDAO;
import ru.remmy.hibernate.dao.UsersList;

public class UserServices {

    private IUsersDAO usersDao = new DAOUser();

    public UserServices() {}

    public User getUsers(String id) {
        return usersDao.getUsers(id);
    }

    public void createUsers(User user) {
        usersDao.createUsers(user);
    }

    public void deleteUsersById(String id) {
        usersDao.deleteUsersById(id);
    }

    public void updateUsers(User user) {
        usersDao.updateUsers(user);
    }

    public UsersList getUsersList() {
        return usersDao.getUsersList();
    }

    public Long userRegistration(String name, String login, String password) {
        return usersDao.userRegistration(name, login, password);
    }
}
