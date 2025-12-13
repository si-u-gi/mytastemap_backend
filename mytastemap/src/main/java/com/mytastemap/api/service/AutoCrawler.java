package com.mytastemap.api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytastemap.api.domain.Store;
import org.springframework.stereotype.Service;

@Service
public class AutoCrawler {

    private final KakaoLocationService kakaoLocationService;
    private final StoreService storeService;

    public AutoCrawler(KakaoLocationService kakaoLocationService, StoreService storeService) {
        this.kakaoLocationService = kakaoLocationService;
        this.storeService = storeService;
    }

    private final ObjectMapper mapper = new ObjectMapper();

    // ✅ 강남구 전체 자동 수집 (학습용 수동 실행)
    public void crawlAll(double latStart, double latEnd, double lngStart, double lngEnd, String DISTRICT_NAME) throws Exception {
        double step = 0.0025; // 약 0.25km

        for (double lat = latStart; lat <= latEnd; lat += step) {
            for (double lng = lngStart; lng <= lngEnd; lng += step) {

                System.out.println("📍 수집 중: " + lat + ", " + lng);

                String json = kakaoLocationService.searchNearbyStores(lat, lng);

                JsonNode root = mapper.readTree(json);
                JsonNode documents = root.get("documents");

                for (JsonNode doc : documents) {

                    String kakaoId = doc.get("id").asText();

                    // ✅ 이미 저장된 가게는 스킵
                    if (storeService.existsById(kakaoId)) continue;

                    Store store = new Store();
                    store.setKakaoId(kakaoId);
                    store.setName(doc.get("place_name").asText());
                    store.setCategory(doc.get("category_name").asText());
                    store.setAddress(doc.get("address_name").asText());
                    store.setRoadAddress(doc.get("road_address_name").asText());
                    store.setPhone(doc.get("phone").asText());
                    store.setLng(doc.get("x").asDouble());
                    store.setLat(doc.get("y").asDouble());
                    store.setPlaceUrl(doc.get("place_url").asText());
                    store.setDistrict(DISTRICT_NAME);

                    storeService.save(store);
                }

                // ✅ 카카오 API 과호출 방지 (필수)
                Thread.sleep(300);
            }
        }

        System.out.println("수집 완료");
    }
}