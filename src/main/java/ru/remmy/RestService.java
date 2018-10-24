package ru.remmy;

import org.springframework.stereotype.Service;
import ru.remmy.hibernate.dao.TasksList;
import ru.remmy.hibernate.dao.UsersList;
import ru.remmy.hibernate.entities.TasksEntity;
import ru.remmy.hibernate.entities.UsersEntity;
import ru.remmy.hibernate.idao.ITasksDAO;
import ru.remmy.hibernate.idao.IUsersDAO;
import ru.remmy.hibernate.services.TaskServices;
import ru.remmy.hibernate.services.UserServices;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;

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

    @GET
    @Path("/service/user/")
    @Produces(MediaType.APPLICATION_JSON)
    public UsersList getUserList(){
        return new UsersList(userService.getUsersList());
    }

    @GET
    @Path("/service/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UsersEntity getUser(@PathParam("id") String id){
        return userService.getUsers(id);
    }

    @GET
    @Path("/service/task/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TasksEntity getTask(@PathParam("id") String id){
        return taskService.findTasksById(id);
    }

//    @GET
//    @Path("/service/tasklist/{doerId}")
//   @Produces(MediaType.APPLICATION_JSON)
//    public TaskList getTaskList(@PathParam("doerId") String doerId){
//        TaskList taskList = taskServices.getTaskList(doerId);
//        return taskList;
//    }
/*
    @POST
    @Path("/service/task/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTask(TasksEntity task) {
        taskServices.createTasks(task);
        return Response.created(URI.create("/service/task/"
                + createTask(task.getId()))).build();
    }
*/
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//   public Response updateTask(Task task) {
//      Task updTask = taskServices.update(task);
//        return Response.created(URI.create("/service/task/"
//                + updTask.getId())).build();
//   }

   @GET
   @Path("/service/tasklist/")
   @Produces(MediaType.APPLICATION_JSON)
    public TasksList getTaskList() throws ParseException {
        return taskService.getTaskList();
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
