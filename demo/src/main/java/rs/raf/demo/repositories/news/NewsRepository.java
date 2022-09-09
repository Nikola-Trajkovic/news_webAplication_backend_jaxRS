package rs.raf.demo.repositories.news;

import rs.raf.demo.entities.News;

import java.util.List;

public interface NewsRepository {

    News addNews(News news);

    List<News> allNews();

    News findNews(Integer id);

    News incrementVisits(News news);

    List<News> findNewsByCategory(Integer id);

    void delete(Integer id);

    void updateNews(News news);

    List<News> sortedNews();
}
