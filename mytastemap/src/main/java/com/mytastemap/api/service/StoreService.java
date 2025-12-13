package com.mytastemap.api.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.mytastemap.api.domain.Store;
import com.mytastemap.api.repository.StoreRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class StoreService {
    private final StoreRepository storeRepository;
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store save(Store store) {
        return storeRepository.save(store);
    }

    public List<Store> findStores() {
        return storeRepository.findAll();
    }

    public List<Store> getStoresByMinRatingAndReviews(Double rating, Integer reviewCount) {
        return storeRepository.findStoresByMinRatingAndReviewCount(rating, reviewCount);
    }

    public boolean existsById(String kakaoId) {
        return storeRepository.findById(kakaoId).isPresent();
    }
}
