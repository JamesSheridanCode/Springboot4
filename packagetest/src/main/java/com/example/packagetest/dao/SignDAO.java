package com.example.packagetest.dao;

import com.example.packagetest.entity.Petition;
import com.example.packagetest.entity.Sign;

import java.util.List;

public interface SignDAO {

    void save(Sign theSign);
    List<Sign> findAllByPetitionId(Long petitionId);
    int getSignCountById(Long id);
}
