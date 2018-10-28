package ru.remmy.hibernate.idao;

import ru.remmy.hibernate.dao.TasksList;
import ru.remmy.hibernate.entities.TasksEntity;

public interface ITasksDAO {
    TasksEntity findTasksById(String id);
    void createTasks(TasksEntity task);
    void updateTasks(TasksEntity task);
    void deleteTasks(TasksEntity task);
    TasksList getTaskList(String doerId);
}
