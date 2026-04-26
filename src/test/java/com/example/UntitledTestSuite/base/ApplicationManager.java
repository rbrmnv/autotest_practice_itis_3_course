package com.example.UntitledTestSuite.base;

import com.example.UntitledTestSuite.helpers.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {

    private WebDriver driver;
    private String baseUrl;

    private NavigationHelper navigation;
    private LoginHelper auth;
    private OrderHelper order;

    public ApplicationManager() {
        driver = new ChromeDriver();
        baseUrl = "https://demowebshop.tricentis.com/";

        navigation = new NavigationHelper(this, baseUrl);
        auth = new LoginHelper(this);
        order = new OrderHelper(this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public NavigationHelper getNavigation() {
        return navigation;
    }

    public LoginHelper getAuth() {
        return auth;
    }

    public OrderHelper getOrder() {
        return order;
    }

    public void stop() {
        driver.quit();
    }
}