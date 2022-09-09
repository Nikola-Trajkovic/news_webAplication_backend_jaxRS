package com.example.Projekat_web.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VestTag {


    private Integer vest_id;


    private Integer tag_id;


    public VestTag() {
    }

    public VestTag(Integer vest_id, Integer tag_id) {
        this.vest_id = vest_id;
        this.tag_id = tag_id;
    }

    public Integer getVest_id() {
        return vest_id;
    }

    public void setVest_id(Integer vest_id) {
        this.vest_id = vest_id;
    }

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }
}
