package rs.raf.demo.repositories.tag;


import rs.raf.demo.entities.Tag;

import java.util.List;

public interface TagRepository {

    Tag addTag(Tag tag);

    List<Tag> allTags();

    Tag findTag(Integer id);

}
