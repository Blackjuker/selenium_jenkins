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
        System.out.println("➡ [SETUP] Démarrage du WebDriver");

        if (driver == null) {
            ChromeOptions options = new ChromeOptions();

            // 🔹 Vérifier l'URL de Selenium Grid
            String gridUrl = System.getProperty("selenium.grid.url", System.getenv("SELENIUM_GRID_URL"));
            if (gridUrl == null || gridUrl.isEmpty()) {
                gridUrl = "http://selenium-hub:4444/wd/hub"; // ✅ En local, utilise localhost
            }

            String fullGridUrl = gridUrl + "/wd/hub";  // ✅ Ajoute `/wd/hub` obligatoirement
            System.out.println("➡ [SETUP] Connexion à Selenium Grid: " + fullGridUrl);

            try {
                driver = new RemoteWebDriver(new URL(fullGridUrl), options);
                System.out.println("✅ [SETUP] WebDriver initialisé avec succès !");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new RuntimeException("❌ [SETUP] URL du Selenium Grid invalide !");
            } catch (Exception e) {
                System.err.println("❌ [SETUP] Impossible de se connecter à Selenium Grid !");
                e.printStackTrace();
                throw new IllegalStateException("❌ WebDriver n'a pas pu être initialisé !");
            }
        } else {
            System.out.println("✅ [SETUP] WebDriver déjà initialisé !");
        }

        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            System.out.println("➡ [TEARDOWN] Fermeture du WebDriver");
            driver.quit();
            driver = null;  // ✅ Important pour éviter des sessions fantômes
        }
    }
}