package ru.remmy.hibernate.dao;
//https://habr.com/post/271115/
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.remmy.hibernate.entities.*;
import ru.remmy.hibernate.idao.ITasksDAO;
import ru.remmy.hibernate.utils.HibernateSessionFactoryUtil;
import ru.remmy.security.UserDetailsImpl;

import java.util.ArrayList;
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
        Task task;
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Criteria userCriteria = session.createCriteria(Task.class);
            userCriteria.add(Restrictions.eq("id", Integer.parseInt(id)));
            task = (Task) userCriteria.uniqueResult();
        } catch (HibernateException ex) {
            return null;
        } finally {
            if (session != null)
                session.close();
        }
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
        session.saveOrUpdate(task);
        session.close();
    }

    public boolean deleteTasksById(String id) {
        try {
            User currentUser = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getCurrentUser();
            Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
            Task task = new Task();
            task.setId(Integer.parseInt(id));
            session.delete(task);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public TasksList getTaskList() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        TasksList all = new TasksList((ArrayList<Task>) session.createQuery("from Task").list());
        session.close();
        return new TasksList(all);
    }
}
