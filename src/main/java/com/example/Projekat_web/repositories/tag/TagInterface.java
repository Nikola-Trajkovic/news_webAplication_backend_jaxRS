package com.example.Projekat_web.repositories.tag;

import com.example.Projekat_web.entities.Tag;
import com.example.Projekat_web.entities.Vest;

import java.util.List;

public interface TagInterface {

    List<Tag> getTag(Integer vestId);
    Tag addTag(Tag tag);
    List<Tag> getAll();
    List<Vest> tagVest(Integer id);

}
