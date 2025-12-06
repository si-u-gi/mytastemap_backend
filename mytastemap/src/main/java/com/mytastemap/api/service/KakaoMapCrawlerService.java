package com.mytastemap.api.service;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import io.github.bonigarcia.wdm.WebDriverManager;

@Service
public class KakaoMapCrawlerService {

    public List<String> searchStores(String keyword) throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--lang=ko");
        options.addArguments("--remote-allow-origins=*");
        
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://map.kakao.com");

        Thread.sleep(3000);

        // ✅ 검색창 찾기
        WebElement searchBox = driver.findElement(By.id("search.keyword.query"));

        // ✅ 키워드 입력
        searchBox.sendKeys(keyword);

        // ✅ 엔터 입력
        searchBox.sendKeys(Keys.ENTER);

        Thread.sleep(500);

        // ✅ 가게 목록 가져오기
        List<WebElement> storeElements =
                driver.findElements(By.cssSelector(".placelist > .PlaceItem"));

        List<String> storeNames = new ArrayList<>();

        for (WebElement store : storeElements) {
            String name = store.findElement(By.cssSelector(".link_name")).getText();
            storeNames.add(name);
        }

        driver.quit();

        return storeNames;
    }
}
