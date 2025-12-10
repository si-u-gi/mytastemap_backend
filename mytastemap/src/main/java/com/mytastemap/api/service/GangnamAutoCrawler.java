package com.mytastemap.api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytastemap.api.domain.Store;
import com.mytastemap.api.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GangnamAutoCrawler {

    private final KakaoLocationService kakaoLocationService;
    private final StoreRepository storeRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    // ✅ 강남구 전체 자동 수집 (학습용 수동 실행)
    public void crawlGangnamAll() throws Exception {

        double latStart = 37.4500;
        double latEnd   = 37.5400;
        double lngStart = 127.0000;
        double lngEnd   = 127.1200;

        double step = 0.01; // 약 1km

        for (double lat = latStart; lat <= latEnd; lat += step) {
            for (double lng = lngStart; lng <= lngEnd; lng += step) {

                System.out.println("📍 수집 중: " + lat + ", " + lng);

                String json = kakaoLocationService.searchNearbyStores(lat, lng);

                JsonNode root = mapper.readTree(json);
                JsonNode documents = root.get("documents");

                for (JsonNode doc : documents) {

                    String kakaoId = doc.get("id").asText();

                    // ✅ 이미 저장된 가게는 스킵
                    if (storeRepository.existsById(kakaoId)) continue;

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

                    storeRepository.save(store);
                }

                // ✅ 카카오 API 과호출 방지 (필수)
                Thread.sleep(300);
            }
        }

        System.out.println("✅ 강남구 전체 수집 완료");
    }

    
}