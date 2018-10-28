package ru.remmy;

import org.springframework.stereotype.Service;
import ru.remmy.hibernate.dao.GroupList;
import ru.remmy.hibernate.dao.TasksList;
import ru.remmy.hibernate.dao.UsersList;
import ru.remmy.hibernate.entities.GroupEntity;
import ru.remmy.hibernate.entities.TasksEntity;
import ru.remmy.hibernate.entities.UsersEntity;
import ru.remmy.hibernate.idao.ITasksDAO;
import ru.remmy.hibernate.idao.IUsersDAO;
import ru.remmy.hibernate.services.GroupServices;
import ru.remmy.hibernate.services.TaskServices;
import ru.remmy.hibernate.services.UserServices;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.text.ParseException;

@Service
@Path("/")
public class RestService {

    private UserServices userService;
    private TaskServices taskService;
    private GroupServices groupService;

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

    @POST
    @Path("/service/user/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UsersEntity user) {
        userService.createUsers(user);
        return Response.ok(user, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/service/task/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TasksEntity getTask(@PathParam("id") String id){
        return taskService.findTasksById(id);
    }

    @POST
    @Path("/service/task/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTask(TasksEntity task) {
        taskService.createTasks(task);
        return Response.ok(task, MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("/service/task/put")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTask(TasksEntity task) {
        TasksEntity updTask = taskService.updateTasks(task);
        return Response.ok(task, MediaType.APPLICATION_JSON).build();
    }
    @DELETE
    @Path("/service/task/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteTask(TasksEntity task) {
        taskService.deleteTasks(task);
        return Response.ok(task, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/service/tasklist/{doerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public TasksList getTaskList(@PathParam("doerId") String doerId) throws ParseException {
        return new TasksList(taskService.getTaskList(doerId));
    }

    @POST
    @Path("/service/group/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGroup(GroupEntity group) {
        groupService.createGroups(group);
        return Response.ok(group, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/service/group/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public GroupEntity getGroup(@PathParam("id") String id){
        return groupService.findGroupsById(id);
    }

    @GET
    @Path("/service/groups/{owner}")
    @Produces(MediaType.APPLICATION_JSON)
    public GroupList getGroupList(@PathParam("owner") String owner_id) throws ParseException {
        return new GroupList(groupService.getGroupList(owner_id));
    }

    @DELETE
    @Path("/service/group/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteGroup(GroupEntity group) {
        groupService.deleteGroups(group);
        return Response.ok(group, MediaType.APPLICATION_JSON).build();
    }

    public void setGroupService(GroupServices groupService) {
        this.groupService = groupService;
    }

    public void setUserService(UserServices userService) {
       this.userService = userService;
    }

    public void setTaskService(TaskServices taskService) {
       this.taskService = taskService;
    }

    public GroupServices getGroupService() {
        return groupService;
    }

    public UserServices getUserService() {
        return userService;
    }

    public TaskServices getTaskService() {
        return taskService;
    }
}
