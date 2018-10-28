package ru.remmy.hibernate.dao;

import ru.remmy.hibernate.entities.GroupEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name="GroupsEntities")
public class GroupList extends ArrayList<GroupEntity> {
    public GroupList() {
    }

    public GroupList(ArrayList<GroupEntity> list) {
        for (GroupEntity entity : list){
            this.add(entity);
        }
    }
}
