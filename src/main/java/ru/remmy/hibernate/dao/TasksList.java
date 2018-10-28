package ru.remmy.hibernate.dao;

import ru.remmy.hibernate.entities.TasksEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name="TasksEntities")
public class TasksList extends ArrayList<TasksEntity>{

    public TasksList() {}

    public TasksList(ArrayList<TasksEntity> list){
        for (TasksEntity entity : list){
            this.add(entity);
        }
    }
}