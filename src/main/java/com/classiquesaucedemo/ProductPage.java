package com.classiquesaucedemo;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

    @FindBy(className = "inventory_item_name")
    private List<WebElement> listTitleProductsElements;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> listItemPriceElements;

    @FindBy(css = ".btn_inventory")
    private List<WebElement> listButtonAddToCartsElements;

    @FindBy(className = "shopping_cart_badge")
    private WebElement badgeButtonElement;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartButtonElement;

    @FindBy(css = ".cart_button")
    private List<WebElement> listButtonInCartsElements;

  



    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void ClicAddOrRemoveProduitDansPanier(int index){
        listButtonAddToCartsElements.get(index).click();
    }

    public int CountListProduit(){
        return listTitleProductsElements.size();
    }

    public String GetPrixProduit(int index){
        return listItemPriceElements.get(index).getText();
    }

    public String GetTitreProduit(int index){
        return listTitleProductsElements.get(index).getText();
    }

    public String GetBadgeNombre(){
        return badgeButtonElement.getText();
    }

    public void CartClickButton(){
        cartButtonElement.click();
    }

    public void RemoveProduitInCart(int index){
        listButtonInCartsElements.get(index).click();
    }







}
