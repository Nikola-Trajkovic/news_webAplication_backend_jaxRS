package com.example.Projekat_web.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Vest {

    private Integer id;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String naslov;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String text;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String vremeKreiranja;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private Integer brojPoseta;


    private Integer fk_korisnik;


    private Integer fk_kategorija;

    public Vest() {
    }

    public Vest(Integer id, String naslov, String text, String vremeKreiranja, Integer brojPoseta, Integer fk_korisnik, Integer fk_kategorija) {
        this.id = id;
        this.naslov = naslov;
        this.text = text;
        this.vremeKreiranja = vremeKreiranja;
        this.brojPoseta = brojPoseta;
        this.fk_korisnik = fk_korisnik;
        this.fk_kategorija = fk_kategorija;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getVremeKreiranja() {
        return vremeKreiranja;
    }

    public void setVremeKreiranja(String vremeKreiranja) {
        this.vremeKreiranja = vremeKreiranja;
    }

    public Integer getBrojPoseta() {
        return brojPoseta;
    }

    public void setBrojPoseta(Integer brojPoseta) {
        this.brojPoseta = brojPoseta;
    }

    public Integer getFk_korisnik() {
        return fk_korisnik;
    }

    public void setFk_korisnik(Integer fk_korisnik) {
        this.fk_korisnik = fk_korisnik;
    }

    public Integer getFk_kategorija() {
        return fk_kategorija;
    }

    public void setFk_kategorija(Integer fk_kategorija) {
        this.fk_kategorija = fk_kategorija;
    }
}
