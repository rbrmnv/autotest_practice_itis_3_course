package com.example.UntitledTestSuite.tests;

import com.example.UntitledTestSuite.base.TestBase;
import com.example.UntitledTestSuite.model.AccountData;
import com.example.UntitledTestSuite.model.OrderAdressData;
import org.junit.Test;

public class ServiceTest extends TestBase {

  @Test
  public void loginTest(){
    AccountData user = new AccountData("robertromanov2018@mail.ru", "Qweasdzxc123$");
    OrderAdressData address = new OrderAdressData("Казань", "ул. Пушкина", "89127231257", "123");

    app.getNavigation().openHomePage();
    app.getNavigation().goToLoginPage();
    app.getAuth().login(user);
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
  }
}