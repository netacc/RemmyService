package ru.remmy.hibernate.services;

import ru.remmy.hibernate.dao.DAOUser;
import ru.remmy.hibernate.idao.IUsersDAO;
import ru.remmy.hibernate.entities.UsersEntity;
import ru.remmy.hibernate.dao.UsersList;

public class UserServices {

    private IUsersDAO usersDao = new DAOUser();

    public UserServices() {}

    public UsersEntity getUsers(String id) {
        return usersDao.getUsers(id);
    }

    public void createUsers(UsersEntity user) {
        usersDao.createUsers(user);
    }

    public void deleteUsersById(String id) {
        usersDao.deleteUsersById(id);
    }

    public void updateUsers(UsersEntity user) {
        usersDao.updateUsers(user);
    }

    public UsersList getUsersList() {
        return usersDao.getUsersList();
    }
}
