package ru.remmy.hibernate.dao;

import ru.remmy.hibernate.entities.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name="tasks")
public class TasksList extends ArrayList<Task>{

    public TasksList(){}

    public TasksList(ArrayList<Task> list){
        for(Task entity : list){
            this.add(entity);
        }
    }

}