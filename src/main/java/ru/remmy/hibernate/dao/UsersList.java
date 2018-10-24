package ru.remmy.hibernate.dao;

import ru.remmy.hibernate.entities.UsersEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name="users")
public class UsersList extends ArrayList<UsersEntity> {

    public UsersList(){}

    public UsersList(ArrayList<UsersEntity> list){
        for(UsersEntity entity : list){
            this.add(entity);
        }
    }
}
