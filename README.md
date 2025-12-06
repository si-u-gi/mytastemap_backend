# mytastemap
상업용이 절대 아닌, 저의 토이 프로젝트입니다.


./gradlew clean build
cd build/libs
java -jar mytastemap-0.0.1-SNAPSHOT.jar

https://animated-space-doodle-69x64p4766jwc5w55-8080.app.github.dev/location/stores?lat=37.4979&lng=127.0276


평점 4.x 이상 리뷰 개수 10개 이상 찾기??
리뷰에서 특히 괜찮은 리뷰 찾아서 맛집 선정??



document{} -> 해서 나오는 정보들 중에서 리뷰 사이트를 찾아서
    -> 리뷰어들을 크롤링
    -> 리뷰어들 중에서 평균 별점이 낮고 리뷰 개수가 많은 사람을 찾기.
    -> 이걸 이용해서 가중치를 적용해서 음식점마다의 등수를 매기면 좋겠지만 이건 될 지 모르겠음.