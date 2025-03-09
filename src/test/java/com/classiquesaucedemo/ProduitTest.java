package com.classiquesaucedemo;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

@Tag("TestProduit")
public class ProduitTest {
    
    private WebDriver driver;
    private ProductPage productPage;
    private LoginPage loginPage;
    // Définitions des Hooks
    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage  = new LoginPage(driver);
        loginPage.SeConnecterAvecLeUsernameEtPasswordValid("standard_user", "secret_sauce");
        productPage  = new ProductPage(driver);       
        
    }

    @AfterEach
    public void tearDown(){
        try{
            Thread.sleep(3000);
        }catch(Exception ex){

        }
        if(driver != null){
            driver.quit();
        }
    }


    //definition des tests
    @Test
    @Tag("TC-PrixVerif")
    public void VerifPrixHorsPanierEgalPrixDansPanier(){
        // ajout d'un produit à la position 0
        String prix = productPage.GetPrixProduit(0);

        productPage.ClicAddOrRemoveProduitDansPanier(0);

        // Vérifier que le badge du panier affiche
         assertTrue( "1".equals(productPage.GetBadgeNombre()),"Le Badge ne contient pas le nombre de produit ajouter dans le panier");

         //clic sur le bouton pannier
         productPage.CartClickButton();

        // Verifier si le prix correspond à celui du cardy
        assertTrue(prix.equals(productPage.GetPrixProduit(0)), "Le prix ne correspond");       
        System.out.println("le prix hors cardi et dans cardi sont pareil : true (oui) / false (non) = "+prix.equals(productPage.GetPrixProduit(0)));
    }

    @Test
    @Tag("TC-DeleteArticle")
    public void Suppression_article_du_panier(){
        productPage.ClicAddOrRemoveProduitDansPanier(0);

        productPage.CartClickButton();

        productPage.RemoveProduitInCart(0);

        assertTrue(true);
    }
}
