package com.classiquesaucedemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends ProductPage{

    @FindBy(id="checkout")
    private WebElement checkoutButtonElement;

    public CartPage(WebDriver driver) {
        super(driver);
        //TODO Auto-generated constructor stub
    }

    public void ClicOnCheckOutButton(){
        checkoutButtonElement.click();
    }
    
}
