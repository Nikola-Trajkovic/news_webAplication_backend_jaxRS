package com.example.Projekat_web.resources;


import com.example.Projekat_web.entities.Vest;
import com.example.Projekat_web.services.VestService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/vest")
public class VestResource {

    @Inject
    private VestService vestService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vest> getVest() {
        return this.vestService.getVest();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Vest addVest(@Valid Vest vest) {
        return this.vestService.addVest(vest);
    }


    @GET
    @Path("/date")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vest> getVestDate() {
        return this.vestService.getVestDate();
    }

    @GET
    @Path("/najcitanije")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vest> getNajcitanije() {
        return this.vestService.getVestPosete();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Vest updateBrojPoseta(Vest vest) {
        return this.vestService.updatevest(vest);
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateVest(Vest vest) {
         this.vestService.updateVestDrugi(vest);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vest getVestDate(@PathParam("id") Integer id) {
        return this.vestService.findVest(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteVest(@PathParam("id") Integer id){
        this.vestService.deleteVest(id);
    }

}
