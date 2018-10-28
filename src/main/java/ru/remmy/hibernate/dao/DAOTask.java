package ru.remmy.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.remmy.hibernate.entities.TasksEntity;
import ru.remmy.hibernate.idao.ITasksDAO;
import ru.remmy.hibernate.utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;


public class DAOTask implements ITasksDAO {

    public TasksEntity findTasksById(String id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(TasksEntity.class, Integer.parseInt(id));
    }

    public void createTasks(TasksEntity task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(task);
        tx1.commit();
        session.close();
    }

    public void updateTasks(TasksEntity task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(task);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteTasks(TasksEntity task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(task);
        tx1.commit();
        session.close();
    }

    public TasksList getTaskList(String doerId) {
        return new TasksList((ArrayList<TasksEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM TasksEntity where doerId = "+doerId).list());
    }
}
