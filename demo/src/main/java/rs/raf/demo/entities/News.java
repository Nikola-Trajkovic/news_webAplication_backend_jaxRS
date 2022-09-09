package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class News {

    private Integer news_id;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String title;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String content;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String date;


    private Integer visits;

    private Integer user_id;

    private Integer category_id;


    public News(Integer news_id, String title, String content, String date, Integer visits, Integer user_id, Integer category_id) {
        this.news_id = news_id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.visits = visits;
        this.user_id = user_id;
        this.category_id = category_id;
    }

    public News() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getNews_id() {
        return news_id;
    }

    public void setNews_id(Integer news_id) {
        this.news_id = news_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }
}
