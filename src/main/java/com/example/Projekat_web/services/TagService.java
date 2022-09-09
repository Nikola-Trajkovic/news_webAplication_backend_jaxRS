package com.example.Projekat_web.services;

import com.example.Projekat_web.entities.Tag;
import com.example.Projekat_web.entities.Vest;
import com.example.Projekat_web.repositories.tag.TagInterface;

import javax.inject.Inject;
import java.util.List;

public class TagService {

    public TagService() {
    }

    @Inject
    private TagInterface tagInterface;

    public List<Tag> getTag(Integer vestId){
        return this.tagInterface.getTag(vestId);
    }

    public Tag addTag(Tag tag){
        return this.tagInterface.addTag(tag);
    }

    public List<Tag> getAll(){
        return this.tagInterface.getAll();
    }

    public List<Vest> tagVest(Integer id){
        return this.tagInterface.tagVest(id);
    }

}
