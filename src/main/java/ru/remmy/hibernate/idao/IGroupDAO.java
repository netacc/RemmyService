package ru.remmy.hibernate.idao;

import ru.remmy.hibernate.dao.GroupList;
import ru.remmy.hibernate.entities.GroupEntity;

public interface IGroupDAO {
    GroupEntity findGroupsById(String id);
    void createGroups(GroupEntity group);
    void updateGroups(GroupEntity group);
    void deleteGroups(GroupEntity group);
    GroupList getGroupList(String owner_id);
}
