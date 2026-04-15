package com.example.UntitledTestSuite;

import java.time.Duration;

import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestBase {


  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  public AccountData testUser;
  public OrderAdressData testAddress;
  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver-win64\\chromedriver.exe");
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    js = (JavascriptExecutor) driver;

    testUser = new AccountData("robertromanov2018@mail.ru", "Qweasdzxc123$");
    testAddress = new OrderAdressData("Казань","ул. Пушкина, д.32, кв.197",
            "89127231257", "123");
  }

  public void login(AccountData user) {
    openHomePage();
    clickLoginLink();
    fillLoginForm(user);
  }

  public void confirmOrderByClicks() {
    driver.findElement(By.xpath("//input[@value='Continue']")).click();
    driver.findElement(By.xpath("//div[@id='shipping-buttons-container']/input")).click();
    driver.findElement(By.xpath("//div[@id='shipping-method-buttons-container']/input")).click();
    driver.findElement(By.xpath("//div[@id='payment-method-buttons-container']/input")).click();
    driver.findElement(By.xpath("//div[@id='payment-info-buttons-container']/input")).click();
    driver.findElement(By.xpath("//input[@value='Confirm']")).click();
  }

  public void fillOrderAdress( OrderAdressData adress) {
    driver.findElement(By.id("BillingNewAddress_Email")).click();
    driver.findElement(By.id("BillingNewAddress_Email")).click();
    driver.findElement(By.xpath("//div[@id='billing-new-address-form']/div/div/div/div[3]")).click();
    driver.findElement(By.id("BillingNewAddress_Email")).click();
    driver.findElement(By.xpath("//div[@id='billing-new-address-form']/div/div/div")).click();
    new Select(driver.findElement(By.id("BillingNewAddress_CountryId"))).selectByVisibleText("Russia");
    driver.findElement(By.id("BillingNewAddress_City")).clear();
    driver.findElement(By.id("BillingNewAddress_City")).sendKeys(adress.getCity());
    driver.findElement(By.id("BillingNewAddress_Address1")).clear();
    driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys(adress.getAddress());
    driver.findElement(By.id("BillingNewAddress_PhoneNumber")).clear();
    driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys(adress.getPhoneNumber());
    driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).click();
    driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).clear();
    driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys(adress.getZipPostalCode());
  }

  public void putItemToCart() {
    driver.findElement(By.linkText("Computers")).click();
    driver.findElement(By.xpath("//img[@alt='Picture for category Notebooks']")).click();
    driver.findElement(By.xpath("//img[@alt='Picture of 14.1-inch Laptop']")).click();
    driver.findElement(By.id("add-to-cart-button-31")).click();
    driver.findElement(By.linkText("shopping cart")).click();
    driver.findElement(By.id("termsofservice")).click();
    driver.findElement(By.id("checkout")).click();
    driver.findElement(By.id("billing-address-select")).click();
    new Select(driver.findElement(By.id("billing-address-select"))).selectByVisibleText("New Address");
    driver.findElement(By.xpath("//div[@id='billing-new-address-form']/div")).click();
    driver.findElement(By.xpath("//div[@id='billing-new-address-form']/div/div/div/div[2]")).click();
  }

  public void fillLoginForm(AccountData user) {
    driver.findElement(By.id("Email")).click();
    driver.findElement(By.id("Email")).clear();
    driver.findElement(By.id("Email")).sendKeys(user.getUsername());
    driver.findElement(By.id("Password")).click();
    driver.findElement(By.id("Password")).clear();
    driver.findElement(By.id("Password")).sendKeys(user.getPassword());
    driver.findElement(By.xpath("//input[@value='Log in']")).click();
  }

  public void clickLoginLink() {
    driver.findElement(By.linkText("Log in")).click();
  }

  public void openHomePage() {
    driver.get("https://demowebshop.tricentis.com/");
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
