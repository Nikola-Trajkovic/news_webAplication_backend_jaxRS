package com.example.Projekat_web.resources;

import com.example.Projekat_web.entities.Korisnik;
import com.example.Projekat_web.entities.Login.Login;
import com.example.Projekat_web.services.KorisnikService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/korisnik")
public class KorisnikResource {

    @Inject
    private KorisnikService korisnikService;

    @GET
    @Path("/admin")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Korisnik> getKorisnik() {
        return this.korisnikService.getKorisnik();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik addKorisnik(@Valid Korisnik korisnik) {
        return this.korisnikService.addKorisnik(korisnik);
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Login login) {
        return Response.ok(this.korisnikService.login(login)).build();
    }

    @DELETE
    @Path("/admin/{id}")
    public void deleteKorisnik(@PathParam("id") Integer id){
        this.korisnikService.deleteKorisnik(id);
    }

    @PUT
    @Path("/admin")
    public Korisnik updateKorisnik(Korisnik korisnik){
        return this.korisnikService.updatKorisnik(korisnik);
    }

    @POST
    @Path("/logOut")
    public void logOut(){
        this.korisnikService.logOut();
    }



}
