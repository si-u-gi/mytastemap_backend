package com.mytastemap.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytastemap.api.service.SeleniumRatingUpdateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class RatingUpdateController {
    private final SeleniumRatingUpdateService seleniumRatingUpdateService;

    // ✅ Selenium으로 place_url 기준 별점/리뷰 업데이트
    @GetMapping("/update/ratings/selenium")
    public String updateRatingsWithSelenium() {
        seleniumRatingUpdateService.updateAllRatingsWithSelenium();
        return "Selenium 별점 & 리뷰 업데이트 완료";
    }
}
