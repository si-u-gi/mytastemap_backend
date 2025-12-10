package com.mytastemap.api.controller;

import com.mytastemap.api.service.RatingUpdateService;
import com.mytastemap.api.service.SeleniumRatingUpdateService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class RatingUpdateController {

    private final RatingUpdateService ratingUpdateService;

    // ✅ DB에 저장된 place_url 기준 별점/리뷰 업데이트
    @GetMapping("/update/ratings")
    public String updateRatings() {
        ratingUpdateService.updateAllRatings();
        return "별점 & 리뷰 업데이트 완료";
    }

    private final SeleniumRatingUpdateService seleniumRatingUpdateService;

    // ✅ Selenium으로 place_url 기준 별점/리뷰 업데이트
    @GetMapping("/update/ratings/selenium")
    public String updateRatingsWithSelenium() {
        seleniumRatingUpdateService.updateAllRatingsWithSelenium();
        return "Selenium 별점 & 리뷰 업데이트 완료";
    }
}
