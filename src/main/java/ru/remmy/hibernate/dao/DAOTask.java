package ru.remmy.hibernate.dao;
//https://habr.com/post/271115/
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import ru.remmy.hibernate.entities.*;
import ru.remmy.hibernate.idao.ITasksDAO;
import ru.remmy.hibernate.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class DAOTask implements ITasksDAO {

    @Autowired
    private DAOUser userDao;

    public List<Task> getTasksByUserName(String userName) {
        List<Task> tasksList = null;
        try {
            User user = userDao.getUsersByName(userName);
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.refresh(user);
            //tasksList = user.getTask();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.getMessage();
        }
        return tasksList;
    }

    public Task findTasksById(String id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Task task = (Task) session.load(Task.class, Integer.parseInt(id));
        return task;
    }

    public void createTasks(Task task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(task);
        tx1.commit();
        session.close();
    }

    public void updateTasks(Task task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(task);
        tx1.commit();
        session.close();
    }

    public void deleteTasksById(String id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(Integer.parseInt(id));
        tx1.commit();
        session.close();
    }

    public TasksList getTaskList() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        TasksList all = (TasksList) session.createQuery("from Task").list();
        session.close();
        return new TasksList(all);
    }
}
