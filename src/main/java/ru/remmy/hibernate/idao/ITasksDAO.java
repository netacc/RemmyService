package ru.remmy.hibernate.idao;

import ru.remmy.hibernate.dao.TasksList;
import ru.remmy.hibernate.entities.TasksEntity;

public interface ITasksDAO {
    TasksEntity findTasksById(String id);
    void createTasks(TasksEntity task);
    void updateTasks(TasksEntity task);
    void deleteTasksById(String id);
    TasksList getTaskList();
}
