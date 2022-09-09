package rs.raf.demo.services;

import rs.raf.demo.entities.News;
import rs.raf.demo.repositories.news.NewsRepository;

import javax.inject.Inject;
import java.util.List;

public class NewsService {

    public NewsService() {
    }

    @Inject
    private NewsRepository newsRepository;

    public News addNews(News news){
        return this.newsRepository.addNews(news);
    }

    public List<News> allNews(){
        return this.newsRepository.allNews();
    }

    public List<News> findNewsByCategory(Integer id){
        return this.newsRepository.findNewsByCategory(id);
    }

    public News findNews(Integer id){
        return this.newsRepository.findNews(id);
    }

    public News incrementVisits(News news){
        return this.newsRepository.incrementVisits(news);
    }

    public void delete(Integer id){
        this.newsRepository.delete(id);
    }

    public void updateNews(News news){
        this.newsRepository.updateNews(news);
    }

    public List<News> sortedNews(){
        return this.newsRepository.sortedNews();
    }
}
