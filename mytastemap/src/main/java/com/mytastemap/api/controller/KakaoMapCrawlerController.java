package com.mytastemap.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mytastemap.api.service.KakaoMapCrawlerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/crawl")
public class KakaoMapCrawlerController {

    private final KakaoMapCrawlerService crawlerService;

    @GetMapping("/stores")
    public List<String> crawl(@RequestParam String keyword) throws InterruptedException {
        return crawlerService.searchStores(keyword);
    }
}
