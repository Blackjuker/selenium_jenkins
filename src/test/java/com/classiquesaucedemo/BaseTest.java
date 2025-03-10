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

        // 🔹 Récupérer l'URL depuis une variable d'environnement
        String gridUrl = System.getProperty("selenium.grid.url", "http://selenium-hub:4444");

        if (gridUrl == null || gridUrl.isEmpty()) {
            gridUrl = "http://selenium-hub:4444"; // Valeur par défaut
        }

        try {
            driver = new RemoteWebDriver(new URL(gridUrl), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("L'URL du Selenium Grid est invalide !");
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
