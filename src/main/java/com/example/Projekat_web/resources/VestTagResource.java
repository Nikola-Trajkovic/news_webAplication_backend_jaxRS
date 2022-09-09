package com.example.Projekat_web.resources;


import com.example.Projekat_web.entities.VestTag;
import com.example.Projekat_web.services.VestTagService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("vestTag")
public class VestTagResource {

    @Inject
    private VestTagService vestTagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<VestTag> getVestTag() {
        return this.vestTagService.getVestTag();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public VestTag addVestTag(@Valid VestTag vestTag) {
        return this.vestTagService.addVestTag(vestTag);
    }

}
