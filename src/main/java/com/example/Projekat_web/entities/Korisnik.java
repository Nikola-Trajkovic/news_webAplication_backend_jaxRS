package com.example.Projekat_web.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Korisnik {

    private Integer id;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String ime;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String prezime;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String email;


    private String tip;


    private String status;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String lozinka;

    public Korisnik() {
    }

    public Korisnik(String ime, String prezime, String email, String lozinka) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.lozinka = lozinka;
    }


    public Korisnik(Integer id, String ime, String prezime, String email, String tip, String status, String lozinka) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.tip = tip;
        this.status = status;
        this.lozinka = lozinka;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
}
