package com.classiquesaucedemo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckOutOverviewTest {

    WebDriver driver;
    CheckOutOverview checkOutOverview;
    ClientInformationPage clientInformationPage;
    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage  = new LoginPage(driver);
        loginPage.SeConnecterAvecLeUsernameEtPasswordValid("standard_user", "secret_sauce");
        ProductPage productPage = new ProductPage(driver);
        productPage.ClicAddOrRemoveProduitDansPanier(0);
        productPage.ClicAddOrRemoveProduitDansPanier(1);
        productPage.CartClickButton();
        CartPage cartPage = new CartPage(driver);
        cartPage.ClicOnCheckOutButton();
        ClientInformationPage clientInformationPage = new ClientInformationPage(driver);
        clientInformationPage.SaisirFirstName("Arnaud");
        clientInformationPage.SaisirLastName("Ngabgna");
        clientInformationPage.SaisirAddresse("12 rue des folles");
        clientInformationPage.ClicButtonContinueCheckoutInformation();
        checkOutOverview = new CheckOutOverview(driver);
    }

    @AfterEach
    public void tearDown(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(driver!=null){
            driver.quit();
        }
    }


    @Test
    @Tag("TC-PrixArticle    ")
    public void Test_SommePrixArticleEgalTotalPrixArticle(){
        BigDecimal prixDesArticles = checkOutOverview.AdditionTotalPrixItem();
        BigDecimal taxe = new BigDecimal(3.20);
        prixDesArticles = prixDesArticles.add(taxe).setScale(2,RoundingMode.HALF_UP);
        System.err.println("************ : "+prixDesArticles);

        assertTrue(prixDesArticles.equals(checkOutOverview.RecuperationTotalPrix()) ,"Le prix ne correspond pas");
    }
}
