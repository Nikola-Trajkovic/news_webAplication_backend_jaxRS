package rs.raf.demo.repositories.newsTag;

import rs.raf.demo.entities.News;
import rs.raf.demo.entities.NewsTag;
import rs.raf.demo.entities.Tag;

import java.util.List;

public interface NewsTagRepository {

    List<Tag> allTagsForNews(Integer id);

    List<News> allNewsForTag(Integer id);

    NewsTag addNewsTag(NewsTag newsTag);

    void delete(Integer id);
}
