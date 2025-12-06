package com.mytastemap.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mytastemap.api.service.KakaoRatingCrawlerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class KakaoRatingController {

    private final KakaoRatingCrawlerService crawlerService;

    @GetMapping("/rating")
    public double getRating(@RequestParam String placeUrl) {
        return crawlerService.crawlRating(placeUrl);
    }
}