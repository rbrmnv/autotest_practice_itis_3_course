package com.example.UntitledTestSuite.tests;

import com.example.UntitledTestSuite.base.TestBase;
import com.example.UntitledTestSuite.model.AccountData;
import com.example.UntitledTestSuite.model.OrderAdressData;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ServiceTest extends TestBase {

  @Test
  public void loginTest() {
    AccountData user = new AccountData("robertromanov2018@mail.ru", "Qweasdzxc123$");

    app.getNavigation().openHomePage();
    app.getNavigation().goToLoginPage();
    app.getAuth().login(user);

    WebElement accountLink = app.getDriver().findElement(
            By.xpath("//div[@class='header-links']//a[contains(@href,'/customer/info')]")
    );
    Assert.assertEquals("robertromanov2018@mail.ru", accountLink.getText());
  }

  @Test
  public void orderTest() {
    AccountData user = new AccountData("robertromanov2018@mail.ru", "Qweasdzxc123$");
    OrderAdressData address = new OrderAdressData("Казань", "ул. Пушкина", "89127231257", "123");

    app.getNavigation().openHomePage();
    app.getNavigation().goToLoginPage();
    app.getAuth().login(user);

    app.getOrder().addItemToCart();
    app.getOrder().startCheckout();
    app.getOrder().fillAddress(address);
    app.getOrder().confirmOrder();

    WebElement thankYouHeading = app.getDriver().findElement(By.tagName("h1"));
    Assert.assertEquals("Thank you", thankYouHeading.getText());

    WebElement successMessage = app.getDriver().findElement(
            By.cssSelector(".order-completed .title strong")
    );
    Assert.assertEquals("Your order has been successfully processed!", successMessage.getText());
  }

  @Test
  public void removeFromCartTest() {
    AccountData user = new AccountData("robertromanov2018@mail.ru", "Qweasdzxc123$");

    app.getNavigation().openHomePage();
    app.getNavigation().goToLoginPage();
    app.getAuth().login(user);

    app.getOrder().addItemToCart();
    app.getOrder().removeItemFromCart();

    Assert.assertTrue("Cart should be empty after removing item",
            app.getOrder().isCartEmpty());
  }
}