package rs.raf.demo.resources;

import rs.raf.demo.entities.News;
import rs.raf.demo.entities.Tag;
import rs.raf.demo.entities.User;
import rs.raf.demo.requests.LoginRequest;
import rs.raf.demo.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User create(@Valid User user) {
        return this.userService.addUser(user);
    }


    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@Valid LoginRequest loginRequest)
    {
        return Response.ok(this.userService.login(loginRequest)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all(){
        return Response.ok(this.userService.allUsers()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User find(@PathParam("id") Integer id) {
        return this.userService.findUser(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Integer id){
        this.userService.delete(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void updateNews(User user){
        this.userService.updateUser(user);
    }
}
