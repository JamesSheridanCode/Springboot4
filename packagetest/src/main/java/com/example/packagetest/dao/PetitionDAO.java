package com.example.packagetest.dao;

import com.example.packagetest.entity.Petition;

import java.util.List;

public interface PetitionDAO {
    void save(Petition thePetition);
     Petition findById(Long id);

     List<Petition> findAll();

    List<Petition> findBySearchTerm(String theSearchTerm);
}
