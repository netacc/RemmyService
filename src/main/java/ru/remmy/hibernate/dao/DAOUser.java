package ru.remmy.hibernate.dao;
//https://habr.com/post/271115/
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ru.remmy.hibernate.entities.*;
import ru.remmy.hibernate.idao.IUsersDAO;
import ru.remmy.hibernate.utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;

public class DAOUser implements IUsersDAO {

    public long saveUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        session.saveOrUpdate(user);
        return (long)user.getId();
    }

    public boolean isExist(String login) {
        boolean exist = false;
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Criteria userCriteria = session.createCriteria(User.class);
            userCriteria.add(Restrictions.eq("login", login));
            if (userCriteria.uniqueResult() != null)
                exist = true;
            else
                exist = false;
            session.close();
        } catch (HibernateException ex) {
            return false;
        }
        return exist;
    }

    public User getUser(String login) {
        User user = null;
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Criteria userCriteria = session.createCriteria(User.class);
            userCriteria.add(Restrictions.eq("login", login));
            user = (User) userCriteria.uniqueResult();
        } catch (HibernateException ex) {
            return null;
        } finally {
            if (session != null)
                session.close();
        }
        return user;
    }

    public User getUsers(String id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        User user = (User) session.get(User.class, id);
        session.close();
        return user;
    }

    public User getUsersByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        User user = (User) session.get(User.class, name);
        session.close();
        return user;
    }

    public void createUsers(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void updateUsers(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Criteria userCriteria = session.createCriteria(User.class);
        userCriteria.add(Restrictions.eq("id", user.getId()));
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void deleteUsersById(String id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(id);
        tx1.commit();
        session.close();
    }

    public UsersList getUsersList() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        UsersList list = new UsersList((ArrayList<User>) session.createQuery("FROM User").list());
        session.close();
        return list;
    }
}