package rs.raf.demo.entities;

public class NewsTag {

    private Integer news_id;

    private Integer tag_id;

    public NewsTag(Integer news_id, Integer tag_id) {
        this.news_id = news_id;
        this.tag_id = tag_id;
    }

    public NewsTag() {
    }

    public Integer getNews_id() {
        return news_id;
    }

    public void setNews_id(Integer news_id) {
        this.news_id = news_id;
    }

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }
}
