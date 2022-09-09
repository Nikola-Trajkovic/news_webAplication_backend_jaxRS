package com.example.Projekat_web.services;

import com.example.Projekat_web.entities.Kategorija;
import com.example.Projekat_web.entities.Vest;
import com.example.Projekat_web.repositories.kategorija.KategorijaInterface;

import javax.inject.Inject;
import java.util.List;

public class KategorijaService {

    public KategorijaService() {
    }

    @Inject
    private KategorijaInterface kategorijaInterface;

    public List<Kategorija> getKategorija(){
        return this.kategorijaInterface.getKategorija();
    }

    public Kategorija addKategorija(Kategorija kategorija){
        return this.kategorijaInterface.addKategorija(kategorija);
    }

    public Kategorija updateKategorija(Kategorija kategorija){
        return this.kategorijaInterface.updateKategorija(kategorija);
    }

    public void deleteKategorija(Integer id){
        this.kategorijaInterface.deleteKategorija(id);
    }

    public List<Vest> vestKategorija(Integer id){
        return this.kategorijaInterface.vestiKategorija(id);
    }

}
