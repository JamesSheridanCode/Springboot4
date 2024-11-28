package com.example.packagetest.dao;

import com.example.packagetest.entity.Petition;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PetitionDAOImpl implements PetitionDAO{

    private EntityManager entityManager;

    @Autowired
    public PetitionDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Petition thePetition) {
        entityManager.persist(thePetition);
    }

    @Override
    public Petition findById(Long id) {
        return entityManager.find(Petition.class,id);
    }

    @Override
    public List<Petition> findAll() {
        //create query
        TypedQuery<Petition> theQuery = entityManager.createQuery("FROM Petition p ORDER BY p.id DESC", Petition.class);
        //return results
        return theQuery.getResultList();
    }

    @Override
    public List<Petition> findBySearchTerm(String theSearchTerm) {
        //create query
        TypedQuery<Petition> theQuery = entityManager.createQuery("FROM Petition WHERE title like :theData", Petition.class);
        //set query parameters
        theQuery.setParameter("theData","%" + theSearchTerm +"%");
        //return results
        return theQuery.getResultList();
    }
}
