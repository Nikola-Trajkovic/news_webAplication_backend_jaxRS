package com.example.Projekat_web.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Komentar {

    private Integer id;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String autor;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String text;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String datum;


    private Integer fk_vest;

    public Komentar() {
    }

    public Komentar(Integer id, String autor, String text, String datum, Integer fk_vest) {
        this.id = id;
        this.autor = autor;
        this.text = text;
        this.datum = datum;
        this.fk_vest = fk_vest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Integer getFk_vest() {
        return fk_vest;
    }

    public void setFk_vest(Integer fk_vest) {
        this.fk_vest = fk_vest;
    }
}
