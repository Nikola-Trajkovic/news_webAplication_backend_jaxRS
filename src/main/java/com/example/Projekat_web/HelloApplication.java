package com.example.Projekat_web;

import com.example.Projekat_web.entities.Kategorija;
import com.example.Projekat_web.entities.VestTag;
import com.example.Projekat_web.repositories.kategorija.KategorijaInterface;
import com.example.Projekat_web.repositories.kategorija.MySqlKategorijaRepository;
import com.example.Projekat_web.repositories.komentar.KomentarInterface;
import com.example.Projekat_web.repositories.komentar.MySqlKomentarRepository;
import com.example.Projekat_web.repositories.korisnik.KorisnikInterface;
import com.example.Projekat_web.repositories.korisnik.MySqlKorisnikRepository;
import com.example.Projekat_web.repositories.tag.MySqlTagRepository;
import com.example.Projekat_web.repositories.tag.TagInterface;
import com.example.Projekat_web.repositories.vest.MySqlVestRepository;
import com.example.Projekat_web.repositories.vest.VestInterface;
import com.example.Projekat_web.repositories.vest_tag.MySqlTagVestRepository;
import com.example.Projekat_web.repositories.vest_tag.VestTagInterface;
import com.example.Projekat_web.services.*;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {

    public static String email = "";
    public static String lozinka = "";

    public HelloApplication(){

        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {

                this.bind(MySqlKorisnikRepository.class).to(KorisnikInterface.class).in(Singleton.class);
                this.bind(MySqlKomentarRepository.class).to(KomentarInterface.class).in(Singleton.class);
                this.bind(MySqlKategorijaRepository.class).to(KategorijaInterface.class).in(Singleton.class);
                this.bind(MySqlTagRepository.class).to(TagInterface.class).in(Singleton.class);
                this.bind(MySqlVestRepository.class).to(VestInterface.class).in(Singleton.class);
                this.bind(MySqlTagVestRepository.class).to(VestTagInterface.class).in(Singleton.class);


                this.bindAsContract(KorisnikService.class);
                this.bindAsContract(KomentarService.class);
                this.bindAsContract(KategorijaService.class);
                this.bindAsContract(TagService.class);
                this.bindAsContract(VestService.class);
                this.bindAsContract(VestTagService.class);

            }
        };

        register(binder);

        packages("com.example.Projekat_web");

    }


    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        HelloApplication.email = email;
    }

    public static String getLozinka() {
        return lozinka;
    }

    public static void setLozinka(String lozinka) {
        HelloApplication.lozinka = lozinka;
    }

}