package com.example.Projekat_web.resources;


import com.example.Projekat_web.entities.Tag;
import com.example.Projekat_web.entities.Vest;
import com.example.Projekat_web.services.TagService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/tag")
public class TagResource {

    @Inject
    private TagService tagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tag> getAll() {
        return this.tagService.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tag> getTag(@PathParam("id") Integer vestId) {
        return this.tagService.getTag(vestId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Tag addTag(@Valid Tag tag) {
        return this.tagService.addTag(tag);
    }

    @GET
    @Path("/{id}/tagvest")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vest> tagVest(@PathParam("id") Integer id){
        return this.tagService.tagVest(id);
    }

}
