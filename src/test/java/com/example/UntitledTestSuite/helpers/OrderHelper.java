package com.example.UntitledTestSuite.helpers;

import com.example.UntitledTestSuite.base.ApplicationManager;
import com.example.UntitledTestSuite.base.HelperBase;
import com.example.UntitledTestSuite.model.OrderAdressData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderHelper extends HelperBase {

    protected WebDriverWait wait;

    public OrderHelper(ApplicationManager app) {
        super(app);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addItemToCart() {
        driver.findElement(By.linkText("Computers")).click();
        driver.findElement(By.linkText("Notebooks")).click();
        driver.findElement(By.linkText("14.1-inch Laptop")).click();
        driver.findElement(By.id("add-to-cart-button-31")).click();
    }

    public void startCheckout() {
        driver.findElement(By.linkText("Shopping cart")).click();
        driver.findElement(By.id("termsofservice")).click();
        driver.findElement(By.id("checkout")).click();
    }

    public void fillAddress(OrderAdressData address) {

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("billing-address-select")
        ));

        new Select(driver.findElement(By.id("billing-address-select")))
                .selectByVisibleText("New Address");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("BillingNewAddress_CountryId")
        ));

        driver.findElement(By.id("BillingNewAddress_FirstName"))
                .sendKeys("Robert");

        driver.findElement(By.id("BillingNewAddress_LastName"))
                .sendKeys("Romanov");

        Select country = new Select(driver.findElement(By.id("BillingNewAddress_CountryId")));
        country.selectByVisibleText("Russia");

        driver.findElement(By.id("BillingNewAddress_City"))
                .sendKeys(address.getCity());

        driver.findElement(By.id("BillingNewAddress_Address1"))
                .sendKeys(address.getAddress());

        driver.findElement(By.id("BillingNewAddress_ZipPostalCode"))
                .sendKeys(address.getZipPostalCode());

        driver.findElement(By.id("BillingNewAddress_PhoneNumber"))
                .sendKeys(address.getPhoneNumber());

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='billing-buttons-container']//input[@value='Continue']")
        )).click();
    }

    public void confirmOrder() {
        waitAndClick(By.xpath("//div[@id='shipping-buttons-container']/input"));
        waitAndClick(By.xpath("//div[@id='shipping-method-buttons-container']/input"));
        waitAndClick(By.xpath("//div[@id='payment-method-buttons-container']/input"));
        waitAndClick(By.xpath("//div[@id='payment-info-buttons-container']/input"));
        waitAndClick(By.xpath("//input[@value='Confirm']"));
        wait.until(ExpectedConditions.urlContains("checkout/completed"));
    }

    private void waitAndClick(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void removeItemFromCart() {
        driver.findElement(By.xpath("//li[@id='topcartlink']/a/span")).click();
        driver.findElement(By.name("removefromcart")).click();
        driver.findElement(By.name("updatecart")).click();
    }

    public boolean isCartEmpty() {
        String bodyText = driver.findElement(
                By.cssSelector(".order-summary-content")
        ).getText();
        return bodyText.contains("Your Shopping Cart is empty!");
    }
}