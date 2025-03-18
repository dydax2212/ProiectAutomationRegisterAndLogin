package HelperMethods;

import Logger.LoggerUtility;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ElementsMethods extends CommonMethods {
    WebDriver driver;
    Actions actions;

    public ElementsMethods(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public WebElement findElementAndWait(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private String getElementInfo(WebElement element) {
        return element.toString().split("->")[1]; // Extracts locator information
    }

    public void clickOnElement(WebElement element) {
        Assert.assertTrue(element.isDisplayed(), "Element is not visible!");
        Assert.assertTrue(element.isEnabled(), "Element is not clickable!");

        try {
            LoggerUtility.infoTest("Clicking on element: " + getElementInfo(element));
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void actionsClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

    public void sendTextToField(WebElement element, String text){
        element.sendKeys(text);
    }

    public boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
