package com.mytastemap.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mytastemap.api.service.KakaoLocationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
@CrossOrigin(origins = "*") // ✅ Flutter, WebView 모두 허용
public class LocationController {

    private final KakaoLocationService kakaoLocationService;

    @GetMapping("/stores")
    public ResponseEntity<String> getNearbyStores(
            @RequestParam double lat,
            @RequestParam double lng
    ) {
        String result = kakaoLocationService.searchNearbyStores(lat, lng);
        return ResponseEntity.ok(result); // ✅ JSON 그대로 반환
    }
}
