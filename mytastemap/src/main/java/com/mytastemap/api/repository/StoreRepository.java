package com.mytastemap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytastemap.api.domain.Store;

public interface StoreRepository extends JpaRepository<Store, String> {
    boolean existsByKakaoId(String kakaoId);
}
