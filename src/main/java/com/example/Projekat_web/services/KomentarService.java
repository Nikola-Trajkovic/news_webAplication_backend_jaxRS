package com.example.Projekat_web.services;

import com.example.Projekat_web.entities.Komentar;
import com.example.Projekat_web.repositories.komentar.KomentarInterface;

import javax.inject.Inject;
import java.util.List;

public class KomentarService {

    public KomentarService() {
    }

    @Inject
    private KomentarInterface komentarInterface;


    public List<Komentar> getKomentar(Integer id){
        return this.komentarInterface.getKomentar(id);
    }

    public Komentar addKomentar(Komentar komentar){
        return this.komentarInterface.addKomentar(komentar);
    }


}
