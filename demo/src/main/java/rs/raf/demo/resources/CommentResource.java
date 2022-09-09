package rs.raf.demo.resources;

import rs.raf.demo.entities.Comment;
import rs.raf.demo.services.CommentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/comments")
public class CommentResource {

    @Inject
    private CommentService commentService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> all(@PathParam("id") Integer id)
    {
        return this.commentService.allComments(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Comment create(@Valid Comment comment) {
        return this.commentService.addComment(comment);
    }

//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Comment find(@PathParam("id") Integer id) {
//        return this.commentService.findComment(id);
//    }
}
