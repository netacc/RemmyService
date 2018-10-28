package ru.remmy.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.remmy.hibernate.entities.GroupEntity;
import ru.remmy.hibernate.idao.IGroupDAO;
import ru.remmy.hibernate.utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;

public class DAOGroup implements IGroupDAO {
    @Override
    public GroupEntity findGroupsById(String id) {
            return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(GroupEntity.class, Integer.parseInt(id));
    }

    @Override
    public void createGroups(GroupEntity group) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(group);
        tx1.commit();
        session.close();
    }

    @Override
    public void updateGroups(GroupEntity group) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(group);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteGroups(GroupEntity group) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(group);
        tx1.commit();
        session.close();
    }

    @Override
    public GroupList getGroupList(String owner_id) {
        return new GroupList((ArrayList<GroupEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM GroupEntity where owner_id = "+owner_id).list());
    }
}
