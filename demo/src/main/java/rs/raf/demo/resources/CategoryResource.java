package rs.raf.demo.resources;

import rs.raf.demo.entities.Category;
import rs.raf.demo.entities.News;
import rs.raf.demo.services.CategoryService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/categories")
public class CategoryResource {

    @Inject
    private CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> all()
    {
        return this.categoryService.allCategories();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Category create(@Valid Category category) {
        return this.categoryService.addCategory(category);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category find(@PathParam("id") Integer id) {
        return this.categoryService.findCategory(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Integer id){
        this.categoryService.delete(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void update(Category category){
        this.categoryService.update(category);
    }
}
