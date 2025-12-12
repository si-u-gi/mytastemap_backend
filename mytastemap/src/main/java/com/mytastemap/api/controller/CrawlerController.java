package com.mytastemap.api.controller;

import com.mytastemap.api.service.AutoCrawler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class CrawlerController {

    private final AutoCrawler crawler;

    @GetMapping("/crawl/gangnam")
    public String crawlGangnam() throws Exception {
        // gangnamAutoCrawler.crawlGangnamAll();
        return "강남구 전체 자동 수집 완료";
    }

    @GetMapping("/crawl/yeongdeungpo")
    public String crawlYeongdeungpo() throws Exception {
        // crawler.crawlYeongdeungpoAll();
        crawler.crawlAll(37.4960, 37.5420, 126.8560, 126.9560, "영등포구");
        return "영등포구 전체 조사 시작";
    }
}
