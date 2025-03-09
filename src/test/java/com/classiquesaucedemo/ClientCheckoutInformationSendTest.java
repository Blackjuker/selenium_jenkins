package com.classiquesaucedemo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ClientCheckoutInformationSendTest {

    private WebDriver driver;
    private ClientInformationPage clientInformationPage;
    
    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/checkout-step-one.html");
        LoginPage loginPage  = new LoginPage(driver);
        loginPage.SeConnecterAvecLeUsernameEtPasswordValid("standard_user", "secret_sauce");
        ProductPage productPage = new ProductPage(driver);
        productPage.ClicAddOrRemoveProduitDansPanier(0);
        productPage.ClicAddOrRemoveProduitDansPanier(1);
        productPage.CartClickButton();
        CartPage cartPage = new CartPage(driver);
        cartPage.ClicOnCheckOutButton();
        clientInformationPage = new ClientInformationPage(driver);
    }


    @AfterEach
    public void tearDown(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (driver!=null){
            driver.quit();
        }
    }


    @Test
    @Tag("TC-ValidFormulaire")
    public void Remplir_Formulaire_Valide(){
        clientInformationPage.SaisirFirstName("Arnaud");
        clientInformationPage.SaisirLastName("Ngabgna");
        clientInformationPage.SaisirAddresse("10 rue des Folles");
        clientInformationPage.ClicButtonContinueCheckoutInformation();
        assertTrue(driver.getCurrentUrl().contains("/checkout-step-two.html"),"Echec de l'enregistrement des informations au niveau Checkout");
    }


    

}
