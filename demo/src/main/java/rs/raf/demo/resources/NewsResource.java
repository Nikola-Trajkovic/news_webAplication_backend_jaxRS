package rs.raf.demo.resources;

import rs.raf.demo.entities.News;
import rs.raf.demo.services.NewsService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/news")
public class NewsResource {

    @Inject
    private NewsService newsService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> all()
    {
        return this.newsService.allNews();
    }

    @GET
    @Path("/sorted")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> sorted()
    {
        return this.newsService.sortedNews();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public News create(@Valid News news) {
        return this.newsService.addNews(news);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public News find(@PathParam("id") Integer id) {
        return this.newsService.findNews(id);
    }

    @GET
    @Path("/category/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> getNewsByCategory(@PathParam("id") Integer id) {
        return this.newsService.findNewsByCategory(id);
    }

    @PUT
    @Path("/incr")
    @Produces(MediaType.APPLICATION_JSON)
    public News update(News news){
        return this.newsService.incrementVisits(news);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void updateNews(News news){
        this.newsService.updateNews(news);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Integer id){
        this.newsService.delete(id);
    }

}
