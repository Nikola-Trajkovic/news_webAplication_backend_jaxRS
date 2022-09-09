package com.example.Projekat_web.repositories.korisnik;

import com.example.Projekat_web.entities.Korisnik;
import com.example.Projekat_web.entities.Login.Login;
import com.example.Projekat_web.entities.Login.LoginResponse;

import java.util.List;

public interface KorisnikInterface {

    List<Korisnik> getKorisnik();
    Korisnik addKorisnik(Korisnik korisnik);
    LoginResponse login(Login login);
    void deleteKorisnik(Integer id);
    Korisnik updateKorisnik(Korisnik korisnik);
    void logOut();

}
