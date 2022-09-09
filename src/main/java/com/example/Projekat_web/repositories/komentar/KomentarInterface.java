package com.example.Projekat_web.repositories.komentar;

import com.example.Projekat_web.entities.Komentar;

import java.util.List;

public interface KomentarInterface {

    List<Komentar> getKomentar(Integer id);
    Komentar addKomentar(Komentar komentar);


}
