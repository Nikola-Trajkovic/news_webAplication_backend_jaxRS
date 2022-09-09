package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Tag {
    private Integer tag_id;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String tag;

    public Tag(Integer tag_id, String tag) {
        this.tag_id = tag_id;
        this.tag = tag;
    }

    public Tag() {
    }

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
