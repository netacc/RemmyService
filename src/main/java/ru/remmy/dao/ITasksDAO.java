package ru.remmy.dao;

import ru.remmy.entities.Task;
import ru.remmy.entities.TaskList;

import javax.sql.DataSource;

public interface ITasksDAO {
    void setDataSource(DataSource dataSource);
    Task createTask(Task task);
    Task getTask(String id);
    Task updateTask(Task task);
    boolean removeTask(String id);
//    TaskList getTaskList();
    TaskList getTaskList(String doerId);
}
