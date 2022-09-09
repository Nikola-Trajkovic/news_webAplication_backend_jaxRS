package com.example.Projekat_web.resources;


import com.example.Projekat_web.entities.Kategorija;
import com.example.Projekat_web.entities.Vest;
import com.example.Projekat_web.services.KategorijaService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/kategorija")
public class KategorijaResource {

    @Inject
    private KategorijaService kategorijaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kategorija> getKategorija() {
        return this.kategorijaService.getKategorija();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Kategorija addKategorija(@Valid Kategorija kategorija) {
        return this.kategorijaService.addKategorija(kategorija);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteKategorija(@PathParam("id") Integer id) {
        this.kategorijaService.deleteKategorija(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Kategorija updateKategorija(Kategorija kategorija) {
        return this.kategorijaService.updateKategorija(kategorija);
    }

    @GET
    @Path("/vest/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vest> vestiKategorija(@PathParam("id")Integer id) {
        return this.kategorijaService.vestKategorija(id);
    }


}
