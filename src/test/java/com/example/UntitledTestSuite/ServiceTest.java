package com.example.UntitledTestSuite;

import org.junit.Test;

public class ServiceTest extends TestBase{

  @Test
  public void loginTest(){
    login(testUser);
  }

  @Test
  public void testOrdercadse(){
    login(testUser);
    putItemToCart();
    fillOrderAdress(testAddress);
    confirmOrderByClicks();
  }
}
