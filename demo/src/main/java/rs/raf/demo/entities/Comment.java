package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

public class Comment {

    private Integer comment_id;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String author;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String content;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String date;

    private Integer news_id;

    public Comment(Integer comment_id, String author, String content, String date) {
        this.comment_id = comment_id;
        this.author = author;
        this.content = content;
        this.date = date;
    }

    public Comment() {
    }

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public Integer getNews_id() {
        return news_id;
    }

    public void setNews_id(Integer news_id) {
        this.news_id = news_id;
    }
}
