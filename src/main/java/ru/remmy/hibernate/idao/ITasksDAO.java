package ru.remmy.hibernate.idao;

import ru.remmy.hibernate.dao.TasksList;
import ru.remmy.hibernate.entities.*;

public interface ITasksDAO {
    Task findTasksById(String id);
    void createTasks(Task task);
    void updateTasks(Task task);
    boolean deleteTasksById(String id);
    TasksList getTaskList();
}
