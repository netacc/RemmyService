package ru.remmy;

import org.springframework.stereotype.Service;
import ru.remmy.hibernate.dao.TasksList;
import ru.remmy.hibernate.dao.UsersList;
import ru.remmy.hibernate.entities.*;
import ru.remmy.hibernate.services.TaskServices;
import ru.remmy.hibernate.services.UserServices;
import javax.ws.rs.*;
import java.net.URI;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

@Service
@Path("/")
public class RestService {

    private UserServices userService;
    private TaskServices taskService;

    @Context
    private HttpHeaders requestHeaders;

    private String getHeaderVersion() {
        return requestHeaders.getRequestHeader("version").get(0);
    }

    //--------------------------------------GET LIST---------------------------------------------
    @GET
    @Path("/service/user/")
    @Produces(MediaType.APPLICATION_JSON)
    public UsersList getUserList(){
        return userService.getUsersList();
    }

    @GET
    @Path("/service/task/")
    @Produces(MediaType.APPLICATION_JSON)
    public TasksList getTaskList(){
        return taskService.getTaskList();
    }

    //--------------------------------------GET ONE ID-------------------------------------------
    @GET
    @Path("/service/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") String id){
        return userService.getUsers(id);
    }

    @GET
    @Path("/service/task/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Task getTask(@PathParam("id") String id){
        return taskService.findTasksById(id);
    }

    //---------------------------------------SAVE OR UPDATE--------------------------------------
   @PUT
   @Path("/service/updateTask/{Task}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response updateTask(Task task) {
        taskService.updateTasks(task);
        return Response.created(URI.create("/service/task/"
                + task.getId())).build();
   }

    @PUT
    @Path("/service/updateUser/{User}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        userService.updateUsers(user);
        return Response.created(URI.create("/service/user/"
                + user.getId())).build();
    }

    //-----------------------------------------DELETE--------------------------------------------
    @PUT
    @Path("/service/deleteUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") String id) {
        userService.deleteUsersById(id);
        return Response.created(URI.create("/service/user/")).build();
    }

    @PUT
    @Path("/service/deleteTask/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTask(@PathParam("id") String id) {
        taskService.deleteTasks(id);
        return Response.created(URI.create("/service/task/")).build();
    }

    public void setUserService(UserServices userService) {
       this.userService = userService;
    }

    public void setTaskService(TaskServices taskService) {
       this.taskService = taskService;
    }

    public UserServices getUserService() {
        return userService;
    }

    public TaskServices getTaskService() {
        return taskService;
    }
}
