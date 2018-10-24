package ru.remmy.hibernate.services;

import ru.remmy.hibernate.dao.DAOTask;
import ru.remmy.hibernate.idao.ITasksDAO;
import ru.remmy.hibernate.entities.TasksEntity;
import ru.remmy.hibernate.dao.TasksList;

public class TaskServices {

    private ITasksDAO taskDao = new DAOTask();

    public TasksEntity findTasksById(String id) {
        return taskDao.findTasksById(id);
    }

    public void createTasks(TasksEntity task) {
        taskDao.createTasks(task);
    }

    public void updateTasks(TasksEntity task) {
        taskDao.updateTasks(task);
    }

    public void deleteTasks(String id) {
        taskDao.deleteTasksById(id);
    }

    public TasksList getTaskList(){
        return taskDao.getTaskList();
    }
}
