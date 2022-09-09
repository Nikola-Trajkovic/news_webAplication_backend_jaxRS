package com.example.Projekat_web.resources;



import com.example.Projekat_web.entities.Komentar;
import com.example.Projekat_web.services.KomentarService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/komentar")
public class KomentarResource {

    @Inject
    private KomentarService komentarService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Komentar> getKomentar(@PathParam("id") Integer id) {
        return this.komentarService.getKomentar(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Komentar addKomentar(@Valid Komentar komentar) {
        return this.komentarService.addKomentar(komentar);
    }

}
