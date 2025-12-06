package com.mytastemap.api.service;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

@Service
public class KakaoRatingCrawlerService {

    public double crawlRating(String placeUrl) {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(placeUrl);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement ratingElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("span.color_b")   // 카카오 평점 selector
                )
            );

            return Double.parseDouble(ratingElement.getText());

        } finally {
            driver.quit();
        }
    }
}