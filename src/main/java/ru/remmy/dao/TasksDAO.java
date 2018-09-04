package ru.remmy.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.remmy.entities.Task;
import ru.remmy.entities.TaskList;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public class TasksDAO implements ITasksDAO {

    Map<String, Task> profsMap = new HashMap<String, Task>();

    DataSource dataSource;

    private SimpleJdbcInsert insertTask;
    private JdbcTemplate templateTask;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.templateTask = new JdbcTemplate(this.dataSource);
        this.insertTask = new SimpleJdbcInsert(this.dataSource).withTableName("tasks");
        this.insertTask.setGeneratedKeyName("id");
    }

    @Override
    public Task createTask(Task task) {
        if (task.getId() == 0) {
            Map<String, Object> parameters = new HashMap<String, Object>(3);

            parameters.put("doerId", task.getDoerId());
            if (task.getHeader() != null)
                parameters.put("header", task.getHeader());
            if (task.getText() != null)
                parameters.put("text", task.getText());
            if (task.getTaskDate() != null)
                parameters.put("taskDate", task.getTaskDate());

            Number key = insertTask.executeAndReturnKey(parameters);
            task.setId(key.intValue());
            return task;
        } else {
            String sqlUpdate = String
                    .format("UPDATE tasks SET header = %s, text = %s, taskDate = %s  WHERE id = %s;",
                            "'" + task.getHeader() + "'", "'" +  task.getText()+ "'",
                            "'" + task.getTaskDate()+"'", "'" + task.getId()+ "'");
            templateTask.update(sqlUpdate);
            return task;
        }

    }

    @Override
    public Task getTask(String id) {
        Task task = (Task)templateTask.queryForObject(
                "select tasks.*, users.name from tasks, users where doerId = users.id and tasks.id = '" + id + "'",
                new RowMapper<Task>() {
                    public Task mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        Task Task = new Task();
                        Task.setId(rs.getInt("id"));
                        Task.setDoerId(rs.getInt("doerId"));
                        Task.setHeader(rs.getString("header"));
                        Task.setText(rs.getString("text"));
                        try {
                            Task.setTaskDate(rs.getString("taskDate"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Task.setDoerInitials(rs.getString("name"));
                        return Task;
                    }
                });

        return task;
    }

    @Override
    public boolean removeTask(String id) {
        return false;
    }

//    @Override
//    public ArrayList<Task> getTaskList() {
//        ArrayList<Task> taskList = (ArrayList<Task>) templateTask.query(
//                "SELECT * FROM tasks;", new RowMapper<Task>() {
//                    public Task mapRow(ResultSet rs, int rowNum)
//                            throws SQLException {
//                        Task task = new Task();
//                        task.setId(rs.getInt("id"));
//                        task.setDoerId(rs.getInt("doerId"));
//                        task.setHeader(rs.getString("header"));
//                        task.setText(rs.getString("text"));
//                        task.setTaskDate(rs.getString("taskDate"));
//                        return task;
//                    }
//                });
//
//        return taskList;
//    }
    @Override
    public TaskList getTaskList(String doerId){
        String sql;
        String sqlAll = "select tasks.*, users.name from tasks, users where doerId = users.id;";
        String sql1 = "select tasks.*, users.name from tasks, users where doerId = users.id and doerId = '" + doerId + "'";
        if (Integer.parseInt(doerId)  == 0){
            sql = sqlAll;
        } else {
            sql = sql1;
        }
        ArrayList<Task> _taskList = (ArrayList<Task>) templateTask.query(sql, new RowMapper<Task>() {
                    public Task mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        Task task = new Task();
                            task.setId(rs.getInt("id"));
                            task.setDoerId(rs.getInt("doerId"));
                            task.setHeader(rs.getString("header"));
                            task.setText(rs.getString("text"));
                        try {
                            task.setTaskDate(rs.getString("taskDate"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        task.setDoerInitials(rs.getString("name"));
                        task.setDoerName(rs.getString("name"));

                        return task;
                    }
                });
        TaskList taskList = new TaskList();
        taskList.addAll(_taskList);
        return taskList;
    }
//    @Override
//    public TaskList getDoerTaskList(String doerId){
//        ArrayList<Task> _taskList = (ArrayList<Task>) templateTask.query(
//                "select tasks.*, users.name from tasks, users where doerId = users.id and doerId = '" + doerId + "'", new RowMapper<Task>() {
//                    public Task mapRow(ResultSet rs, int rowNum)
//                            throws SQLException {
//                        Task task = new Task();
//                        task.setId(rs.getInt("id"));
//                        task.setDoerId(rs.getInt("doerId"));
//                        task.setHeader(rs.getString("header"));
//                        task.setText(rs.getString("text"));
//                        try {
//                            task.setTaskDate(rs.getString("taskDate"));
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                        task.setDoerInitials(rs.getString("name"));
//                        task.setDoerName(rs.getString("name"));
//                        return task;
//                    }
//                });
//        TaskList taskList = new TaskList();
//        taskList.addAll(_taskList);
//        return taskList;
//    }

    @Override
    public Task updateTask(Task task) {
        if (task != null && task.getId() != 0) {
            Task oldTask = getTask(Integer.toString(task.getId()));
            String sqlUpdate = String
                    .format("UPDATE tasks SET header = %s, text = %s, taskDate = %s  WHERE id = %s",
                            "'" + task.getHeader() + "'", "'" +  task.getText()+ "'",
                            "'" + task.getTaskDate()+"'", "'" + task.getId()+ "'");
            templateTask.update(sqlUpdate);
            return oldTask;
        } else {
            return null;
        }
    }
}
