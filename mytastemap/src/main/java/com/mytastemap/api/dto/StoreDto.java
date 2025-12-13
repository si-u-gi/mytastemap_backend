package com.mytastemap.api.dto;

import com.mytastemap.api.domain.Store;

public class StoreDto {
    private String id;
    private String name;
    private String category;
    private String address;
    private String phone;
    private double lat;
    private double lng;;
    private Double rating;
    private Integer reviewCount;
    private String district;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public Double getRating() {
        return rating;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public String getDistrict() {
        return district;
    }

    public StoreDto(String id, String name, String category, String address, String phone, double lat, double lng,
            Double rating, Integer reviewCount, String district) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.address = address;
        this.phone = phone;
        this.lat = lat;
        this.lng = lng;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.district = district;
    }
    
    public static StoreDto from(Store s) {
        return new StoreDto(
            s.getKakaoId(),
            s.getName(),
            s.getCategory(),
            s.getAddress(),
            s.getPhone(),
            s.getLat(),
            s.getLng(),
            s.getRating(),
            s.getReviewCount(),
            s.getDistrict()
        );
    }
}
