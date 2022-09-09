package com.example.Projekat_web.repositories.vest;

import com.example.Projekat_web.entities.Vest;

import java.util.List;

public interface VestInterface {

    List<Vest> getVest();
    Vest addVest(Vest vest);
    List<Vest> getVestDate();
    List<Vest> getVestPosete();
    Vest updateVest(Vest vest);
    Vest findVest(Integer id);
    void deleteVest(Integer id);
    void updateVestDrugi(Vest vest);

}
