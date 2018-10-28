package ru.remmy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.WebContext;
import ru.remmy.hibernate.dao.TasksList;
import ru.remmy.hibernate.dao.UsersList;
import ru.remmy.hibernate.entities.*;
import ru.remmy.hibernate.services.RegistrationService;
import ru.remmy.hibernate.services.TaskServices;
import ru.remmy.hibernate.services.UserServices;
import ru.remmy.hibernate.utils.ThymeleafTemplateUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import java.io.IOException;
import java.net.URI;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

@Service
@Path("/")
public class RestService {

    @Autowired
    private UserServices userService;

    @Autowired
    private TaskServices taskService;

    @Autowired
    private RegistrationService registrationService;

    @Context
    private HttpHeaders requestHeaders;

    private String getHeaderVersion() {
        return requestHeaders.getRequestHeader("version").get(0);
    }

    //--------------------------------------Sequrity---------------------------------------------
    @GET
    @Path("/service/userRegistration/{name}, {login}, {password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Long userRegistration(
            @PathParam("name") String name,
            @PathParam("login")String login,
            @PathParam("password")String password) {
        return userService.userRegistration(name, login, password);
    }

    @Autowired
    @Qualifier("authManager")
    private AuthenticationManager authenticationManager;

    @POST
    @Path("/service/user/")
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public void registration(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "name", required = true) String name,
                             @RequestParam(value = "login", required = true) String login,
                             @RequestParam(value = "password", required = true) String password) {

        Long id = registrationService.userRegistration(name, login, password);
        if (id > 0) {
            try {
                SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password)));
                response.sendRedirect(request.getContextPath() + "/myTasks");
            } catch (IOException ex) {
                return;
            }
        }
        if (id == 0) {
            try {
                response.getOutputStream().print("This login already exist");
            } catch (IOException ex) {

            }
        }
    }

    @RequestMapping(value = "toRegistration", method = RequestMethod.GET)
    public void toRegistration(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            WebContext webContext = new WebContext(request, response, request.getSession().getServletContext());
            ThymeleafTemplateUtil.getTemplateEngine().process("registration", webContext, response.getWriter());
        } catch (Exception ex) {
            return;
        }
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
   @POST
   @Path("/service/updateTask/{Task}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response updateTask(Task task) {
        taskService.updateTasks(task);
        return Response.created(URI.create("/service/task/"
                + task.getId())).build();
   }

    @POST
    @Path("/service/updateUser/{User}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        userService.updateUsers(user);
        return Response.created(URI.create("/service/user/"
                + user.getId())).build();
    }

    //-----------------------------------------DELETE--------------------------------------------
    @DELETE
    @Path("/service/deleteUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") String id) {
        userService.deleteUsersById(id);
        return Response.created(URI.create("/service/user/")).build();
    }

    @DELETE
    @Path("/service/deleteTask/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTask(@PathParam("id") String id) {
        taskService.deleteTasks(id);
        return Response.created(URI.create("/service/task/")).build();
    }
}
