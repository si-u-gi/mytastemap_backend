package com.mytastemap.api.service;

import com.mytastemap.api.domain.Store;
import com.mytastemap.api.repository.StoreRepository;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeleniumRatingUpdateService {

    private final StoreRepository storeRepository;

    public void updateAllRatingsWithSelenium() {

        System.out.println("✅ Selenium 별점 & 리뷰 업데이트 시작");

        // ✅ 크롬 드라이버 설정 (로컬 Windows 기준)
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // 🔹 Codespaces / 서버 환경이면 headless 권장
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1280,720");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<Store> stores = storeRepository.findAll();
        System.out.println("✅ 대상 가게 수: " + stores.size());

        int success = 0;
        int fail = 0;

        for (Store store : stores) {
            String url = store.getPlaceUrl();
            if (url == null || url.isBlank()) continue;

            try {
                System.out.println("▶ 접속 중: " + url);
                driver.get(url);

                // 페이지 로딩 대기용 (필요하면 조절)
                Thread.sleep(2000);

                // ✅ 네가 찾은 selector 그대로 사용
                WebElement ratingEl = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("span.num_star")
                        )
                );

                WebElement reviewEl = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("span.info_num")
                        )
                );

                String ratingText = ratingEl.getText();           // 예: "2.6"
                String reviewText = reviewEl.getText();           // 예: "리뷰 123"
                double rating = Double.parseDouble(ratingText);
                int reviewCount = Integer.parseInt(
                        reviewText.replaceAll("[^0-9]", "")
                );

                store.setRating(rating);
                store.setReviewCount(reviewCount);
                storeRepository.save(store);

                success++;
                System.out.println("✅ 저장 완료: " + store.getName()
                        + " / rating=" + rating + ", reviews=" + reviewCount);

                // 너무 빠르게 요청 보내지 않도록 딜레이 (매우 중요)
                Thread.sleep(500);

            } catch (TimeoutException e) {
                fail++;
                System.out.println("⏰ 타임아웃: " + url);
            } catch (Exception e) {
                fail++;
                System.out.println("❌ 실패: " + url);
                e.printStackTrace();
            }
        }

        driver.quit();

        System.out.println("✅ Selenium 별점 업데이트 완료");
        System.out.println("   성공: " + success + ", 실패: " + fail);
    }
}
