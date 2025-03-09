package com.classiquesaucedemo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutOverview {

   @FindBy(className = "inventory_item_price")
   private List<WebElement> listItemPriceElement;

   @FindBy(className = "summary_total_label")
   private WebElement summaryTotalPriceElement;


    public CheckOutOverview(WebDriver driver) {
       PageFactory.initElements(driver, this);
    }

    public BigDecimal AdditionTotalPrixItem(){
        Double prix = 0.0;
        for (WebElement prixElement : listItemPriceElement){
            System.err.println(prixElement.getText());
            prix = prix + PrixExtractionFromArticle(prixElement.getText());
            System.err.println(prix);
        }

        return BigDecimal.valueOf(prix).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal RecuperationTotalPrix(){
        return BigDecimal.valueOf(PrixExtraction(summaryTotalPriceElement.getText())).setScale(2,RoundingMode.HALF_UP);
    }


    // Methode qui prend en parametre le prix : Total: $43.18 et retourne 43.18
    private Double PrixExtraction(String prixAvecTexte){
        return Double.parseDouble(prixAvecTexte.split("\\$")[1]);
    }

     // Methode qui prend en parametre le prix :  $43.18 et retourne 43.18
     private Double PrixExtractionFromArticle(String prixAvecTexte) {
        if (prixAvecTexte == null || !prixAvecTexte.startsWith("$")) {
            throw new IllegalArgumentException("Format du prix invalide : " + prixAvecTexte);
        }
        
        try {
            return Double.parseDouble(prixAvecTexte.substring(1)); // Supprime le "$" et convertit en double
        } catch (NumberFormatException e) {
            throw new RuntimeException("Erreur lors de la conversion du prix : " + prixAvecTexte, e);
        }
    }
    

   
}
