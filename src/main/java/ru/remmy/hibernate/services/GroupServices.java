package ru.remmy.hibernate.services;

import ru.remmy.hibernate.dao.DAOGroup;
import ru.remmy.hibernate.dao.GroupList;
import ru.remmy.hibernate.entities.GroupEntity;
import ru.remmy.hibernate.idao.IGroupDAO;

public class GroupServices {

    private IGroupDAO groupDao = new DAOGroup();

    public GroupEntity findGroupsById(String id) {
        return groupDao.findGroupsById(id);
    }

    public void createGroups(GroupEntity group) {
        groupDao.createGroups(group);
    }

    public void updateGroups(GroupEntity group) {
        groupDao.updateGroups(group);
    }

    public void deleteGroups(GroupEntity group) {
        groupDao.deleteGroups(group);
    }

    public GroupList getGroupList(String owner_id){
        return groupDao.getGroupList(owner_id);
    }
}
