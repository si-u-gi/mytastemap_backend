package com.mytastemap.api.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mytastemap.api.dto.StoreDto;
import com.mytastemap.api.service.StoreService;

@RestController
@RequestMapping("/location")
@CrossOrigin(origins = "*") // ✅ Flutter, WebView 모두 허용
public class LocationController {
    private final StoreService storeService;
    public LocationController(StoreService storeService) {
        this.storeService = storeService;
    }

    // GET /stores?minRating=4.5&minReview=100
    @GetMapping("/stores")
    public List<StoreDto> getStores(
        @RequestParam(required = false) Double minRating,
        @RequestParam(required = false) Integer minReview
    ) {
        // 서비스 → 엔티티 목록 조회
        // stream() → 처리 시작
        // map(StoreDto::from) → DTO 변환
        // toList() → 반환

        return 
            storeService.getStoresByMinRatingAndReviews(minRating, minReview)
            .stream()
            .map(StoreDto::from)
            .toList();
    }
}
