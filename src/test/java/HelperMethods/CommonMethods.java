package HelperMethods;

import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CommonMethods {
    WebDriver driver;

    public CommonMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Assert.assertTrue(element.isDisplayed(), "Element is not visible!");
    }

    public void waitUntilElementIsPresent(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
            Assert.assertTrue(element.isDisplayed(), "Element is not visible!");
        } catch (Exception e) {
            System.out.println("Element is not visible or interactable: " + e.getMessage());
        }
    }

    public void verifyUrl(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();

        ChainTestListener.log("Verify URL: " + actualUrl);
        Assert.assertEquals(actualUrl, expectedUrl, "The actual URL does not match the expected URL!");
    }

    public void waitUsingThreadSleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
