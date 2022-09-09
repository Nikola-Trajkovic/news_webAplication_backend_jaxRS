package com.example.Projekat_web.repositories.kategorija;

import com.example.Projekat_web.entities.Kategorija;
import com.example.Projekat_web.entities.Vest;

import java.util.List;

public interface KategorijaInterface {

    List<Kategorija> getKategorija();
    Kategorija addKategorija(Kategorija kategorija);
    Kategorija updateKategorija(Kategorija kategorija);
    void deleteKategorija(Integer id);
    List<Vest> vestiKategorija(Integer id);

}
