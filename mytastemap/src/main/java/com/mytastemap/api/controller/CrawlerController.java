package com.mytastemap.api.controller;

import com.mytastemap.api.service.GangnamAutoCrawler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class CrawlerController {

    private final GangnamAutoCrawler gangnamAutoCrawler;

    // ✅ 브라우저에서 한 번 눌러서 실행 (학습용)
    @GetMapping("/crawl/gangnam")
    public String crawlGangnam() throws Exception {
        gangnamAutoCrawler.crawlGangnamAll();
        return "강남구 전체 자동 수집 완료";
    }
}
