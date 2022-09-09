package com.example.Projekat_web.services;

import com.example.Projekat_web.entities.VestTag;
import com.example.Projekat_web.repositories.vest_tag.VestTagInterface;

import javax.inject.Inject;
import java.util.List;

public class VestTagService {

    public VestTagService() {
    }

    @Inject
    private VestTagInterface vestTagInterface;

    public List<VestTag> getVestTag(){
        return this.vestTagInterface.getVestTag();
    }

    public VestTag addVestTag(VestTag vestTag){
        return this.vestTagInterface.addvestTag(vestTag);
    }

}
