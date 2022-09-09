package rs.raf.demo.services;


import rs.raf.demo.entities.News;
import rs.raf.demo.entities.NewsTag;
import rs.raf.demo.entities.Tag;
import rs.raf.demo.repositories.newsTag.NewsTagRepository;

import javax.inject.Inject;
import java.util.List;

public class NewsTagService {

    public NewsTagService() {
    }

    @Inject
    private NewsTagRepository newsTagRepository;

    public List<Tag> allTagsForNews(Integer id){
        return this.newsTagRepository.allTagsForNews(id);
    }

    public List<News> allNewsForTag(Integer id){
        return this.newsTagRepository.allNewsForTag(id);
    }

    public NewsTag addNewsTag(NewsTag newsTag){
        return this.newsTagRepository.addNewsTag(newsTag);
    }

    public void delete(Integer id){
        this.newsTagRepository.delete(id);
    }
}
