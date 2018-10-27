package ru.remmy.hibernate.services;

import ru.remmy.hibernate.dao.DAOTask;
import ru.remmy.hibernate.entities.*;
import ru.remmy.hibernate.idao.ITasksDAO;
import ru.remmy.hibernate.dao.TasksList;

public class TaskServices {

    private ITasksDAO taskDao = new DAOTask();

    public Task findTasksById(String id) {
        return taskDao.findTasksById(id);
    }

    public void createTasks(Task task) {
        taskDao.createTasks(task);
    }

    public void updateTasks(Task task) {
        taskDao.updateTasks(task);
    }

    public void deleteTasks(String id) {
        taskDao.deleteTasksById(id);
    }

    public TasksList getTaskList(){
        return taskDao.getTaskList();
    }
}
