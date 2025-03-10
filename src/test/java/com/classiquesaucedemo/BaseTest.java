package com.classiquesaucedemo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();

        // 🔹 Récupérer l'URL du Selenium Grid
        String gridUrl = System.getProperty("selenium.grid.url", System.getenv("SELENIUM_GRID_URL"));

        if (gridUrl == null || gridUrl.isEmpty()) {
            gridUrl = "http://selenium-hub:4444"; // Valeur par défaut
        }

        try {
            System.out.println("➡ Connexion à Selenium Grid: " + gridUrl);
            driver = new RemoteWebDriver(new URL(gridUrl), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ URL du Selenium Grid invalide !");
        }

        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
