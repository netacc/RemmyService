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

    public TasksEntity updateTasks(TasksEntity task) {
        taskDao.updateTasks(task);
        return task;
    }

    public void deleteTasks(TasksEntity task) {
        taskDao.deleteTasks(task);
    }

    public TasksList getTaskList(String doerId){
        return taskDao.getTaskList(doerId);
    }
}
