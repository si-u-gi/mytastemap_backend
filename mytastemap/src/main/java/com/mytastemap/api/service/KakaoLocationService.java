package com.mytastemap.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.common.net.HttpHeaders;

@Service
public class KakaoLocationService {

    private final WebClient webClient;

    public KakaoLocationService(
            @Value("${kakao.rest-api-key}") String apiKey
    ) {
        this.webClient = WebClient.builder()
                .baseUrl("https://dapi.kakao.com")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "KakaoAK " + apiKey)
                .build();
    }

    public String searchNearbyStores(double lat, double lng) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/local/search/category.json")
                        .queryParam("category_group_code", "FD6")
                        .queryParam("x", lng)
                        .queryParam("y", lat)
                        .queryParam("radius", 1000)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
