package rs.raf.demo.resources;

import rs.raf.demo.entities.News;
import rs.raf.demo.entities.NewsTag;
import rs.raf.demo.entities.Tag;
import rs.raf.demo.services.NewsTagService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/newstag")
public class NewsTagResource {

    @Inject
    private NewsTagService newsTagService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tag> all(@PathParam("id") Integer id)
    {
        return this.newsTagService.allTagsForNews(id);
    }

    @GET
    @Path("/tag/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> allNews(@PathParam("id") Integer id)
    {
        return this.newsTagService.allNewsForTag(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public NewsTag create(@Valid NewsTag newsTag) {
        return this.newsTagService.addNewsTag(newsTag);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id){
        this.newsTagService.delete(id);
    }



//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public News find(@PathParam("id") Integer id) {
//        return this.newsTagService.findNews(id);
//    }
}
