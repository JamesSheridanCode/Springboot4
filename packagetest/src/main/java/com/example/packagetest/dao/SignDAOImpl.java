package com.example.packagetest.dao;

import com.example.packagetest.entity.Petition;
import com.example.packagetest.entity.Sign;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository
public class SignDAOImpl implements SignDAO{

    @Autowired
    public SignDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private EntityManager entityManager;


    @Override
    @Transactional
    public void save(Sign theSign) {
        entityManager.persist(theSign);
    }

    @Override
    public List<Sign> findAllByPetitionId(Long petitionId) {
        TypedQuery<Sign> theQuery = entityManager.createQuery("SELECT s FROM Sign s WHERE s.petitionId = :petitionId", Sign.class);
        theQuery.setParameter("petitionId", petitionId );
        return theQuery.getResultList();
    }

    @Override
    public int getSignCountById(Long id) {
        Long count = entityManager.createQuery(
                        "SELECT COUNT(s) FROM Sign s WHERE s.petitionId = :petitionId", Long.class)
                .setParameter("petitionId", id)
                .getSingleResult();

        // Convert to int and return
        return count.intValue();
    }
}
