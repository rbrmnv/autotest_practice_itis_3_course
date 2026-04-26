package com.example.UntitledTestSuite.helpers;

import com.example.UntitledTestSuite.base.ApplicationManager;
import com.example.UntitledTestSuite.base.HelperBase;
import com.example.UntitledTestSuite.model.OrderAdressData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

        clickContinueGlobal();

        clickIfPresent(By.xpath("//div[@id='shipping-buttons-container']//input"));
        clickIfPresent(By.xpath("//div[@id='shipping-method-buttons-container']//input"));
        clickIfPresent(By.xpath("//div[@id='payment-method-buttons-container']//input"));
        clickIfPresent(By.xpath("//div[@id='payment-info-buttons-container']//input"));

        clickIfPresent(By.xpath("//input[@value='Confirm']"));
    }

    private void clickContinueGlobal() {
        try {
            WebElement btn = driver.findElement(By.xpath("//input[@value='Continue']"));

            if (btn.isDisplayed() && btn.isEnabled()) {
                btn.click();
            }
        } catch (Exception ignored) {
        }
    }

    private void clickIfPresent(By locator) {
        try {
            WebElement el = driver.findElement(locator);

            if (el.isDisplayed() && el.isEnabled()) {
                el.click();
            }
        } catch (Exception ignored) {
        }
    }
}