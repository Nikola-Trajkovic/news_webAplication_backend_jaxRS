package com.example.Projekat_web.services;

import com.example.Projekat_web.entities.Korisnik;
import com.example.Projekat_web.entities.Login.Login;
import com.example.Projekat_web.entities.Login.LoginResponse;
import com.example.Projekat_web.repositories.korisnik.KorisnikInterface;

import javax.inject.Inject;
import java.util.List;

public class KorisnikService {

    public KorisnikService() {

    }

    @Inject
    private KorisnikInterface korisnikInterface;

    public List<Korisnik> getKorisnik(){
        return this.korisnikInterface.getKorisnik();
    }

    public Korisnik addKorisnik(Korisnik korisnik){
        return this.korisnikInterface.addKorisnik(korisnik);
    }

    public LoginResponse login(Login login){
        return this.korisnikInterface.login(login);
    }

    public void deleteKorisnik(Integer id){
        this.korisnikInterface.deleteKorisnik(id);
    }

    public Korisnik updatKorisnik(Korisnik korisnik){
        return this.korisnikInterface.updateKorisnik(korisnik);
    }

    public void logOut(){
        this.korisnikInterface.logOut();
    }

}
