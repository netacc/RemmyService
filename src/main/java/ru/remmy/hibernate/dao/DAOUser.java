package ru.remmy.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.remmy.hibernate.entities.UsersEntity;
import ru.remmy.hibernate.idao.IUsersDAO;
import ru.remmy.hibernate.utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;

public class DAOUser implements IUsersDAO {

    public UsersEntity getUsers(String id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(UsersEntity.class, id);
    }

    public void createUsers(UsersEntity user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void updateUsers(UsersEntity user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void deleteUsersById(String id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(Integer.parseInt(id));
        tx1.commit();
        session.close();
    }

    public UsersList getUsersList() {
        return new UsersList((ArrayList<UsersEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM UsersEntity").list());
    }
}