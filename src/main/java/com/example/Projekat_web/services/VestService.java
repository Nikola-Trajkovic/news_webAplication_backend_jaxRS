package com.example.Projekat_web.services;

import com.example.Projekat_web.entities.Vest;
import com.example.Projekat_web.repositories.vest.VestInterface;

import javax.inject.Inject;
import java.util.List;

public class VestService {

    public VestService() {
    }

    @Inject
    private VestInterface vestInterface;

    public List<Vest> getVest(){
        return this.vestInterface.getVest();
    }

    public Vest addVest(Vest vest){
        return this.vestInterface.addVest(vest);
    }

    public List<Vest> getVestDate(){
        return this.vestInterface.getVestDate();
    }

    public List<Vest> getVestPosete(){
        return this.vestInterface.getVestPosete();
    }

    public Vest updatevest(Vest vest){
        return this.vestInterface.updateVest(vest);
    }

    public Vest findVest(Integer id){
        return this.vestInterface.findVest(id);
    }

    public void deleteVest(Integer id){
        this.vestInterface.deleteVest(id);
    }

    public void updateVestDrugi(Vest vest){
        this.vestInterface.updateVestDrugi(vest);
    }
}
