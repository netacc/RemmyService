package ru.remmy.hibernate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.remmy.hibernate.dao.DAOUser;
import ru.remmy.hibernate.entities.User;

@Service
public class RegistrationService {

    @Autowired
    @Qualifier(value = "userDao")
    private DAOUser userDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public Long userRegistration(String name, String login, String password) {
        if (!userDao.isExist(login)) {
            User user = new User();
            user.setName(name);
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(0);
            return userDao.saveUser(user);
        } else {
            return 0L;
        }
    }
}
