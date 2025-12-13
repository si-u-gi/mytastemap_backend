package com.mytastemap.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.mytastemap.api.domain.Store;
import jakarta.persistence.EntityManager;

@Repository
public class JpaStoreRepository implements StoreRepository  {
    private final EntityManager em;

    public JpaStoreRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Store> findStoresByMinRatingAndReviewCount(Double rating, Integer reviewCount) {
        List<Store> result = em.createQuery("""
            select s 
            from Store s
            where (
                :rating is null or s.rating >= :rating) and 
                (:reviewCount is null or s.reviewCount >= :reviewCount)
            """, Store.class)
                .setParameter("rating", rating)
                .setParameter("reviewCount", reviewCount)
                .getResultList();
        return result;
    }

    @Override
    public Store save(Store store) {
        em.persist(store);
        return store;
    }

    @Override
    public Optional<Store> findById(String kakaoId) {
        Store store = em.find(Store.class, kakaoId);
        return Optional.ofNullable(store);
    }

    @Override
    public List<Store> findAll() {
        return em.createQuery("select s from Store s", Store.class)
            .getResultList();
    }
}
