package com.example.UntitledTestSuite.helpers;

import com.example.UntitledTestSuite.base.ApplicationManager;
import com.example.UntitledTestSuite.base.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

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
        List<WebElement> logoutLinks = driver.findElements(By.linkText("Log out"));
        if (!logoutLinks.isEmpty()) {
            logoutLinks.get(0).click();
        }
        driver.findElement(By.linkText("Log in")).click();
    }
}