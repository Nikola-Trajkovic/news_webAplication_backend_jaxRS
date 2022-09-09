package com.example.Projekat_web.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Tag {

    private Integer id;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String tag;

    public Tag() {
    }

    public Tag(Integer id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
