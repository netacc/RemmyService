package ru.remmy.hibernate.dao;

import ru.remmy.hibernate.entities.TasksEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name="tasks")
public class TasksList extends ArrayList<TasksEntity>{

    public TasksList(ArrayList<TasksEntity> list){
        for (TasksEntity entity : list){
            this.add(entity);
        }
    }

}