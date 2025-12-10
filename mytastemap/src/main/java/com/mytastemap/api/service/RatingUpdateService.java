package com.mytastemap.api.service;

import com.mytastemap.api.domain.Store;
import com.mytastemap.api.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingUpdateService {

    private final StoreRepository storeRepository;

    public void updateAllRatings() {
        List<Store> stores = storeRepository.findAll();
        System.out.println("✅ 대상 가게 수: " + stores.size());

        if (stores.isEmpty()) {
            System.out.println("❌ 저장된 가게가 없음");
            return;
        }

        Store store = stores.get(0); // 일단 첫 번째만 테스트
        String url = store.getPlaceUrl();
        System.out.println("▶ 테스트 URL: " + url);

        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                    .referrer("https://map.kakao.com/")
                    .timeout(15000)
                    .get();

            System.out.println("✅ 페이지 타이틀: " + doc.title());

            // ✅ 실제로 num_star가 몇 개 있는지 체크
            int starCount = doc.select("span.num_star").size();
            int reviewCountEl = doc.select("span.info_num").size();
            System.out.println("✅ num_star 개수: " + starCount);
            System.out.println("✅ info_num 개수: " + reviewCountEl);

            Element ratingEl = doc.selectFirst("span.num_star");
            Element reviewEl = doc.selectFirst("span.info_num");

            System.out.println("✅ ratingEl: " + (ratingEl != null ? ratingEl.text() : "null"));
            System.out.println("✅ reviewEl: " + (reviewEl != null ? reviewEl.text() : "null"));

            if (ratingEl == null || reviewEl == null) {
                System.out.println("⚠ Jsoup으로는 별점/리뷰를 찾지 못함 (JS로 나중에 로딩될 가능성)");
                return;
            }

            double rating = Double.parseDouble(ratingEl.text());
            int reviewCount = Integer.parseInt(reviewEl.text().replaceAll("[^0-9]", ""));

            store.setRating(rating);
            store.setReviewCount(reviewCount);
            storeRepository.save(store);

            System.out.println("✅ 최종 저장: " + rating + " / " + reviewCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
