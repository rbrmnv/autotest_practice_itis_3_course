package com.example.UntitledTestSuite.base;

import org.openqa.selenium.*;

public class HelperBase {

    protected WebDriver driver;
    protected ApplicationManager app;

    public HelperBase(ApplicationManager app) {
        this.app = app;
        this.driver = app.getDriver();
    }
}