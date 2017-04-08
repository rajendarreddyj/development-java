package com.rajendarreddyj.selenium.test;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class TestGoogleHomePage {
    private static final String HTTP_WWW_GOOGLE_COM = "http://www.google.com";
    private WebDriver driver;

    @Before
    public void beforeClass() {
        driver = new HtmlUnitDriver();
        ((HtmlUnitDriver) driver).setJavascriptEnabled(true);

    }

    @After
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void verifySearchButton() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(HTTP_WWW_GOOGLE_COM);
        Assert.assertEquals("Title not found!", driver.getTitle(), "Google");
        WebElement search_button = driver.findElement(By.name("btnK"));
        String text = search_button.getAttribute("value");
        Assert.assertEquals("Text not found!",text, "Google Search");
    }
}
