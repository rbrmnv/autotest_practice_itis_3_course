package com.example.UntitledTestSuite.helpers;

import com.example.UntitledTestSuite.base.ApplicationManager;
import com.example.UntitledTestSuite.base.HelperBase;
import com.example.UntitledTestSuite.model.AccountData;
import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {

    public LoginHelper(ApplicationManager app) {
        super(app);
    }

    public void login(AccountData user) {
        driver.findElement(By.id("Email")).clear();
        driver.findElement(By.id("Email")).sendKeys(user.getUsername());

        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("Password")).sendKeys(user.getPassword());

        driver.findElement(By.xpath("//input[@value='Log in']")).click();
    }
}