package ru.remmy.hibernate.dao;

import ru.remmy.hibernate.entities.User;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name="UsersList")
public class UsersList extends ArrayList<User> {

    public UsersList(){}

    public UsersList(ArrayList<User> list){
        for(User entity : list){
            this.add(entity);
        }
    }
}
