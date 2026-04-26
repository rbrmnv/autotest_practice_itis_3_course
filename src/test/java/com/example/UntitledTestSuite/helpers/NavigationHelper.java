package com.example.UntitledTestSuite.helpers;

import com.example.UntitledTestSuite.base.ApplicationManager;
import com.example.UntitledTestSuite.base.HelperBase;
import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

    private String baseUrl;

    public NavigationHelper(ApplicationManager app, String baseUrl) {
        super(app);
        this.baseUrl = baseUrl;
    }

    public void openHomePage() {
        driver.get(baseUrl);
    }

    public void goToLoginPage() {
        driver.findElement(By.linkText("Log in")).click();
    }

    public void goToCart() {
        driver.findElement(By.linkText("Shopping cart")).click();
    }
}