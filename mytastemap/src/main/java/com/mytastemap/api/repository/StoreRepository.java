package com.mytastemap.api.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.mytastemap.api.domain.Store;

@Repository
public interface StoreRepository {
    Store save(Store store);
    List<Store> findAll();
    Optional<Store> findById(String kakaoId);
    List<Store> findStoresByMinRatingAndReviewCount(Double rating, Integer reviewCount);
}
