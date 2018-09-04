package ru.remmy;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.stereotype.Service;
import ru.remmy.dao.ITasksDAO;
import ru.remmy.dao.IUsersDAO;
import ru.remmy.dao.UsersDAO;
import ru.remmy.entities.Task;
import ru.remmy.entities.TaskList;
import ru.remmy.entities.User;
import ru.remmy.entities.UserList;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.util.ArrayList;

@Service
@Path("/")
public class RestService {

    // link to our dao object
    private IUsersDAO usersDAO;
    private ITasksDAO tasksDAO;

    // for customersDAO bean property injection
    public IUsersDAO getUsersDAO() {
        return usersDAO;
    }
    public ITasksDAO getTasksDAO() {return tasksDAO; }

    public void setUsersDAO(IUsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }
    public void setTasksDAO(ITasksDAO tasksDAO) {this.tasksDAO = tasksDAO; }

    @Context
    private HttpHeaders requestHeaders;

    private String getHeaderVersion() {
        return requestHeaders.getRequestHeader("version").get(0);
    }

    @GET
    @Path("/service/user/")
    @Produces(MediaType.APPLICATION_JSON)
    public UserList getUserList(){
        UserList userList = usersDAO.getUserList();
        return userList;
    }
    @GET
    @Path("/service/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") String id){
        User user = usersDAO.getUser(id);
        return user;
    }

    @GET
    @Path("/service/task/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Task getTask(@PathParam("id") String id){
        Task task = tasksDAO.getTask(id);
        return task;
    }

//    @GET
//    @Path("/service/tasklist/")
//    @Produces(MediaType.APPLICATION_JSON)
//    public TaskList getTaskList(){
//        TaskList taskList = tasksDAO.getTaskList();
//        return taskList;
//    }

    @GET
    @Path("/service/tasklist/{doerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public TaskList getTaskList(@PathParam("doerId") String doerId){
        TaskList taskList = tasksDAO.getTaskList(doerId);
        return taskList;
    }

    @POST
    @Path("/service/task/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTask(Task task) {
        Task creTask = tasksDAO.createTask(task);
        return Response.created(URI.create("/service/task/"
                + creTask.getId())).build();
    }

//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updateTask(Task task) {
//        Task updTask = tasksDAO.updateTask(task);
//        return Response.created(URI.create("/service/task/"
//                + updTask.getId())).build();
//
//    }
}
