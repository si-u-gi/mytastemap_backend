package com.mytastemap.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "stores") // 이러면 자동으로 DB에 테이블로 등록됨.
public class Store {

    @Id
    @Column(length = 30)
    private String kakaoId;

    private String name;
    private String category;
    private String address;
    private String roadAddress;
    private String phone;

    private double lat;
    private double lng;

    private String placeUrl;
    private Double rating;
    private Integer reviewCount;
}
